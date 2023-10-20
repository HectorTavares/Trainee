import './quests.css'
import { useEffect, useState } from 'react'
import { QuestCard, QuestSelected, Loader, Error, QuestButton, StoreWarningBox } from '../../components'
import { useWowApi } from '../../../api/wow/use-wow-api'
import { useHistory } from 'react-router-dom'
import { useGlobalCharacter } from '../../../context'

const QuestsList = ({ allQuests, onClickAction, selectedQuestId, disabledButton }) => {
  const quests = allQuests ? allQuests : []

  return (
    <ul className="quests-list">
      {quests.map(quest => (
        <li key={quest.id} className="quests-list__item">
          <QuestCard
            onClickAction={onClickAction}
            selectedQuestId={selectedQuestId}
            questId={quest.id}
            questDescription={quest.description}
            disabledButton={disabledButton}
          />
        </li>
      ))}
    </ul>
  )
}

const ERROR_MESSAGES_API = {
  ALREADY_IN_QUEST: 'Você já tem uma quest em andamento',
  NO_QUEST_TO_FINISH: 'Este personagem tem nenhuma missão para finalizar',
  QUEST_STILL_IN_PROGRESS: 'Ainda não é possível finalizar a missão',
  EXPANSION_REQUIRED: 'Você não possui a expansão necessária para iniciar essa missão',
}

const getQuestById = (questId, quests) => {
  return quests.find(quest => quest.id === questId)
}

const SUCCESS_WARNINGS = {
  API_SUCCESS_START_QUEST: 'Seu personagem começou uma missão!',
  API_SUCCESS_FINISH_QUEST: 'Seu personagem terminou uma missão com sucesso!',
}

const verifyReasonError = errorData => {
  const { message } = errorData

  switch (message) {
    case ERROR_MESSAGES_API.ALREADY_IN_QUEST:
      return 'O personagem já está em uma missão!'

    case ERROR_MESSAGES_API.NO_QUEST_TO_FINISH:
      return 'O personagem não está em nenhuma missão!'

    case ERROR_MESSAGES_API.QUEST_STILL_IN_PROGRESS:
      return 'O personagem ainda não acabou a missão!'

    case ERROR_MESSAGES_API.EXPANSION_REQUIRED:
      return 'Falta de expansão necessária!'

    default:
      return 'Não foi possível!'
  }
}

export const QuestsScreen = () => {
  const [globalIdCharacter] = useGlobalCharacter()
  const [isLoading, setIsLoading] = useState(true)
  const [hasError, setHasError] = useState(false)
  const [warning, setWarning] = useState('')
  const [allQuests, setAllQuests] = useState([])
  const [selectedQuestId, setSelectedQuestId] = useState(1)
  const [character, setCharacter] = useState(null)

  const history = useHistory()
  const wowApi = useWowApi()

  useEffect(() => {
    const getQuests = async () => {
      setIsLoading(true)

      try {
        const quests = await wowApi.getQuests()
        setAllQuests(quests)

        setIsLoading(false)
        setHasError(false)
      } catch (error) {
        setHasError(true)
        setIsLoading(false)
      }
    }

    const getCharacter = async () => {
      setIsLoading(true)

      try {
        const updatedCharacter = await wowApi.getMyCharacterById(globalIdCharacter.id)
        setCharacter(updatedCharacter)

        if (updatedCharacter.busy) {
          const idQuestInProgress = updatedCharacter.questInProgress.id
          setSelectedQuestId(idQuestInProgress)
        }

        setIsLoading(false)
        setHasError(false)
      } catch (error) {
        setHasError(true)
        setIsLoading(false)
      }
    }

    getQuests()
    getCharacter()
  }, [wowApi])

  const handleSelectQuest = questId => {
    setSelectedQuestId(questId)
  }

  const startQuest = async () => {
    setIsLoading(true)

    try {
      await wowApi.startQuest(selectedQuestId, globalIdCharacter.id)
      setWarning(SUCCESS_WARNINGS.API_SUCCESS_START_QUEST)

      const updatedCharacter = { ...character, busy: true }
      setCharacter(updatedCharacter)

      setIsLoading(false)
      setHasError(false)
    } catch (error) {
      setIsLoading(false)
      const newWarning = verifyReasonError(error.response.data)
      setWarning(newWarning)
    }
  }

  const finishQuest = async () => {
    setIsLoading(true)

    try {
      await wowApi.finishQuest(globalIdCharacter.id)
      setWarning(SUCCESS_WARNINGS.API_SUCCESS_FINISH_QUEST)

      const updatedCharacter = { ...character, busy: false }
      setCharacter(updatedCharacter)

      setIsLoading(false)
      setHasError(false)
    } catch (error) {
      setIsLoading(false)
      const newWarning = verifyReasonError(error.response.data)
      setWarning(newWarning)
    }
  }

  const handleBackToWorld = () => {
    history.push('/world')
  }

  const handleWarningOkClick = () => {
    setWarning('')
  }

  return (
    <div className="quests">
      <div className="quests__container">
        <QuestButton title="Voltar" onClickAction={handleBackToWorld} />

        <div className="quests__content">
          {allQuests.length > 0 && !character?.busy ? (
            <QuestSelected
              quest={getQuestById(selectedQuestId, allQuests)}
              onClickAction={startQuest}
              buttonTitle="Começar"
            />
          ) : null}
          {allQuests.length > 0 && character?.busy ? (
            <QuestSelected
              quest={getQuestById(selectedQuestId, allQuests)}
              onClickAction={finishQuest}
              buttonTitle="Finalizar missão"
            />
          ) : null}

          <div className="quests__all-quests">
            <header className="quests__all-quests__header">
              <h1 className="quests-title">MISSÕES</h1>
            </header>

            {isLoading ? <Loader /> : null}

            {hasError ? (
              <Error />
            ) : (
              <QuestsList
                allQuests={allQuests}
                onClickAction={handleSelectQuest}
                selectedQuestId={selectedQuestId}
                disabledButton={character?.busy}
              />
            )}
          </div>
        </div>
      </div>

      {warning !== '' ? <StoreWarningBox warning={warning} onClickOk={handleWarningOkClick} /> : null}
    </div>
  )
}
