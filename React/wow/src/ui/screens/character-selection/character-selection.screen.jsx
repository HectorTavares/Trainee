import { useEffect, useState } from 'react'
import './character-selection.style.css'
import { useWowApi } from '../../../api/wow/use-wow-api'
import { CharacterItem } from './character-item/character-item.component'
import { CharacterInfo } from './character-info/character-info.component'
import logoWow from '../../../assets/img/foto-wow.png'
import { MenuContainer, Loader, Error,Button } from '../../components'
import { useGlobalCharacter, useGlobalUser } from '../../../context'
import { CreateCharacterForm } from './create-character-form/create-character-form.component'
import { useHistory } from 'react-router-dom'
import { CardCharacter } from '../../components/card-character/card-character.component'
import { ConfirmationBox } from './confirmation-box/confirmation-box.component'

export const CharacterSelectionScreen = () => {
  const [, setGlobalUser] = useGlobalUser()
  const [selectedCharacter, setSelectedCharacter] = useState(null)
  const [myCharacters, setMyCharacters] = useState([])
  const [creatingCharacter, setCreatingCharacter] = useState(false)
  const [isLoading, setIsLoading] = useState(true)
  const [hasError, setHasError] = useState(false)
  const [, setGlobalCharacter] = useGlobalCharacter()
  const [excludingCharacter, setExcludingCharacter] = useState(false)
  const { push } = useHistory()

  const wowApi = useWowApi()

  useEffect(() => {
    setGlobalCharacter(selectedCharacter)
  }, [selectedCharacter])

  useEffect(() => {
    const getMyCharacter = async () => {
      setIsLoading(true)
      try {
        const response = await wowApi.getMyCharacters()
        setMyCharacters(response)
        setIsLoading(false)
      } catch (error) {
        setHasError(true)
        setIsLoading(false)
      }

    }

    getMyCharacter()
  }, [wowApi, creatingCharacter])

  useEffect(() => {
    if (!selectedCharacter) {
      setExcludingCharacter(false)
    }
  }, [selectedCharacter, excludingCharacter])

  const onSelectCharacter = selectedCharacterId => {
    if (selectedCharacterId === selectedCharacter?.id) {
      setSelectedCharacter(null)
      return
    }

    const character = myCharacters.find(character => character.id === selectedCharacterId)
    setSelectedCharacter(character)
  }

  const toggleCreateCharacter = () => {
    setCreatingCharacter(!creatingCharacter)
  }

  const onLogout = () => {
    localStorage.setItem('user', JSON.stringify({}))
    setGlobalUser({})
  }

  const onGoToWorld = () => {
    push('/world')
  }

  const onDeleteCharacter = () => {
    setExcludingCharacter(true)
  }
  const onAcceptDeleteCharacter = async () => {
    const newCharacterList = myCharacters.filter(character => character.id !== selectedCharacter.id)
    const updatedMyCharacters = [...newCharacterList]

    setIsLoading(true)
    try {
      wowApi.deleteCharacter(selectedCharacter.id)
      setMyCharacters(updatedMyCharacters)
      setSelectedCharacter(null)

      setIsLoading(false)
      setHasError(false)
    } catch (error) {
      setHasError(true)
      setIsLoading(false)
    }

    setExcludingCharacter(false)
  }

  const onDeclineDeleteCharacter = () => {
    setExcludingCharacter(false)
  }

  return (
    <section className="characterSelectionScreen">
      {excludingCharacter && selectedCharacter ? (
        <ConfirmationBox
          tittle="Tem certeza que quer deletar para sempre seu personagem?"
          paragraph="Essa ação não poderá ser desfeita!"
          onAccept={onAcceptDeleteCharacter}
          onDecline={onDeclineDeleteCharacter}
        />
      ) : null}

      {hasError ? (
        <Error />
      ) : (
        <>
          <div className="characterSelectionScreen__side">
            <img src={logoWow} alt="logoWow" className="characterSelectionScreen__logo" />
            {!creatingCharacter && selectedCharacter && (
              <MenuContainer>
                <CharacterInfo character={selectedCharacter} />
              </MenuContainer>
            )}
            {creatingCharacter && (
              <MenuContainer>
                <CreateCharacterForm toggleCreateCharacter={toggleCreateCharacter} />
              </MenuContainer>
            )}
          </div>
          <div className="characterSelectionScreen__midlle">
            <Button disabled={!selectedCharacter} onClick={onGoToWorld}>
              <p className="characterSelectionScreen__button-text">Entrar no Mundo</p>
            </Button>

            {selectedCharacter && (
              <div className="characterSelectionScreen__characterCard">
                <CardCharacter
                  name={selectedCharacter.name}
                  raceName={selectedCharacter.race.name}
                  level={selectedCharacter.level}
                  image={selectedCharacter.race.image}
                />
              </div>
            )}
          </div>
          <div className="characterSelectionScreen__side">
            <MenuContainer hasScroll={true}>
              {isLoading ? (
                <Loader />
              ) : (
                <>
                  {myCharacters?.length > 0 ? (
                    myCharacters.map(character => (
                      <CharacterItem
                        key={character.id}
                        id={character.id}
                        name={character.name}
                        race={character.race.name}
                        level={character.level}
                        selected={selectedCharacter?.id === character.id}
                        faction={character?.faction}
                        onClick={onSelectCharacter}
                      />
                    ))
                  ) : (
                    <p className="characterSelectionScreen__button-text">
                      Você não tem personagens ainda... <br />
                      Clique em Criar personagen
                    </p>
                  )}
                </>
              )}
            </MenuContainer>
            <div className="characterSelectionScreen__buttons">
              <Button onClick={toggleCreateCharacter}>
                <p className="characterSelectionScreen__button-text">Criar Personagem</p>
              </Button>
              <Button onClick={onDeleteCharacter}>
                <p className="characterSelectionScreen__button-text">Deletar personagem</p>
              </Button>
              <Button onClick={onLogout}>
                <p className="characterSelectionScreen__button-text">Sair</p>
              </Button>
            </div>
          </div>
        </>
      )}
    </section>
  )
}
