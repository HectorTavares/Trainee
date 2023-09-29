import instrutores from './instrutores.json'
import { SuperArray } from '../src/super-array'

let INSTRUTORES

beforeEach(() => {
  INSTRUTORES = SuperArray(instrutores)
})


// describe('Exemplo de testes', () => {
//   it('Valor importado deve ser true', () => {
//     expect(true).toBeTruthy()
//   })
// })

describe('Push', () => {
  it('push deve adicionar um novo instrutor ao meu super array', () => {
    const instrutoresAUX = SuperArray(instrutores)

    const novoInstrutor = {
      nome: "Héctor Tavares",
      dandoAula: true,
    }



    instrutoresAUX.push(novoInstrutor)
    expect(instrutoresAUX.itens[8]).toEqual(novoInstrutor)
  })
})

describe('ForEach', () => {
  it('forEach deve passar por todos os instrutores e chamando o callback esperado', () => {
    const array = SuperArray()

    INSTRUTORES.forEach(item => {
      array.push(item)
    })

    expect(array.itens).toEqual(INSTRUTORES.itens)
  })

  describe('filter', () => {
    it('filter deve retornar um novo array apenas com os instrutores que estão dando aula', () => {
      const valorEsperado = [{ "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true },
      { "nome": "William Cardozo", "dandoAula": true },]
      const valorArmazenado = INSTRUTORES.filter(item => {
        return item.dandoAula == true
      })
      expect(valorArmazenado).toEqual(valorEsperado)
    })
  })

  describe('Map', () => {
    it('map deve retornar um novo array com todos instrutores dando aula', () => {
      const valorEsperado = [
        { "nome": "Fabio Junqueira", "dandoAula": true },
        { "nome": "Pablo Oliveira", "dandoAula": true },
        { "nome": "Sergio Andrade", "dandoAula": true },
        { "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true },
        { "nome": "William Cardozo", "dandoAula": true },
        { "nome": "Diandra Rocha", "dandoAula": true },
        { "nome": "Rafael Zorzanelo", "dandoAula": true },
        { "nome": "Victor Herzog Damke", "dandoAula": true },

      ]

      const valorArmazenado = INSTRUTORES.map(item => {
        item.dandoAula = true;
        return item
      })
      expect(valorArmazenado).toEqual(valorEsperado)
    })

    it('map deve retornar um novo array com o numero de nomes que o instrutor tem', () => {
      const valorEsperado = [2,2,2,3,2,2,2,3];
      const valorArmazenado = INSTRUTORES.map(item =>{

        const aux = item['nome']
        const resultado  = aux.split(" ")
        return resultado.length

      });

      expect(valorArmazenado).toEqual(valorEsperado)
    })
  })

  describe('find', () => {
    it('find deve retornar o primeiro instrutor que está dando aula', () => {
      const valorEsperado = { "nome": "Gustavo Büttenbender Rodrigues", "dandoAula": true }
      const valorArmazenado = INSTRUTORES.find(item => {
        return item.nome == "Gustavo Büttenbender Rodrigues"
      })
      expect(valorArmazenado).toEqual(valorEsperado)

    })
    it('find deve retorar undefined quando não achar nada', () => {
      const valorEsperado = undefined
      const valorArmazenado = INSTRUTORES.find(item => {
        return item.nome == "Joao Doria"
      })
      expect(valorEsperado).toEqual(valorArmazenado)
    })
  })

  describe('reduce', () => {
    it('reduce deve retornar o total de letras no nome dos instrutores', () => {
      const valorEsperado = 14 + 13 + 13 + 29 + 14 + 12 + 15 + 18;
      const valorArmazenado = INSTRUTORES.reduce((acumulador, item) => {
        const resultado = item['nome']
        const resul = resultado.replace(" ", "")

        return acumulador + resul.length
      }, 0)

      expect(valorArmazenado).toBe(valorEsperado)
    })

    it('reduce deve retornar um boolean se todos os instrutores estão dando aula', () => {
      const valorEsperado = false;
      const valorArmazenado = INSTRUTORES.reduce((acumulador, item) => {
        return acumulador + item['dandoAula']
      }, 0)

      expect(valorArmazenado).notToBeTruthy
    })
  })
})
