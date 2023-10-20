import './battle.style.css'
import { Button } from '../../components/button/button.component'
import { useEffect, useState } from 'react'
import { useWowApi } from '../../../api/wow/use-wow-api'
import { Loader, BattleCard } from '../../components'
import { useHistory } from 'react-router-dom'

export const BattleScreen = () => {
  const [allCharacters, setAllCharacters] = useState([])
  const [selectedCharacter, setSelectedCharacter] = useState(null)
  const [error, setError] = useState()
  const wowAPi = useWowApi()
  const [isLoading, setIsLoading] = useState(true)
  const { push } = useHistory()
  const [initial, setInicial] = useState(0)
  const [final, setFinal] = useState(24)
  const [charToShow, setCharToShow] = useState([])
  const [disabledBtnLast, setDisabledBtnLast] = useState(true)

  useEffect(() => {
    const getAllCharacters = async () => {
      setIsLoading(true)
      try {
        const response = await wowAPi.getAllCharacters()
        setAllCharacters(response)
        setCharToShow(
          response.filter(char => response.indexOf(char) >= initial && response.indexOf(char) < final)
        )
        setIsLoading(false)
        if (initial === 0) {
          setDisabledBtnLast(true)
        }
      } catch (error) {

        setIsLoading(false)

        setError(true)
      }
    }
    getAllCharacters()
  }, [wowAPi])

  useEffect(() => {
    setCharToShow(
      allCharacters.filter(
        char => allCharacters.indexOf(char) >= initial && allCharacters.indexOf(char) < final
      )
    )

    if (initial === 0) {
      setDisabledBtnLast(true)
    }
  }, [initial, final])

  const newPage = () => {
    setCharToShow(
      allCharacters.filter(
        char => allCharacters.indexOf(char) >= initial && allCharacters.indexOf(char) < final
      )
    )
    setInicial(initial + 24)
    setFinal(final + 24)
    setDisabledBtnLast(false)
  }

  const lastPage = () => {
    setCharToShow(
      allCharacters.filter(
        char => allCharacters.indexOf(char) >= initial && allCharacters.indexOf(char) < final
      )
    )
    setInicial(initial - 24)
    setFinal(final - 24)
  }

  const onSelectCharacter = selectedCharacterId => {
    if (selectedCharacterId === selectedCharacter?.id) {
      setSelectedCharacter(null)
      return
    }

    const character = allCharacters.find(character => character.id === selectedCharacterId)
    setSelectedCharacter(character)
  }

  const onGoToBattleField = () => {
    push({
      pathname: '/battle/battlefield',
      enemyCharacter: selectedCharacter,
    })
  }

  const onGoToWorld = () => {
    push('/world')
  }

  return (
    <section className="battleScreen">
      <header className="battleScreen__header">
        <Button onClick={onGoToWorld}>
          <p className="battleScreen__text">Voltar</p>
        </Button>
        <Button
          disabled={disabledBtnLast}
          className="button-next-back"
          onClick={lastPage}
        >
          Anterior
        </Button>
        <Button className="button-next-back" onClick={newPage}>
          Próximo
        </Button>

        <h1 className="battleScreen__header-tittle">SELECIONE UM ADVESARIO</h1>

        <Button onClick={onGoToBattleField} disabled={!selectedCharacter}>
          <p className="battleScreen__text">Batalhar</p>
        </Button>
      </header>

      <div className="battleScreen__content">
        {isLoading && <Loader />}
        {error ? (
          <h2>Erro no servidor, volte mais tarde!</h2>
        ) : charToShow ? (
          charToShow.map(character => (
            <BattleCard
              key={character.id}
              id={character.id}
              name={character.name}
              race={character.race.name}
              level={character.level}
              onClick={onSelectCharacter}
              selected={selectedCharacter?.id === character.id}
            />
          ))
        ) : (
          <Loader />
        )}
      </div>
    </section>
  )
}
