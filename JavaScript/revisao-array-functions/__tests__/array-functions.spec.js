import * as arrayFunction from '../src/index'

describe('exercicio01', () => {
  //
  // Recebe um array de números inteiros
  // Deve retornar apenas os números pares
  //

  it('Recebendo de 1 a 10', () => {
    const entrada = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    const retornoEsperado = [2, 4, 6, 8, 10]

    const retorno = arrayFunction.exercicio01(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo apenas ímpares', () => {
    const entrada = [3, 15, 21, 7]
    const retornoEsperado = []

    const retorno = arrayFunction.exercicio01(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo de -3 a 3', () => {
    const entrada = [-3, -2, -1, 0, 1, 2, 3]
    const retornoEsperado = [-2, 0, 2]

    const retorno = arrayFunction.exercicio01(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio02', () => {
  //
  // Recebe um array de números inteiros
  // Deve validar se existe algum número maior que 10
  //

  it('Recebendo apenas menores', () => {
    const entrada = [1, 2, -30, 0, 9, 1]

    const retorno = arrayFunction.exercicio02(entrada)

    expect(retorno).toEqual(false)
  })

  it('Recebendo até iguais', () => {
    const entrada = [10, -10, 0, -22]

    const retorno = arrayFunction.exercicio02(entrada)

    expect(retorno).toEqual(false)
  })

  it('Recebendo maiores e menores', () => {
    const entrada = [-30, 2, 14]

    const retorno = arrayFunction.exercicio02(entrada)

    expect(retorno).toEqual(true)
  })
})

describe('exercicio03', () => {
  //
  // Recebe um array de números inteiros
  // Deve validar se todos são ímpares
  //

  it('Recebendo apenas pares', () => {
    const entrada = [2, 4, 6, 8, -2]

    const retorno = arrayFunction.exercicio03(entrada)

    expect(retorno).toEqual(false)
  })

  it('Recebendo apenas ímpares', () => {
    const entrada = [1, -1, 9, -99, 7]

    const retorno = arrayFunction.exercicio03(entrada)

    expect(retorno).toEqual(true)
  })

  it('Recebendo ímpares e pares', () => {
    const entrada = [1, 2, 3, 4, 5, 6]

    const retorno = arrayFunction.exercicio03(entrada)

    expect(retorno).toEqual(false)
  })
})

describe('exercicio04', () => {
  //
  // Recebe um array de números inteiros
  // Deve retornar o quadrado deles
  //

  it('Recebendo negativo, positivo e zero', () => {
    const entrada = [2, 4, 6, 8, -2, 0, 99]
    const retornoEsperado = [4, 16, 36, 64, 4, 0, 9801]

    const retorno = arrayFunction.exercicio04(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio05', () => {
  //
  // Recebe um array de números inteiros
  // Deve retornar a soma de todos eles
  //

  it('Recebendo números variados', () => {
    const entrada = [0, 2, 4, 6, 8, -2, 0, 10]
    const retornoEsperado = 28

    const retorno = arrayFunction.exercicio05(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo zero e negativos', () => {
    const entrada = [0, 0, -2, 0, -333]
    const retornoEsperado = -335

    const retorno = arrayFunction.exercicio05(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio06', () => {
  //
  // Recebe um array de números inteiros
  // Deve retornar os números ordenados de forma decrescente
  //

  it('Recebendo números variados', () => {
    const entrada = [0, 2, 4, 6, 8, -2, 10]
    const retornoEsperado = [10, 8, 6, 4, 2, 0, -2]

    const retorno = arrayFunction.exercicio06(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo com valores repetidos', () => {
    const entrada = [0, 0, -2, 0, -333, -15]
    const retornoEsperado = [0, 0, 0, -2, -15, -333]

    const retorno = arrayFunction.exercicio06(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio07', () => {
  //
  // Recebe um array de números inteiros
  // Deve retornar o primeiro que for menor que 100
  //

  it('Recebendo mais de um menor', () => {
    const entrada = [100, 101, 99, 98, 102]
    const retornoEsperado = 99

    const retorno = arrayFunction.exercicio07(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo apenas maiores e iguais', () => {
    const entrada = [222, 333, 111, 100, 6543]

    const retorno = arrayFunction.exercicio07(entrada)

    expect(retorno).toBeUndefined()
  })

  it('Recebendo apenas um menor', () => {
    const entrada = [100, 101, 98, 102, 1000]
    const retornoEsperado = 98

    const retorno = arrayFunction.exercicio07(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

const instrutoresSimples = [
  { nome: 'murillo', sobrenome: 'peteffi' },
  { nome: 'camille', sobrenome: 'grings' },
  { nome: 'william', sobrenome: 'cardozo' },
  { nome: 'gustavo', sobrenome: 'buttenbender' },
  { nome: 'emerson', sobrenome: 'pisoni' },
  { nome: 'luan', sobrenome: 'winck' },
]

describe('exercicio08', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar apenas os que tenham uma determinada letra no nome
  //

  it('Recebendo letra que apenas um tem', () => {
    const letra = 't'
    const arrayDeEntrada = [...instrutoresSimples]
    const retornoEsperado = [{ nome: 'gustavo', sobrenome: 'buttenbender' }]

    const retorno = arrayFunction.exercicio08(arrayDeEntrada, letra)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo letra que só tem nos sobrenomes', () => {
    const letra = 'z'
    const arrayDeEntrada = [...instrutoresSimples]
    const retornoEsperado = []

    const retorno = arrayFunction.exercicio08(arrayDeEntrada, letra)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo letra que tem no início e no meio', () => {
    const letra = 'l'
    const arrayDeEntrada = [...instrutoresSimples]
    const retornoEsperado = [
      { nome: 'murillo', sobrenome: 'peteffi' },
      { nome: 'camille', sobrenome: 'grings' },
      { nome: 'william', sobrenome: 'cardozo' },
      { nome: 'luan', sobrenome: 'winck' },
    ]

    const retorno = arrayFunction.exercicio08(arrayDeEntrada, letra)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio09', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar uma lista com as iniciais dos sobrenomes
  //

  it('Recebendo todos instrutores', () => {
    const entrada = [...instrutoresSimples]
    const retornoEsperado = ['p', 'g', 'c', 'b', 'p', 'w']

    const retorno = arrayFunction.exercicio09(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo lista com apenas um', () => {
    const entrada = [{ nome: 'oliver', sobrenome: 'tsubasa' }]
    const retornoEsperado = ['t']

    const retorno = arrayFunction.exercicio09(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio10', () => {
  //
  // Recebe um array de instrutores
  // Deve validar se algum nome ou sobrenome começa com a letra informada
  //

  it('Recebendo letra que tem no sobrenome', () => {
    const letra = 'p'
    const arrayDeEntrada = [...instrutoresSimples]

    const retorno = arrayFunction.exercicio10(arrayDeEntrada, letra)

    expect(retorno).toEqual(true)
  })

  it('Recebendo letra que tem no nome', () => {
    const letra = 'h'
    const arrayDeEntrada = [...instrutoresSimples, { nome: 'harry', sobrenome: 'potter' }]

    const retorno = arrayFunction.exercicio10(arrayDeEntrada, letra)

    expect(retorno).toEqual(true)
  })

  it('Recebendo letra que tem no nome e sobrenome', () => {
    const letra = 'c'
    const arrayDeEntrada = [...instrutoresSimples]

    const retorno = arrayFunction.exercicio10(arrayDeEntrada, letra)

    expect(retorno).toEqual(true)
  })

  it('Recebendo letra que só tem no meio dos nomes', () => {
    const letra = 's'
    const arrayDeEntrada = [...instrutoresSimples]

    const retorno = arrayFunction.exercicio10(arrayDeEntrada, letra)

    expect(retorno).toEqual(false)
  })
})

describe('exercicio11', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar ordenados por sobrenome alfabeticamente
  //

  it('Recebendo instrutores variados', () => {
    const entrada = [
      { nome: 'william', sobrenome: 'cardozo' },
      { nome: 'gustavo', sobrenome: 'buttenbender' },
      { nome: 'harry', sobrenome: 'potter' },
    ]
    const retornoEsperado = [
      { nome: 'gustavo', sobrenome: 'buttenbender' },
      { nome: 'william', sobrenome: 'cardozo' },
      { nome: 'harry', sobrenome: 'potter' },
    ]

    const retorno = arrayFunction.exercicio11(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio12', () => {
  //
  // Recebe um array de instrutores
  // Deve validar se todos contém determinada letra no nome ou no sobrenome
  //

  it('Recebendo letra que tem nos sobrenomes', () => {
    const letra = 'i'
    const arrayDeEntrada = [
      { nome: 'camille', sobrenome: 'grings' },
      { nome: 'luan', sobrenome: 'winck' },
      { nome: 'murillo', sobrenome: 'peteffi' },
      { nome: 'emerson', sobrenome: 'pisoni' },
    ]

    const retorno = arrayFunction.exercicio12(arrayDeEntrada, letra)

    expect(retorno).toEqual(true)
  })

  it('Recebendo letra que tem em uns nomes e outros sobrenomes', () => {
    const letra = 'e'
    const arrayDeEntrada = [
      { nome: 'murillo', sobrenome: 'peteffi' },
      { nome: 'camille', sobrenome: 'grings' },
      { nome: 'emerson', sobrenome: 'pisoni' },
      { nome: 'gustavo', sobrenome: 'buttenbender' },
    ]

    const retorno = arrayFunction.exercicio12(arrayDeEntrada, letra)

    expect(retorno).toEqual(true)
  })

  it('Recebendo com alguns que não têm', () => {
    const letra = 'i'
    const arrayDeEntrada = [...instrutoresSimples]

    const retorno = arrayFunction.exercicio12(arrayDeEntrada, letra)

    expect(retorno).toEqual(false)
  })
})

describe('exercicio13', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar um objeto onde os nomes dos instrutores são propriedades e os sobrenomes valores
  // Ex: { harry: 'potter', oliver: 'tsubasa'  }
  //

  it('Recebendo instrutores variados', () => {
    const entrada = [...instrutoresSimples]
    const retornoEsperado = {
      camille: 'grings',
      emerson: 'pisoni',
      gustavo: 'buttenbender',
      luan: 'winck',
      murillo: 'peteffi',
      william: 'cardozo',
    }

    const retorno = arrayFunction.exercicio13(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo instrutores variados', () => {
    const entrada = [{ nome: 'harry', sobrenome: 'potter' }]
    const retornoEsperado = { harry: 'potter' }

    const retorno = arrayFunction.exercicio13(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo instrutores variados', () => {
    const entrada = []
    const retornoEsperado = {}

    const retorno = arrayFunction.exercicio13(entrada)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio14', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar o primeiro instrutor encontrado com determinada letra no sobrenome
  //

  it('Recebendo mais de um instrutor com a letra', () => {
    const letra = 'n'
    const arrayDeEntrada = [...instrutoresSimples]
    const retornoEsperado = { nome: 'camille', sobrenome: 'grings' }

    const retorno = arrayFunction.exercicio14(arrayDeEntrada, letra)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo apenas um instrutor com a letra', () => {
    const letra = 'w'
    const arrayDeEntrada = [...instrutoresSimples]
    const retornoEsperado = { nome: 'luan', sobrenome: 'winck' }

    const retorno = arrayFunction.exercicio14(arrayDeEntrada, letra)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo nenhum instrutor com a letra', () => {
    const letra = 'y'
    const arrayDeEntrada = [...instrutoresSimples]

    const retorno = arrayFunction.exercicio14(arrayDeEntrada, letra)

    expect(retorno).toBeUndefined()
  })
})

const instrutoresCompleto = [
  {
    nome: 'murillo',
    sobrenome: 'peteffi',
    idade: 132,
    cidade: 'Springfield',
  },
  {
    nome: 'camille',
    sobrenome: 'grings',
    idade: 128,
    cidade: 'Hawkins',
  },
  {
    nome: 'william',
    sobrenome: 'cardozo',
    idade: 236,
    cidade: 'Springfield',
  },
  {
    nome: 'gustavo',
    sobrenome: 'buttenbender',
    idade: 319,
    cidade: 'Hawkins',
  },
  {
    nome: 'emerson',
    sobrenome: 'pisoni',
    idade: 325,
    cidade: 'Springfield',
  },
  {
    nome: 'luan',
    sobrenome: 'winck',
    idade: 213,
    cidade: 'Hawkins',
  },
]

describe('exercicio15', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar lista de sobrenomes dos que têm menos de determinada idade
  //

  it('Recebendo alguns abaixo', () => {
    const idade = 250
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = ['peteffi', 'grings', 'cardozo', 'winck']

    const retorno = arrayFunction.exercicio15(arrayDeEntrada, idade)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo alguns abaixo e um igual', () => {
    const idade = 213
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = ['peteffi', 'grings']

    const retorno = arrayFunction.exercicio15(arrayDeEntrada, idade)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo com nenhum abaixo', () => {
    const idade = 50
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = []

    const retorno = arrayFunction.exercicio15(arrayDeEntrada, idade)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio16', () => {
  //
  // Recebe um array de instrutores
  // Deve validar se algum morador de determinada cidade tem mais de determinada idade
  //

  it('Recebendo cidade com alguns acima da idade', () => {
    const idade = 200
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio16(arrayDeEntrada, cidade, idade)

    expect(retorno).toEqual(true)
  })

  it('Recebendo cidade com um acima da idade', () => {
    const idade = 300
    const cidade = 'Hawkins'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio16(arrayDeEntrada, cidade, idade)

    expect(retorno).toEqual(true)
  })

  it('Recebendo cidade com nenhum acima da idade', () => {
    const idade = 400
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio16(arrayDeEntrada, cidade, idade)

    expect(retorno).toEqual(false)
  })
})

describe('exercicio17', () => {
  //
  // Recebe um array de instrutores
  // Deve validar se todos os moradores de determinada cidade tem determinada letra no nome ou no sobrenome
  //

  it('Recebendo cidade com todos com letra', () => {
    const letra = 'a'
    const cidade = 'Hawkins'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio17(arrayDeEntrada, cidade, letra)

    expect(retorno).toEqual(true)
  })

  it('Recebendo cidade com alguns com a letra', () => {
    const letra = 'l'
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio17(arrayDeEntrada, cidade, letra)

    expect(retorno).toEqual(false)
  })

  it('Recebendo cidade com nenhum acima da letra', () => {
    const letra = 'y'
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio17(arrayDeEntrada, cidade, letra)

    expect(retorno).toEqual(false)
  })
})

describe('exercicio18', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar a soma das idades dos moradores de determinada cidade
  //

  it('Recebendo cidade com moradores', () => {
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = 693

    const retorno = arrayFunction.exercicio18(arrayDeEntrada, cidade)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo cidade sem moradores', () => {
    const cidade = 'London'
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = 0

    const retorno = arrayFunction.exercicio18(arrayDeEntrada, cidade)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio19', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar os moradores de determinada cidade ordenados por ordem decrescente de idade
  //

  it('Recebendo cidade com moradores', () => {
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = [
      { cidade: 'Springfield', idade: 325, nome: 'emerson', sobrenome: 'pisoni' },
      { cidade: 'Springfield', idade: 236, nome: 'william', sobrenome: 'cardozo' },
      { cidade: 'Springfield', idade: 132, nome: 'murillo', sobrenome: 'peteffi' },
    ]

    const retorno = arrayFunction.exercicio19(arrayDeEntrada, cidade)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo cidade sem moradores', () => {
    const cidade = 'Hawkins'
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = [
      { cidade: 'Hawkins', idade: 319, nome: 'gustavo', sobrenome: 'buttenbender' },
      { cidade: 'Hawkins', idade: 213, nome: 'luan', sobrenome: 'winck' },
      { cidade: 'Hawkins', idade: 128, nome: 'camille', sobrenome: 'grings' },
    ]

    const retorno = arrayFunction.exercicio19(arrayDeEntrada, cidade)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio20', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar o sobrenome dos que tenham mais de determinada idade.
  // O retorno deve vir ordenado de forma decrescente e em letras maiúsculas.
  //

  it('Recebendo idade com dois instrutores', () => {
    const idade = 300
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = ['PISONI', 'BUTTENBENDER']

    const retorno = arrayFunction.exercicio20(arrayDeEntrada, idade)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo cidade com todos moradores', () => {
    const idade = 0
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = ['WINCK', 'PISONI', 'PETEFFI', 'GRINGS', 'CARDOZO', 'BUTTENBENDER']

    const retorno = arrayFunction.exercicio20(arrayDeEntrada, idade)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo cidade com todos moradores', () => {
    const idade = 9999
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = []

    const retorno = arrayFunction.exercicio20(arrayDeEntrada, idade)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio21', () => {
  //
  // Recebe um array de instrutores
  // Deve validar se metade da idade de algum morador de determinada cidade é maior que determinado valor
  //

  it('Recebendo limite com alguns acima', () => {
    const limite = 100
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio21(arrayDeEntrada, cidade, limite)

    expect(retorno).toEqual(true)
  })

  it('Recebendo limite com apenas um acima', () => {
    const limite = 150
    const cidade = 'Hawkins'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio21(arrayDeEntrada, cidade, limite)

    expect(retorno).toEqual(true)
  })

  it('Recebendo limite com apenas um igual', () => {
    const limite = 162.5
    const cidade = 'Springfield'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio21(arrayDeEntrada, cidade, limite)

    expect(retorno).toEqual(false)
  })

  it('Recebendo limite com todos menores', () => {
    const limite = 200
    const cidade = 'Hawkins'
    const arrayDeEntrada = [...instrutoresCompleto]

    const retorno = arrayFunction.exercicio21(arrayDeEntrada, cidade, limite)

    expect(retorno).toEqual(false)
  })
})

describe('exercicio22', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar um objeto com as iniciais dos sobrenomes dos instrutores
  // cujos dobros das idades sejam maiores que determinado valor.
  // O retorno deve estar em ordem alfabética, onde a posição na ordenação é o nome da prop.
  //
  // Ex de resposta: { 1: 'f', 2: 'g' }
  //

  it('Recebendo limite suficiente para apenas um', () => {
    const limite = 640
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = { 1: 'p' }

    const retorno = arrayFunction.exercicio22(arrayDeEntrada, limite)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo soma para todos instrutores', () => {
    const limite = 0
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = { 1: 'b', 2: 'c', 3: 'g', 4: 'p', 5: 'p', 6: 'w' }

    const retorno = arrayFunction.exercicio22(arrayDeEntrada, limite)

    expect(retorno).toEqual(retornoEsperado)
  })
})

describe('exercicio23', () => {
  //
  // Recebe um array de instrutores
  // Deve retornar uma string com todas as consoantes dos sobrenomes dos instrutores
  // cujas somas dos dígitos da idade sejam maiores do que um determinado valor.
  // O retorno deve estar em ordem alfabética, sem repetições e em letras maiúsculas.
  //

  it('Recebendo soma suficiente para um instrutor', () => {
    const soma = 11
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = 'BDNRT'

    const retorno = arrayFunction.exercicio23(arrayDeEntrada, soma)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo soma para todos instrutores', () => {
    const soma = 1
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = 'BCDFGKNPRSTWZ'

    const retorno = arrayFunction.exercicio23(arrayDeEntrada, soma)

    expect(retorno).toEqual(retornoEsperado)
  })

  it('Recebendo soma para nenhum instrutor', () => {
    const soma = 2222
    const arrayDeEntrada = [...instrutoresCompleto]
    const retornoEsperado = ''

    const retorno = arrayFunction.exercicio23(arrayDeEntrada, soma)

    expect(retorno).toEqual(retornoEsperado)
  })
})
