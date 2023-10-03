export const exercicio01 = numeros => numeros.filter(numero => numero % 2 === 0)

export const exercicio02 = numeros => numeros.some(numero => numero > 10)

export const exercicio03 = numeros => numeros.every(numero => numero % 2 !== 0)

export const exercicio04 = numeros => numeros.map(numero => numero * numero)

export const exercicio05 = numeros => numeros.reduce((atual, numero) => atual + numero)

export const exercicio06 = numeros => numeros.sort((atual, proximo) => proximo - atual)

export const exercicio07 = numeros => numeros.find(numero => numero < 100)

export const exercicio08 = (instrutores, letra) =>
  instrutores.filter(instrutor => instrutor.nome.includes(letra))

export const exercicio09 = instrutores => instrutores.map(instrutor => instrutor.sobrenome[0])

export const exercicio10 = (instrutores, letra) =>
  instrutores.some(instrutor => instrutor.nome[0] === letra || instrutor.sobrenome[0] === letra)

export const exercicio11 = instrutores =>
  instrutores.sort((atual, proximo) => (atual.sobrenome < proximo.sobrenome ? -1 : 1))

export const exercicio12 = (instrutores, letra) =>
  instrutores.every(instrutor => instrutor.nome.includes(letra) || instrutor.sobrenome.includes(letra))

export const exercicio13 = instrutores =>
  instrutores.reduce((total, instrutor) => {
    total[instrutor.nome] = instrutor.sobrenome
    return total
  }, {})

export const exercicio14 = (instrutores, letra) =>
  instrutores.find(instrutor => instrutor.sobrenome.includes(letra))

export const exercicio15 = (instrutores, limiteIdade) =>
  instrutores.filter(instrutor => instrutor.idade < limiteIdade).map(instrutor => instrutor.sobrenome)

export const exercicio16 = (instrutores, cidade, limiteIdade) =>
  instrutores
    .filter(instrutor => instrutor.cidade === cidade)
    .some(instrutor => instrutor.idade > limiteIdade)

export const exercicio17 = (instrutores, cidade, letra) =>
  instrutores
    .filter(instrutor => instrutor.cidade === cidade)
    .every(instrutor => instrutor.nome.includes(letra) || instrutor.sobrenome.includes(letra))

export const exercicio18 = (instrutores, cidade) =>
  instrutores
    .filter(instrutor => instrutor.cidade === cidade)
    .reduce((atual, instrutor) => instrutor.idade + atual, 0)

export const exercicio19 = (instrutores, cidade) =>
  instrutores
    .filter(instrutor => instrutor.cidade === cidade)
    .sort((atual, proximo) => proximo.idade - atual.idade)

export const exercicio20 = (instrutores, limiteIdade) =>
  instrutores
    .filter(instrutor => instrutor.idade > limiteIdade)
    .sort((atual, proximo) => (atual.sobrenome < proximo.sobrenome ? 1 : -1))
    .map(instrutor => instrutor.sobrenome.toUpperCase())

export const exercicio21 = (instrutores, cidade, limiteMetadeIdade) =>
  instrutores
    .filter(instrutor => instrutor.cidade === cidade)
    .some(instrutor => instrutor.idade / 2 > limiteMetadeIdade)

export const exercicio22 = (instrutores, limiteDobroIdade) =>
  instrutores
    .filter(instrutor => instrutor.idade * 2 > limiteDobroIdade)
    .map(instrutor => instrutor.sobrenome[0])
    .sort()
    .reduce((total, valor, index) => {
      total[index + 1] = valor
      return total
    }, {})

export const exercicio23 = (instrutores, limiteSomaIdade) =>
  instrutores
    .filter(
      instrutor =>
        (instrutor.idade + '').split('').reduce((total, idade) => total + Number(idade), 0) > limiteSomaIdade
    )
    .reduce(
      (total, instrutor) =>
        total +
        instrutor.sobrenome
          .toUpperCase()
          .match(/[^aeiou]/gi)
          .join(''),
      ''
    )
    .split('')
    .filter((instrutorLetraSobrenome, index, array) => array.indexOf(instrutorLetraSobrenome) == index)
    .sort()
    .join('')
