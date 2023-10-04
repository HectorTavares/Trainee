import './pokemon-list.style.css'
import { PokemonCard } from '../../../components'

export function PokemonList({ type, currentTime, tittle, pokemons, onClick }) {
  function whenToDisable(type, currentTime, pokemon) {
    if (type === 'myPokemons') {
      return pokemon.life === 100
    } else if (type === 'pokecenterPokemons') {
      return currentTime <= pokemon.timeToHeal
    }
  }

  return (
    <div className="pokelist">
      <h2>{tittle}</h2>
      <div className="pokelist__pokemons">
        {pokemons?.map(pokemon => {
          const disabled = whenToDisable(type, currentTime, pokemon)
          return (
            <div key={pokemon.id} className="pokelist__card">
              <PokemonCard
                key={pokemon.id}
                id={pokemon.id}
                name={pokemon.name}
                type="small"
                image={pokemon.image}
                life={pokemon.life}
                onClick={onClick}
                disabled={disabled}
                nickname={pokemon.nickname}
              />
              {type === 'pokecenterPokemons' && pokemon.timeToHeal - currentTime > 0 && (
                <h2 className="pokelist__pokemons-status">{`Espera: ${
                  pokemon.timeToHeal - currentTime
                } s`}</h2>
              )}
              {type === 'pokecenterPokemons' && pokemon.timeToHeal - currentTime < 0 && (
                <h2 className="pokelist__pokemons-status">Pronto</h2>
              )}
            </div>
          )
        })}
      </div>
    </div>
  )
}

PokemonList.defaultProps = {
  currentTime: null,
}
