import './pokedex.style.css'
import { PokemonCard, ReturnButton } from '../../components'
import { useGlobalCapturedPokemons } from '../../../context'
import { useHistory } from 'react-router'
import { useState } from 'react'
import { Link } from 'react-router-dom'

export function PokedexScreen() {
  const { push } = useHistory()
  const [globalCapturedPokemons] = useGlobalCapturedPokemons()
  const [searchInput, setSearchInput] = useState('')
  const [foundPokemons, setFoundPokemons] = useState([])

  function onReturnToMenu() {
    push('/')
  }

  function handleChange(changeEvent) {
    const { value } = changeEvent.target
    const formatedValue = value.trim().toLowerCase()
    const result = globalCapturedPokemons.filter(
      pokemon =>
        pokemon.name.toLowerCase().includes(formatedValue) ||
        pokemon?.nickname?.toLowerCase()?.includes(formatedValue)
    )

    setFoundPokemons(result)
    setSearchInput(formatedValue)
  }

  return (
    <section className="pokedexScreen">
      <header className="pokedexScreen__header">
        <ReturnButton onReturn={onReturnToMenu} />
        <h1 className="pokedexScreen__title">POKÉDEX</h1>
      </header>
      <div className="pokedexScreen__content">
        <div className="pokedexScreen__content-header">
          <form className="pokedexScreen__form">
            <input
              value={searchInput}
              onChange={handleChange}
              autoComplete="off"
              className="pokedexScreen__search-input"
              placeholder="nome/apelido"
              name="name/nickname"
            />
          </form>
        </div>
        {searchInput.length !== 0
          ? foundPokemons.map(pokemon => (
              <Link
                to={{
                  pathname: `/pokemon/${pokemon.id}`,
                  state: { fromPokedex: true },
                }}
              >
                <PokemonCard
                  key={pokemon.id}
                  id={pokemon.id}
                  name={pokemon.name}
                  image={pokemon.image}
                  life={pokemon.life}
                  onClickDisable={true}
                  nickname={pokemon.nickname}
                />
              </Link>
            ))
          : globalCapturedPokemons.map(pokemon => (
              <Link
                to={{
                  pathname: `/pokemon/${pokemon.id}`,
                  state: { fromPokedex: true },
                }}
              >
                <PokemonCard
                  key={pokemon.id}
                  id={pokemon.id}
                  name={pokemon.name}
                  image={pokemon.image}
                  life={pokemon.life}
                  onClickDisable={true}
                  nickname={pokemon.nickname}
                />
              </Link>
            ))}
      </div>
    </section>
  )
}
