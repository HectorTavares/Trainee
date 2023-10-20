import './battlefield.style.css'
import { useGlobalCharacter } from '../../../context'
import { useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { CardCharacter, MenuContainer, Loader, Button } from '../../components'
import { useWowApi } from '../../../api'

export const Battlefield = () => {
  const [globalCharacter] = useGlobalCharacter()
  const [battleLog, setBattleLog] = useState([])
  const { push, location } = useHistory()
  const enemyCharacter = location?.enemyCharacter
  const wowAPi = useWowApi()
  const [isLoading, setIsLoading] = useState(true)
  const [error, setError] = useState(false)
  const [count, setCount] = useState(0)
  const [winner, setWinner] = useState(false)

  useEffect(() => {
    if (battleLog.battleLogs?.length === count + 1) {
      setWinner(true)
    } else if (battleLog.battleLogs?.length > count) {
      const interval = setInterval(() => {
        setCount(count + 1)
      }, 3000)
      return () => clearInterval(interval)
    }
  }, [battleLog.battleLogs?.length, count])

  useEffect(() => {
    const getBattleLog = async () => {
      try {
        const response = await wowAPi.getBattleLog(globalCharacter.id, enemyCharacter.id)
        setBattleLog(response)
        setIsLoading(false)
      } catch (error) {
        setError(true)
        setIsLoading(false)
      }
    }

    getBattleLog()
  }, [wowAPi])

  useEffect(() => {
    if (!enemyCharacter) {
      push('/battle')
    }
  }, [])

  return (
    <section className="battlefield">
      <div className="battlefield__card-content">
        <CardCharacter
          name={globalCharacter.name}
          raceName={globalCharacter.race.name}
          image={globalCharacter.race.image}
          level={globalCharacter.level}
        />
      </div>

      <div className="battlefield__log">
        <MenuContainer hasScroll={true}>
          {isLoading ? (
            <Loader />
          ) : error ? (
            <h2>Erro no servidor, volte mais tarde!</h2>
          ) : battleLog.draw ? (
            <h2>A Batalha Empatou</h2>
          ) : (
            <div className={'battlefield__round'}>
              <h2> {battleLog.battleLogs[count].character.name}</h2>
              <p>O ataque causou {battleLog.battleLogs[count].damage} de dano</p>
              <p>{battleLog.battleLogs[count].critical ? 'Ataque foi critico' : 'não critou desta vez'}</p>
              {winner && <h2>{battleLog.winner ? 'Você venceu!' : 'Você Perdeu!'}</h2>}
            </div>
          )}
        </MenuContainer>
        <Button onClick={() => push('/battle')}> Voltar </Button>
      </div>
      <div className="battlefield__card-content">
        <CardCharacter
          name={enemyCharacter.name}
          raceName={enemyCharacter.race.name}
          image={enemyCharacter.race.image}
          level={enemyCharacter.level}
        />
      </div>
    </section>
  )
}
