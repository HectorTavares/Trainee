import axios from 'axios'
import {
  escolherPista,
  escolherPersonagen,
  calcularVantagemTipoPista,
  calcularDebuffDePista,
  configurarPersonagen,
  velocidade,
  Corrida,
  EscolherAliado,
  EscolherInimigo,
  rodada,

} from '../src/metodos'

let pistas
let personagens

beforeAll(async () => {

  const getPistas = await axios.get('https://hectortavares.github.io/Trainee/Conteudo/corrida-maluca/pistas.json')
  const getPersonagens = await axios.get('https://hectortavares.github.io/Trainee/Conteudo/corrida-maluca/personagens.json')

  pistas = getPistas.data
  personagens = getPersonagens.data

})

describe('Expected_Tests', () => {

  it('Deve conseguir obter a pista corretamente', () => {

    const resultadoObtido = escolherPista(pistas, 3)
    const resultadoEsperado = {
      "id": 3,
      "nome": "Deserto do Saara",
      "tipo": "DESERTO",
      "descricao": "Uma deserto imenso, os corredores vão derrapar muito na areia",
      "tamanho": 50,
      "debuff": -2,
      "posicoesBuffs": [12, 26, 38]
    }

    expect(resultadoObtido).toEqual(resultadoEsperado)

  })

  it('Deve conseguir obter o corredor corretamente', () => {
    const resultadoObtido = escolherPersonagen(personagens, 5)
    const resultadoEsperado = {
      "id": 5,
      "nome": "Barão Vermelho",
      "velocidade": 5,
      "drift": 1,
      "aceleracao": 2,
      "vantagem": "MONTANHA"
    }

    expect(resultadoObtido).toEqual(resultadoEsperado)
  })

  it('Deve conseguir calcular a vantagem de tipo pista corretamente', () => {

    const personagenEscolhido = escolherPersonagen(personagens, 1)
    const pistaEscolhida = escolherPista(pistas, 2)
    const resultadoObtido = calcularVantagemTipoPista(pistaEscolhida, personagenEscolhido)
    const resultadoEsperado = {
      "id": 1,
      "nome": "Dick Vigarista",
      "velocidade": 7,
      "drift": 4,
      "aceleracao": 6,
      "vantagem": "CIRCUITO"
    }

    expect(resultadoObtido).toEqual(resultadoEsperado)
  })

  it('Deve conseguir calcular o debuff de pista corretamente', () => {
    const personagenEscolhido = escolherPersonagen(personagens, 1)
    const pistaEscolhida = escolherPista(pistas, 2)
    const resultadoObtido = calcularDebuffDePista(pistaEscolhida, personagenEscolhido)
    const resultadoEsperado = {
      "id": 1,
      "nome": "Dick Vigarista",
      "velocidade": 4,
      "drift": 1,
      "aceleracao": 3,
      "vantagem": "CIRCUITO"
    }
    expect(resultadoObtido).toEqual(resultadoEsperado)
  })

  it('Deve conseguir calcular o buff de posição de pista para 3 corredores', () => {
    //Criar Teste Aqui
    //Fazer metodo 
    const personagen1 = escolherPersonagen(personagens, 1)
    const personagen2 = escolherPersonagen(personagens, 2)
    const personagen3 = escolherPersonagen(personagens, 3)
    const listaPersonagens = [personagen1, personagen2, personagen3]

    const pistaEscolhida = escolherPista(pistas, 2)



    const resultado = Corrida(listaPersonagens, pistaEscolhida)

    const correto = [{ "aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 2, "inimigoEstaNaCorrida": false, "nome": "Irmãos Rocha", "posicao": 22, "vantagem": "MONTANHA", "velocidade": 4 }, { "aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 3, "inimigoEstaNaCorrida": false, "nome": "Irmãos Pavor", "posicao": 19, "vantagem": "DESERTO", "velocidade": 3 }, { "aceleracao": 5, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 3, "id": 1, "inimigoEstaNaCorrida": false, "nome": "Dick Vigarista", "posicao": 18, "vantagem": "CIRCUITO", "velocidade": 6 }]

    expect(resultado).toEqual(correto)
  })

  it('Deve conseguir calcular a próxima posição corretamente se estiver sob o buff de um aliado', () => {
    
    const pistaEscolhidaNaDisputa = escolherPista(pistas,5)
    const corredorPrincipal = EscolherAliado(escolherPersonagen(personagens,9),2)
    const aliado = escolherPersonagen(personagens,2)

    const corredoresNaDisputa = [corredorPrincipal,aliado]

    const finalCorrida = Corrida(corredoresNaDisputa,pistaEscolhidaNaDisputa)
   
    const resultadoEsperado = [{"aceleracao": 7, "aliado": 2, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 3, "id": 9, "inimigoEstaNaCorrida": false, "nome": "Tio Tomás", "posicao": 30, "vantagem": "FLORESTA", "velocidade": 5}, {"aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, 
    "buffsPosicaoUtilizados": 1, "drift": 1, "id": 2, "inimigoEstaNaCorrida": false, "nome": "Irmãos Rocha", "posicao": 12, "vantagem": "MONTANHA", "velocidade": 4}]
    
    expect(finalCorrida).toEqual(resultadoEsperado)
  })

  it('Deve conseguir calcular a próxima posição corretamente se estiver sob o debuff de um inimigo', () => {
    
    const pistaEscolhidaNaDisputa = escolherPista(pistas,5)
    const corredorPrincipal = EscolherInimigo(escolherPersonagen(personagens,9),2)
    const aliado = escolherPersonagen(personagens,2)

    const corredoresNaDisputa = [corredorPrincipal,aliado]

    const finalCorrida = Corrida(corredoresNaDisputa,pistaEscolhidaNaDisputa)
   
    const resultadoEsperado = [{"aceleracao": 7, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 3, "id": 9, "inimigo": 2, "inimigoEstaNaCorrida": true, "nome": "Tio Tomás", "posicao": 33, "vantagem": "FLORESTA", "velocidade": 5}, {"aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 1, "drift": 1, "id": 2, "inimigoEstaNaCorrida": false, "nome": "Irmãos Rocha", "posicao": 16, "vantagem": "MONTANHA", "velocidade": 
    4}]
    expect(finalCorrida).toEqual(resultadoEsperado)
  })

  it('Deve conseguir completar uma corrida com um vencedor', () => {
    
    const personagen1 = escolherPersonagen(personagens, 1)
    const personagen2 = escolherPersonagen(personagens, 2)
    const personagen3 = escolherPersonagen(personagens, 3)
    const listaPersonagens = [personagen1, personagen2, personagen3]

    const pistaEscolhida = escolherPista(pistas, 2)



    const resultadoCorrida = Corrida(listaPersonagens, pistaEscolhida)

    const resultado = resultadoCorrida[0].nome;
    const esperado = 'Irmãos Rocha'

    expect(resultado).toEqual(esperado)
  })

  it('Deve conseguir criar corredor corretamente somente com aliado', () => {
    //Criar Teste Aqui

    const corredorEscolhido = escolherPersonagen(personagens, 1)
    const corredorConfigurado = EscolherAliado(corredorEscolhido, 2)

    const resultadoEsperado = {
      "id": 1,
      "nome": "Dick Vigarista",
      "velocidade": 5,
      "drift": 2,
      "aceleracao": 4,
      "vantagem": "CIRCUITO",
      "aliado": 2
    }

    expect(corredorConfigurado).toEqual(resultadoEsperado)
  })

  it('Deve conseguir criar corredor corretamente somente com inimigo', () => {
    //Criar Teste Aqui
    const corredorEscolhido = escolherPersonagen(personagens, 1)
    const corredorConfigurado = EscolherInimigo(corredorEscolhido, 2)

    const resultadoEsperado = {
      "id": 1,
      "nome": "Dick Vigarista",
      "velocidade": 5,
      "drift": 2,
      "aceleracao": 4,
      "vantagem": "CIRCUITO",
      "inimigo": 2
    }

    expect(corredorConfigurado).toEqual(resultadoEsperado)
  })

  it('Deve conseguir criar corredor corretamente com aliado e inimigo', () => {
    //Criar Teste Aqui
    const corredorEscolhido = escolherPersonagen(personagens, 1)
    const corredorConfigurado1 = EscolherAliado(corredorEscolhido, 2)
    const corredorConfigurado = EscolherInimigo(corredorConfigurado1, 3)

    const resultadoEsperado = {
      "id": 1,
      "nome": "Dick Vigarista",
      "velocidade": 5,
      "drift": 2,
      "aceleracao": 4,
      "vantagem": "CIRCUITO",
      "aliado": 2,
      "inimigo": 3
    }

    expect(corredorConfigurado).toEqual(resultadoEsperado)
  })

  it('Deve conseguir calcular as novas posições corretamente de uma rodada para a próxima', () => {
    //Criar Teste Aqui
    //possivel
    const pistaDaRodada = escolherPista(pistas, 4)
    const corredoresDaRodada = [configurarPersonagen(pistaDaRodada, escolherPersonagen(personagens, 1)), configurarPersonagen(pistaDaRodada, escolherPersonagen(personagens, 5))]



    const contadores = [0, 0, 0, 0]

    rodada(corredoresDaRodada, pistaDaRodada, 2, contadores)

    const corredoresPosRodada = [{ "aceleracao": 2, "buffPosicao": 0, "buffsPosicaoUtilizados": 0, "drift": 0, "id": 1, "nome": "Dick Vigarista", "posicao": 2, "vantagem": "CIRCUITO", "velocidade": 3 }, { "aceleracao": 0, "buffPosicao": 0, "buffsPosicaoUtilizados": 0, "drift": 0, "id": 5, "nome": "Barão Vermelho", "posicao": 0, "vantagem": "MONTANHA", "velocidade": 3 }]

    expect(corredoresDaRodada).toEqual(corredoresDaRodada)

  })

  it('Deve impedir que corredor se mova negativamente mesmo se o calculo de velocidade seja negativo', () => {
    //Criar Teste Aqui
    //possivel

    const penelope = {
      "id": 6,
      "nome": "Penélope Charmosa",
      "velocidade": -12,
      "drift": -2,
      "aceleracao": 2,
      "vantagem": "CIDADE"
    }

    const resultado = configurarPersonagen(escolherPista(pistas, 3), penelope)
    const esperado = { "aceleracao": 0, "buffPosicao": 0, "buffsPosicaoUtilizados": 0, "drift": 0, "id": 6, "nome": "Penélope Charmosa", "posicao": 0, "vantagem": "CIDADE", "velocidade": 0 }

    expect(resultado).toEqual(esperado)

  })

  it('Deve impedir que o Dick Vigarista vença a corrida se estiver a uma rodada de ganhar', () => {
    
    const personagen1 = escolherPersonagen(personagens, 1)
    const personagen2 = escolherPersonagen(personagens, 2)
    const personagen3 = escolherPersonagen(personagens, 3)
    const listaPersonagens = [personagen1, personagen2, personagen3]

    const pistaEscolhida = escolherPista(pistas, 2)



    const resultado = Corrida(listaPersonagens, pistaEscolhida)
    const esperado = [{ "aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 2, "inimigoEstaNaCorrida": false, "nome": "Irmãos Rocha", "posicao": 22, "vantagem": "MONTANHA", "velocidade": 4 }, { "aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 3, "inimigoEstaNaCorrida": false, "nome": "Irmãos Pavor", "posicao": 19, "vantagem": "DESERTO", "velocidade": 3 }, { "aceleracao": 5, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 3, "id": 1, "inimigoEstaNaCorrida": false, "nome": "Dick Vigarista", "posicao": 18, "vantagem": "CIRCUITO", "velocidade": 6 }]

    expect(resultado).toEqual(esperado)


  })

})



describe('Outros_Testes', () => {

  it('Configurar Personagen corretamente', () => {

    const personagenEscolhido = escolherPersonagen(personagens, 3)
    const pistaEscolhida = escolherPista(pistas, 3)
    const resultadoObtido = configurarPersonagen(pistaEscolhida, personagenEscolhido)
    const resultaoEsperado = {
      "id": 3,
      "nome": "Irmãos Pavor",
      "velocidade": 4,
      "drift": 2,
      "aceleracao": 3,
      "vantagem": "DESERTO",
      "posicao": 0,
      "buffPosicao": 0,
      "buffsPosicaoUtilizados": 0
    }

    expect(resultadoObtido).toEqual(resultaoEsperado)

  })

  it('deve pegar o status de aceleração como velocidade', () => {
    const personagenEscolhido = escolherPersonagen(personagens, 1)

    const resultadoObtido = velocidade(2, personagenEscolhido)

    expect(resultadoObtido).toBe(4)
  })

  it('deve pegar o status de drift com velocidade', () => {
    const personagenEscolhido = escolherPersonagen(personagens, 1)

    const resultadoObtido = velocidade(8, personagenEscolhido)

    expect(resultadoObtido).toBe(2)
  })

  it('deve pegar o status de velocidade como velocidade', () => {
    const personagenEscolhido = escolherPersonagen(personagens, 1)

    const resultadoObtido = velocidade(7, personagenEscolhido)

    expect(resultadoObtido).toBe(5)
  })

  it('Corrida sem aliados nem inimigos', () => {
    const personagen1 = escolherPersonagen(personagens, 1)
    const personagen2 = escolherPersonagen(personagens, 2)
    const personagen3 = escolherPersonagen(personagens, 3)
    const listaPersonagens = [personagen1, personagen2, personagen3]

    const pistaEscolhida = escolherPista(pistas, 2)



    const resultado = Corrida(listaPersonagens, pistaEscolhida)
    const esperado = [{ "aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 2, "inimigoEstaNaCorrida": false, "nome": "Irmãos Rocha", "posicao": 22, "vantagem": "MONTANHA", "velocidade": 4 }, { "aceleracao": 2, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 3, "inimigoEstaNaCorrida": false, "nome": "Irmãos Pavor", "posicao": 19, "vantagem": "DESERTO", "velocidade": 3 }, { "aceleracao": 5, "aliadoEstaNaCorrida": false, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 3, "id": 1, "inimigoEstaNaCorrida": false, "nome": "Dick Vigarista", "posicao": 18, "vantagem": "CIRCUITO", "velocidade": 6 }]

    expect(resultado).toEqual(esperado)

  })

  it('Corrida Completa', ()=>{
    const pistaEscolhida = escolherPista(pistas,3)
    const primeiro = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,1),2),3)
    const segundo = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,2),1),4)
    const terceiro = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,3),1),2)
    const quarto = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,4),1),2)
    const quinto = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,5),6),7)
    const sexto = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,6),7),8)
    const setimo = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,7),2),1)
    const oitavo = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,8),9),10)
    const nono = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,9),4),3)
    const decimo = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,10),11),7)
    const decimoPrimeiro = EscolherAliado(EscolherInimigo(escolherPersonagen(personagens,11),2),7)

    const listaCompletaCorredores = [primeiro,segundo,terceiro,quarto,quinto,sexto,setimo,oitavo,nono,decimo,decimoPrimeiro]

    const corrida = Corrida(listaCompletaCorredores,pistaEscolhida)
    const resultado = [{"aceleracao": 1, "aliado": 2, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 3, "drift": 2, "id": 4, "inimigo": 1, "inimigoEstaNaCorrida": true, "nome": "Professor Aéreo", "posicao": 54, "vantagem": "DESERTO", "velocidade": 6}, {"aceleracao": 1, "aliado": 10, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 3, "drift": 1, "id": 8, "inimigo": 9, "inimigoEstaNaCorrida": true, "nome": "Quadrilha da Morte", "posicao": 50, "vantagem": "CIDADE", "velocidade": 4}, {"aceleracao": 0, "aliado": 7, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 3, "drift": 0, "id": 10, "inimigo": 11, "inimigoEstaNaCorrida": true, "nome": "Peter Perfeito", "posicao": 48, "vantagem": "CIRCUITO", "velocidade": 5}, {"aceleracao": 3, "aliado": 2, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 3, "drift": 2, "id": 3, "inimigo": 1, "inimigoEstaNaCorrida": true, "nome": "Irmãos Pavor", "posicao": 47, "vantagem": "DESERTO", "velocidade": 4}, {"aceleracao": 0, "aliado": 1, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 2, "id": 7, "inimigo": 2, "inimigoEstaNaCorrida": true, "nome": "Sargento Bombarda", "posicao": 37, "vantagem": "CIDADE", "velocidade": 2}, {"aceleracao": 3, "aliado": 8, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 1, "id": 6, "inimigo": 7, "inimigoEstaNaCorrida": true, "nome": "Penélope Charmosa", "posicao": 35, "vantagem": "CIDADE", "velocidade": 2}, {"aceleracao": 1, "aliado": 7, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 0, "id": 11, "inimigo": 2, "inimigoEstaNaCorrida": true, "nome": "Rufus Lenhador", "posicao": 33, "vantagem": "FLORESTA", "velocidade": 3}, {"aceleracao": 1, "aliado": 4, "aliadoEstaNaCorrida": true, "buffPosicao": 0, "buffsPosicaoUtilizados": 2, "drift": 0, "id": 2, "inimigo": 1, "inimigoEstaNaCorrida": true, "nome": "Irmãos Rocha", "posicao": 33, "vantagem": "MONTANHA", "velocidade": 3}, {"aceleracao": 0, "aliado": 7, "aliadoEstaNaCorrida": true, "buffPosicao": 8, "buffsPosicaoUtilizados": 2, "drift": 0, "id": 5, "inimigo": 6, "inimigoEstaNaCorrida": true, "nome": "Barão Vermelho", "posicao": 28, "vantagem": "MONTANHA", "velocidade": 3}, {"aceleracao": 2, "aliado": 3, "aliadoEstaNaCorrida": true, "buffPosicao": 9, "buffsPosicaoUtilizados": 2, "drift": 0, "id": 1, "inimigo": 2, "inimigoEstaNaCorrida": true, "nome": "Dick Vigarista", "posicao": 28, "vantagem": "CIRCUITO", "velocidade": 3}, {"aceleracao": 4, "aliado": 3, "aliadoEstaNaCorrida": true, "buffPosicao": 10, "buffsPosicaoUtilizados": 2, "drift": 0, "id": 9, "inimigo": 4, "inimigoEstaNaCorrida": true, "nome": "Tio Tomás", "posicao": 26, "vantagem": "FLORESTA", "velocidade": 2}]

    expect(corrida).toEqual(resultado)
  })

})
