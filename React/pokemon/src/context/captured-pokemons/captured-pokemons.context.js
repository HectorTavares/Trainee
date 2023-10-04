import createGlobalState from 'react-create-global-state'

const CAPTURED_POKEMONS_KEY = 'captured_pokemons'

const stringifyCapturedPokemons = localStorage.getItem(CAPTURED_POKEMONS_KEY)

const capturedPokemons = stringifyCapturedPokemons ? JSON.parse(stringifyCapturedPokemons) : []

const [_useGlobalCapturedPokemons, CapturedPokemonsProvider] = createGlobalState(capturedPokemons)

const useGlobalCapturedPokemons = () => {
  const [globalCapturedPokemons, _setGlobalCapturedPokemons] = _useGlobalCapturedPokemons()

  const setGlobalCapturedPokemons = newGlobalCapturedPokemon => {
    _setGlobalCapturedPokemons(newGlobalCapturedPokemon)
    localStorage.setItem(CAPTURED_POKEMONS_KEY, JSON.stringify(newGlobalCapturedPokemon))
  }

  return [globalCapturedPokemons, setGlobalCapturedPokemons]
}

export { useGlobalCapturedPokemons, CapturedPokemonsProvider }
