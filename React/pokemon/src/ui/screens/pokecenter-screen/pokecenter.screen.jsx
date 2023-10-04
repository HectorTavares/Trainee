import './pokecenter.style.css'
import { ReturnButton } from '../../components/index'
import { useGlobalCapturedPokemons } from '../../../context'
import { useEffect, useState } from 'react'
import { PokemonList } from './pokemon-list/pokemon-list.component'
import { useHistory } from 'react-router'

export function PokecenterScreen() {
  const [globalCapturedPokemons, setGlobalCapturedPokemons] = useGlobalCapturedPokemons()
  const [pokecenterPokemons, setPokecenterPokemons] = useState([]) //abstrair a logica pra prop
  const [currentTime, setCurrentTime] = useState()
  const { push } = useHistory()
  const POKELIST_TYPE = {
    MY_POKEMONS: 'myPokemons',
    POKECENTER_POKEMONS: 'pokecenterPokemons',
  }

  useEffect(() => {
    const pkm = localStorage.getItem('pokecenter_pokemons')
    setPokecenterPokemons(JSON.parse(pkm))
  }, [])

  useEffect(() => {
    localStorage.setItem('pokecenter_pokemons', JSON.stringify(pokecenterPokemons))
  }, [pokecenterPokemons])

  useEffect(() => {
    setCurrentTime(Math.round(Date.now() / 1000))
  }, [])

  useEffect(() => {
    setTimeout(() => {
      setCurrentTime(Math.round(Date.now() / 1000))
    }, 1000)
  }, [currentTime])

  function onWithdraw(pokemonId) {
    const pokemonToWithdraw = pokecenterPokemons.find(pokemon => pokemon.id === pokemonId)
    const updatedPokecenterPokemons = pokecenterPokemons.filter(pokemon => pokemon.id !== pokemonId)

    const updatedPokemonToWithdraw = { ...pokemonToWithdraw, life: 100 }

    delete updatedPokemonToWithdraw.timeToHeal

    const updatedCapturedPokemons = [...globalCapturedPokemons, updatedPokemonToWithdraw]

    setGlobalCapturedPokemons(updatedCapturedPokemons)
    setPokecenterPokemons(updatedPokecenterPokemons)
  }

  function onReturnToMenu() {
    push('/')
  }

  function onSend(pokemonId) {
    const pokemon = globalCapturedPokemons.find(pokemon => pokemon.id === pokemonId)
    const updatedCapturedPokemons = globalCapturedPokemons.filter(pokemon => pokemon.id !== pokemonId)

    const timeToHeal = Math.round((100 - pokemon.life) * 5 + currentTime)
    const pokemonToSend = { ...pokemon, timeToHeal: timeToHeal }
    if (pokecenterPokemons) {
      setPokecenterPokemons([...pokecenterPokemons, pokemonToSend])
    } else {
      setPokecenterPokemons([pokemonToSend])
    }

    setGlobalCapturedPokemons(updatedCapturedPokemons)
  }
  return (
    <section className="pokecenterScreen">
      <header className="pokecenterScreen__header">
        <ReturnButton onReturn={onReturnToMenu} />
        <h1 className="pokecenterScreen__tittle">POKECENTER</h1>
      </header>
      <div className="pokecenterScreen__content">
        <PokemonList
          type={POKELIST_TYPE.MY_POKEMONS}
          tittle="MEUS POKEMONS"
          pokemons={globalCapturedPokemons}
          onClick={onSend}
        />
        <PokemonList
          type={POKELIST_TYPE.POKECENTER_POKEMONS}
          currentTime={currentTime}
          tittle="Pokemons sendo cuidados"
          pokemons={pokecenterPokemons}
          onClick={onWithdraw}
        />
      </div>
    </section>
  )
}
