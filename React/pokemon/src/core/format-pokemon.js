export function formatPokemon(pokemon) {
  const stringifyActualId = localStorage.getItem('id')
  const actualId = (stringifyActualId ? JSON.parse(stringifyActualId) : 0) + 1

  const formatedPokemon = { ...pokemon, id: actualId, speciesId: pokemon.id, life: 100 }
  localStorage.setItem('id', actualId)

  return formatedPokemon
}
