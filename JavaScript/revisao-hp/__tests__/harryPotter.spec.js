import { criarBruxo,criarTrouxa,falarMateriasQueEstuda } from '../src/harryPotter'

describe('Suite - criacao', () => {
  
  it('Deve criar um Bruxo com nome correto', () => {
    const novoBruxo = criarBruxo("Héctor","Lufa-Lufa")
    
    const esperado = "Héctor"

    expect(esperado).toEqual(novoBruxo.nome)
  })

  it('Deve criar um Bruxo com casa correta',()=>{
    const novoBruxo = criarBruxo("Ronanldinho","Grifinoria")
    const esperado = "Grifinoria"

    expect(esperado).toEqual(novoBruxo.casa)
  })

  it('Deve criar um Trouxa com o nome correto',()=>{
    const novoTrouxa = criarTrouxa("Jorge")
    const esperado = "Jorge"

    expect(esperado).toEqual(novoTrouxa.nome)

  })

})

describe('Suite Falar Materias que Estuda',()=>{
  
  it('Bruxo Falar corretamente o que estuda',()=>{
    const harry = criarBruxo("Harry","Corvinal")
    const esperado ="As matérias que estudo são Pocoes, Artes das Trevas"

    expect(esperado).toEqual(harry.falarMateriasQueEstuda())
  })

  it('Trouxa Falar corretamente o que estuda', ()=>{
    const trouxa = criarTrouxa("Héctor")
    const esperado = "As matérias que estudo são Matematica, Geografia"

    expect(esperado).toEqual(trouxa.falarMateriasQueEstuda())
  })
})