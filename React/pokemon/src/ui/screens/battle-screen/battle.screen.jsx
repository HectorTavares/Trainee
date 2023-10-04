import './battle.style.css'
import { useEffect, useState } from 'react'
import { PokemonCard, ReturnButton } from '../../components/'
import { useGlobalCapturedPokemons } from '../../../context'
import { usePokeApi } from '../../../api/poke/poke.api'
import { formatPokemon, randomItemFromArray } from '../../../core'
import { InBattle } from '../../components/in-battle/in-battle.component'
import { useHistory } from 'react-router'

export function BattleScreen() {
  const [choosedPokemon, setChoosedPokemon] = useState(null)
  const [enemyPokemon, setEnemyPokemon] = useState(null)
  const [globalCapturedPokemons, setGlobalCapturedPokemons] = useGlobalCapturedPokemons()
  const [allPokemons, setAllPokemons] = useState([])
  const pokeApi = usePokeApi()
  const { push } = useHistory()

  function onReturnToMenu() {
    push('/')
  }

  useEffect(() => {
    async function getAllPokemons() {
      try {
        const response = await pokeApi.getAllPokemons()
        setAllPokemons(response)
      } catch {}
    }
    getAllPokemons()
  }, [pokeApi])

  function onChoosePokemon(choosedPokemonId) {
    const newChoosedPokemon = globalCapturedPokemons.find(pokemon => pokemon.id === choosedPokemonId)

    setChoosedPokemon(newChoosedPokemon)
  }

  function onFindEnemy() {
    const enemy = randomItemFromArray(allPokemons)

    setEnemyPokemon(enemy)
  }
  function whenBattleEnds(updatedMyPokemon) {
    const pokemonsWithoutUpdated = globalCapturedPokemons.filter(
      pokemon => pokemon.id !== updatedMyPokemon.id
    )

    const updatedCapturedPokemons = [...pokemonsWithoutUpdated, updatedMyPokemon]

    setGlobalCapturedPokemons(updatedCapturedPokemons)

    setChoosedPokemon(null)
    setEnemyPokemon(null)
  }

  function capturePokemon(capturedPokemonId) {
    const newPokemon = allPokemons.find(pokemon => pokemon.id === capturedPokemonId)

    const newFormatedPokemon = formatPokemon(newPokemon)
    const updatedCapturedPokemons = [...globalCapturedPokemons, newFormatedPokemon]
    setGlobalCapturedPokemons(updatedCapturedPokemons)
  }

  return (
    <section className="battleScreen">
      <header className="battleScreen__header">
        <ReturnButton disabled={!!choosedPokemon && !!enemyPokemon} onReturn={onReturnToMenu} />
        <h1 className="battleScreen__title">BATALHA</h1>
      </header>
      <div className="battleScreen__content">
        <div className="battleScreen__options">
          <h2>MEUS POKEMONS</h2>
          <div className="battleScreen__myPokemons">
            {globalCapturedPokemons.map(pokemon => (
              <PokemonCard
                key={pokemon.id}
                id={pokemon.id}
                name={pokemon.name}
                image={pokemon.image}
                life={pokemon.life}
                disabled={!pokemon.life}
                onClick={onChoosePokemon}
                type="small"
                nickname={pokemon.nickname}
                isChoosed={choosedPokemon?.id === pokemon.id}
              />
            ))}
          </div>

          <button className="battleScreen__findEnemy" disabled={!!enemyPokemon} onClick={onFindEnemy}>
            PROCURAR ADVERSARIO
          </button>
        </div>
        <div className="battleScreen__battle">
          {choosedPokemon !== null && enemyPokemon !== null ? (
            <InBattle
              myPokemon={choosedPokemon}
              enemyPokemon={enemyPokemon}
              battleEnds={whenBattleEnds}
              capturePokemon={capturePokemon}
            />
          ) : (
            <h2 className="battleScreen__title">Escolha seu pokemon e procure um adversario</h2>
          )}
        </div>
      </div>
    </section>
  )
}
