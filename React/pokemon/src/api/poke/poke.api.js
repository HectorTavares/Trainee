import { useMemo } from 'react'
import { useHttp } from '../_base/use-http'

export function usePokeApi() {
  const http = useHttp('https://hectortavares.github.io/Trainee/Conteudo/poke/')

  function getInitials() {
    return http.get('initial-pokemons.json')
  }

  function getAllPokemons() {
    return http.get('pokemons.json')
  }

  return useMemo(
    () => ({
      getInitials,
      getAllPokemons,
    }),
    []
  )
}
