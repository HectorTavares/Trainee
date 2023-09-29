// inicie por aqui
import listaPokemons from "../__tests__/pokemons"

function getPokemonById(idPokemon) {
    return listaPokemons[idPokemon - 1]
}


export function criarTreinador(nome, idade, idPokemonInicial, pokemons = [getPokemonById(idPokemonInicial)]) {
    const treinador = {
        nome: nome,
        idade: idade,
        pokemonInicial: idPokemonInicial,
        pokemons: [...pokemons],
    }

    return treinador
}

export function capturarPokemon(treinador, idPokemonCapturado) {
    const treinadorAtualizado = treinador;

    treinadorAtualizado["pokemons"] = subirNivelPokemons(treinador)
    treinadorAtualizado["pokemons"].push(getPokemonById(idPokemonCapturado))

    return treinadorAtualizado
}

export function subirNivelPokemons(treinador) {
    treinador["pokemons"].forEach(pokemon => {
        pokemon["levelInicial"] += 1    
    })

    const pokemonsAtualizados = evoluirPokemonNecessario(treinador["pokemons"])

    return pokemonsAtualizados
}

export function evoluirPokemonNecessario(pokemonsAdquiridos) {
    for (let i = 0; i < pokemonsAdquiridos.length; i++) {
        const levelNecessarioParaEvoluir = pokemonsAdquiridos[i]["evolucao"]["level"]
        const levelAtual = pokemonsAdquiridos[i]["levelInicial"]
        const idPokemonEvolucao = pokemonsAdquiridos[i]["evolucao"]["id"]

        if(levelAtual == levelNecessarioParaEvoluir) {
            pokemonsAdquiridos.splice(i, 1, getPokemonById(idPokemonEvolucao))
        }
    }

    return pokemonsAdquiridos
}