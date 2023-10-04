import { useEffect, useState } from 'react'
import './in-battle.style.css'
import { randomItemFromArray, randomNumber, hitAttack } from '../../../core'

export function InBattle({ myPokemon, enemyPokemon, battleEnds, capturePokemon }) {
  const moveType = {
    ATTACK: 'attack',
    CAPTURE: 'capture',
    ESCAPE: 'escape',
  }
  const [myPokemonState, setMyPokemonState] = useState(myPokemon)
  const [enemyPokemonState, setEnemyPokemonState] = useState({ ...enemyPokemon, life: 100 })
  const [playerStart, setPlayerStart] = useState()
  const [battleStatus, setBattleStatus] = useState([true, '']) //array com bool e msg

  useEffect(() => {
    setPlayerStart(randomNumber(2) - 1)
  }, [])

  function myRound(moveType, attack, myPokemon, enemyPokemon, battleStatus) {
    let updatedMyPokemon = { ...myPokemon }
    let updatedEnemyPokemon = { ...enemyPokemon }
    let updatedBattleStatus = [...battleStatus]

    switch (moveType) {
      case 'attack':
        if (hitAttack()) {
          let updatedLife = updatedEnemyPokemon.life - attack.damage

          if (updatedLife < 1) {
            updatedLife = 0
            updatedBattleStatus = [false, 'Você venceu.']
          }
          updatedEnemyPokemon = { ...updatedEnemyPokemon, life: updatedLife }
        }

        break
      case 'escape':
        const updatedLife = Math.ceil(updatedMyPokemon.life / 2)
        updatedMyPokemon = { ...updatedMyPokemon, life: updatedLife }
        updatedBattleStatus = [false, 'Você escapou']

        break
      case 'capture':
        const captureChance = updatedEnemyPokemon.life + 15
        if (randomNumber(100) > captureChance) {
          capturePokemon(updatedEnemyPokemon.id)
          const capturePokemonText = 'Você capturou um ' + updatedEnemyPokemon.name
          updatedBattleStatus = [false, capturePokemonText]
        }
        break
      default:
        break
    }

    return {
      myPokemon: updatedMyPokemon,
      enemyPokemon: updatedEnemyPokemon,
      battleStatus: updatedBattleStatus,
    }
  }

  function enemyRound(myPokemon, enemyPokemon, battleStatus) {
    let updatedMyPokemon = { ...myPokemon }
    let updatedEnemyPokemon = { ...enemyPokemon }
    let updatedBattleStatus = [...battleStatus]

    const enemyAttack = randomItemFromArray(updatedEnemyPokemon.abilities)

    if (hitAttack()) {
      let updatedLife = updatedMyPokemon.life - enemyAttack.damage

      if (updatedLife < 1) {
        updatedLife = 0
        updatedBattleStatus = [false, 'Você Perdeu.']
      }

      updatedMyPokemon = { ...updatedMyPokemon, life: updatedLife }
    }

    return {
      myPokemon: updatedMyPokemon,
      enemyPokemon: updatedEnemyPokemon,
      battleStatus: updatedBattleStatus,
    }
  }

  function onMove(moveType, attack, myPokemon, enemyPokemon, battleStatus) {
    const myPokemonCopy = { ...myPokemon }
    const enemyPokemonCopy = { ...enemyPokemon }
    const battleStatusCopy = battleStatus

    let result = {
      myPokemon: myPokemonCopy,
      enemyPokemon: enemyPokemonCopy,
      battleStatus: battleStatusCopy,
    }

    if (playerStart) {
      result = myRound(moveType, attack, myPokemonCopy, enemyPokemonCopy, battleStatusCopy)

      if (result.battleStatus[0]) {
        result = enemyRound(result.myPokemon, result.enemyPokemon, result.battleStatus)
      }
    } else {
      result = enemyRound(myPokemonCopy, enemyPokemonCopy, battleStatusCopy)

      if (result.battleStatus[0]) {
        result = myRound(moveType, attack, result.myPokemon, result.enemyPokemon, result.battleStatus)
      }
    }

    setBattleStatus(result.battleStatus)
    setMyPokemonState(result.myPokemon)
    setEnemyPokemonState(result.enemyPokemon)
  }

  function onCaptureTry() {
    onMove(moveType.CAPTURE, null, myPokemonState, enemyPokemonState, battleStatus)
  }
  function onEscape() {
    onMove(moveType.ESCAPE, null, myPokemonState, enemyPokemonState, battleStatus)
  }
  function whenBattleEnds() {
    battleEnds(myPokemonState)
  }

  return (
    <div className="in-battle">
      {battleStatus[0] ? (
        <>
          <div className="in-battle__enemyPokemon">
            <img src={enemyPokemonState.sprites.front} alt={enemyPokemonState.name} />
            <h2>{enemyPokemonState.name}</h2>
            <h3>{enemyPokemonState.life}</h3>
          </div>
          <div className="in-battle__myPokemon">
            <img
              className="in-battle__pokemon-name"
              src={myPokemonState.sprites.back}
              alt={myPokemonState.name}
            />
            {myPokemonState.nickname ? <h2>{myPokemonState.nickname}</h2> : <h2>{myPokemonState.name}</h2>}
            <h3>{myPokemonState.life}</h3>
            <div className="in-battle_buttons">
              <div className="in-battle__abilities-buttons">
                {myPokemonState.abilities.map(ability => (
                  <button
                    className="ability-button"
                    key={ability.name}
                    onClick={() =>
                      onMove(moveType.ATTACK, ability, myPokemonState, enemyPokemonState, battleStatus)
                    }
                  >
                    {ability.name} ({ability.damage})
                  </button>
                ))}
              </div>
              <div className="in-battle__option-buttons">
                <button onClick={onCaptureTry}>Capturar</button>
                <button onClick={onEscape}>Fugir</button>
              </div>
            </div>
          </div>
        </>
      ) : (
        <div className="in-battle__feedback">
          <h2>{battleStatus[1]}</h2>
          <button onClick={whenBattleEnds}>Batalhar de novo</button>
        </div>
      )}
    </div>
  )
}
