import { useEffect, useState } from 'react'
import { useHistory, useParams, useLocation } from 'react-router'
import { useGlobalCapturedPokemons } from '../../../context'
import { ROUTES } from '../../../routes'
import { ReturnButton } from '../../components'
import './pokemon-info.style.css'

export function PokemonInfoScreen() {
  const [globalCapturedPokemons, setGlobalCapturedPokemons] = useGlobalCapturedPokemons()
  const { push } = useHistory()
  const { pokemonId } = useParams()
  const { state } = useLocation()
  const [selectedPokemon, setSelectedPokemon] = useState(
    globalCapturedPokemons.find(pokemon => pokemon.id === parseInt(pokemonId))
  )
  const [inputValue, setInputValue] = useState()

  function onReturnToPokedex() {
    push(ROUTES.POKEDEX)
  }

  useEffect(() => {
    if (!state?.fromPokedex) {
      push(ROUTES.POKEDEX)
    }
  }, [state, push])

  function handleChange(event) {
    const { value } = event.target

    const formatedValue = value.trim().toLowerCase()

    setInputValue(formatedValue)
  }

  function handleSubmit(event) {
    event.preventDefault()

    const updatedPokemon = { ...selectedPokemon, nickname: inputValue }

    const capturedPokemons = globalCapturedPokemons.filter(
      capturedPokemon => capturedPokemon.id !== selectedPokemon.id
    )

    const updatedCapturedPokemons = [...capturedPokemons, updatedPokemon]

    setSelectedPokemon(updatedPokemon)
    setGlobalCapturedPokemons(updatedCapturedPokemons)
  }

  return (
    <section className="pokemonInfoScreen">
      <header className="pokemonInfoScreen__header">
        <ReturnButton onReturn={onReturnToPokedex} />
      </header>
      <div className="pokemonInfoScreen_content">
        <div className="pokemonInfoScreen__informations">
          <h2>Dados do pokemon</h2>
          <ul>
            <li className="pokemonInfoScreen__infortation">Nome: {selectedPokemon.name}</li>
            <li className="pokemonInfoScreen__infortation">Apelido: {selectedPokemon.nickname}</li>
            <li className="pokemonInfoScreen__infortation">Vida Atual: {selectedPokemon.life}</li>
          </ul>
          <h2>Habilidades</h2>
          <ul>
            {selectedPokemon.abilities.map(ability => (
              <li key={ability.name} className="pokemonInfoScreen__infortation">
                {ability.name} ({ability.damage})
              </li>
            ))}
          </ul>
          <h2>Sprites</h2>
          <ul>
            <li className="pokemonInfoScreen__infortation">
              <img src={selectedPokemon.sprites.front} alt="" />
            </li>
            <li className="pokemonInfoScreen__infortation">
              <img src={selectedPokemon.sprites.back} alt="" />
            </li>
          </ul>
          <div>
            <form onSubmit={handleSubmit} className="pokemonInfoScreen__form">
              <label>
                Novo Apelido :{' '}
                <input
                  value={inputValue}
                  onChange={handleChange}
                  autoComplete="off"
                  className="pokemonInfoScreen__input"
                  placeholder="apelido"
                  name="nickname"
                />
              </label>
              <button className="pokemonInfoScreen__button"></button>
            </form>
          </div>
        </div>
        <div className="pokemonInfoScreen__banner">
          {selectedPokemon.nickname ? (
            <>
              <h2>{selectedPokemon.nickname}</h2> <p>{selectedPokemon.name}</p>
            </>
          ) : (
            <h2>{selectedPokemon.name}</h2>
          )}
          <img src={selectedPokemon.image} alt={selectedPokemon.name} />
        </div>
      </div>
    </section>
  )
}
