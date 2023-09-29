import { criarTreinador, capturarPokemon, subirNivelPokemons, evoluirPokemonNecessario } from "../src/index"


describe('Exemplo de testes', () => {
  it('Treinador será criado com nome correto', () => {
    const treinador = criarTreinador("Jobson Pereira", 41, 7)

    const resultadoEsperado = "Jobson Pereira"
    const resultadoArmazenado = treinador["nome"]

    expect(resultadoArmazenado).toBe(resultadoEsperado)
  })

  it('Treinador será criado com a idade correta', () => {
    const treinador = criarTreinador("Héctor", 18, 1)

    const resultadoEsperado = 18
    const resultadoArmazenado = treinador["idade"]

    expect(resultadoArmazenado).toBe(resultadoEsperado)
  })

  it('Treinador será criado com o pokemon inicial correto', () => {
    const treinador = criarTreinador("Ash", 10, 4)

    const resultadoEsperado = 4
    const resultadoArmazenado = treinador["pokemonInicial"]

    expect(resultadoArmazenado).toBe(resultadoEsperado)
  })

  it('Treinador terá seus pokemons atualizados após nova captura', () => {
    const treinador2 = criarTreinador("Red", 41, 7)
    let treinadorAtualizado = capturarPokemon(treinador2, 1)
    treinadorAtualizado = capturarPokemon(treinadorAtualizado, 5)

    const resultadoEsperado = [
      {
        "id": 7,
        "nome": "Bulbasaur",
        "poderAtaque": 1,
        "levelInicial": 3,
        "evolucao": {
        "level": 5,
        "id": 8
        }
      },

      {
        "id": 1,
        "nome": "Squirtle",
        "poderAtaque": 1,
        "levelInicial": 2,
        "evolucao": {
        "level": 5,
        "id": 2
        } 
      },

      {
        "id": 5,
        "nome": "Quilava",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 6
        }
      }, 
    ]
    const resultadoArmazenado = treinadorAtualizado["pokemons"]

    expect(resultadoEsperado).toEqual(resultadoArmazenado)
  })

  it('Deve subir o nível do pokemon corretamente', () => {
    const treinador = criarTreinador("Tavares", 41, 7, [
      {
        "id": 1,
        "nome": "Squirtle",
        "poderAtaque": 1,
        "levelInicial": 1,
        "evolucao": {
        "level": 5,
        "id": 2
        } 
      },
      {
        "id": 4,
        "nome": "Cyndaquil",
        "poderAtaque": 1,
        "levelInicial": 1,
        "evolucao": {
        "level": 5,
        "id": 5
        }
      },
      {
        "id": 8,
        "nome": "Ivysaur",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 9
        }
      },
    ])

    const resultadoEsperado = [
      {
        "id": 1,
        "nome": "Squirtle",
        "poderAtaque": 1,
        "levelInicial": 2,
        "evolucao": {
        "level": 5,
        "id": 2
        } 
      },
      {
        "id": 4,
        "nome": "Cyndaquil",
        "poderAtaque": 1,
        "levelInicial": 2,
        "evolucao": {
        "level": 5,
        "id": 5
        }
      },
      {
        "id": 8,
        "nome": "Ivysaur",
        "poderAtaque": 10,
        "levelInicial": 6,
        "evolucao": {
        "level": 10,
        "id": 9
        }
      },
    ]
    const resultadoArmazenado = subirNivelPokemons(treinador)

    expect(resultadoEsperado).toEqual(resultadoArmazenado)
  })

  it('Deve evoluir pokemon ao atingir o nível necessário', () => {
    const treinador = criarTreinador("Tavares", 41, 7, [
      {
        "id": 1,
        "nome": "Squirtle",
        "poderAtaque": 1,
        "levelInicial": 5,
        "evolucao": {
        "level": 5,
        "id": 2
        } 
      },
      {
        "id": 4,
        "nome": "Cyndaquil",
        "poderAtaque": 1,
        "levelInicial": 3,
        "evolucao": {
        "level": 5,
        "id": 5
        }
      },
      {
        "id": 8,
        "nome": "Ivysaur",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 9
        }
      },
    ])

    const resultadoEsperado = [
      {
        "id": 2,
        "nome": "Wartortle",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 3
        } 
      },
      {
        "id": 4,
        "nome": "Cyndaquil",
        "poderAtaque": 1,
        "levelInicial": 3,
        "evolucao": {
        "level": 5,
        "id": 5
        }
      },
      {
        "id": 8,
        "nome": "Ivysaur",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 9
        }
      },
    ]
    const resultadoArmazenado = evoluirPokemonNecessario(treinador["pokemons"])

    expect(resultadoEsperado).toEqual(resultadoArmazenado)
  })

  it('Não deve evoluir pokemon caso não possua o level necessário', () => {
    const treinador = criarTreinador("Tavares", 41, 7, [
      {
        "id": 1,
        "nome": "Squirtle",
        "poderAtaque": 1,
        "levelInicial": 4,
        "evolucao": {
        "level": 5,
        "id": 2
        } 
      },
      {
        "id": 4,
        "nome": "Cyndaquil",
        "poderAtaque": 1,
        "levelInicial": 3,
        "evolucao": {
        "level": 5,
        "id": 5
        }
      },
      {
        "id": 8,
        "nome": "Ivysaur",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 9
        }
      },
    ])

    const resultadoEsperado = [
      {
        "id": 1,
        "nome": "Squirtle",
        "poderAtaque": 1,
        "levelInicial": 4,
        "evolucao": {
        "level": 5,
        "id": 2
        } 
      },
      {
        "id": 4,
        "nome": "Cyndaquil",
        "poderAtaque": 1,
        "levelInicial": 3,
        "evolucao": {
        "level": 5,
        "id": 5
        }
      },
      {
        "id": 8,
        "nome": "Ivysaur",
        "poderAtaque": 10,
        "levelInicial": 5,
        "evolucao": {
        "level": 10,
        "id": 9
        }
      },
    ]
    const resultadoArmazenado = evoluirPokemonNecessario(treinador["pokemons"])

    expect(resultadoEsperado).toEqual(resultadoArmazenado)
  })
})