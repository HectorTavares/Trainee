import { PokemonCard } from '../../components'
import './initial-pokemons.style.css'
import { usePokeApi } from '../../../api/poke/poke.api'
import { useState, useEffect } from 'react'
import { useGlobalCapturedPokemons } from '../../../context/index'
import { formatPokemon } from '../../../core/format-pokemon'
import { useHistory } from 'react-router'
import { ROUTES } from '../../../routes'

export function InitialPokemonsScreen() {
  const pokeAPi = usePokeApi()
  const [initials, setInitials] = useState([])
  const [allPokemons, setAllPokemons] = useState([])
  const [, setGlobalCapturedPokemons] = useGlobalCapturedPokemons()
  const { push } = useHistory()

  useEffect(() => {
    async function getInitials() {
      try {
        const response = await pokeAPi.getInitials()
        setInitials(response)
      } catch {}
    }
    getInitials()
  }, [pokeAPi])

  useEffect(() => {
    async function getAllPokemons() {
      try {
        const response = await pokeAPi.getAllPokemons()
        setAllPokemons(response)
      } catch {}
    }
    getAllPokemons()
  }, [pokeAPi])

  function onChooseInitialPokemon(choosedPokemonId) {
    const choosedPokemon = allPokemons.find(pokemon => pokemon.id === choosedPokemonId)

    const formatedChoosedPokemon = [formatPokemon(choosedPokemon)]
    setGlobalCapturedPokemons(formatedChoosedPokemon)
    localStorage.setItem('initial_was_chosen', true)
    push(ROUTES.MENU)
  }

  return (
    <section className="initialPokemonsScreen">
      <h1 className="initialPokemonsScreen__title">Escolha um dos iniciais</h1>
      <div className="initialPokemonsScreen__content">
        {initials.map(initial => (
          <PokemonCard
            className="teste"
            key={initial.id}
            id={initial.id}
            name={initial.name}
            image={initial.image}
            onClick={onChooseInitialPokemon}
          />
        ))}
      </div>
    </section>
  )
}
