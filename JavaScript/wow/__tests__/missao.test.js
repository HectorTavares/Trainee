import {
  realizarMissao,
  receberRecompensa,
  selecionarMissao,
} from '../src/missao';
import { criaPersonagem } from '../src/personagem';

describe('Suite de testes da missao', () => {
  let listaMissoes;

  beforeEach(() => {
    listaMissoes = [
      {
        id: 1,
        descricao: 'Limpar os canecos da taberna',
        tempoEstimado: 5000,
        niveisRecebidos: 0,
        dinheiroRecebido: 10,
      },
      {
        id: 2,
        descricao: 'Limpar a taberna',
        tempoEstimado: 15000,
        niveisRecebidos: 0,
        dinheiroRecebido: 20,
      },
      {
        id: 3,
        descricao: 'Batalhar contra Murloc',
        tempoEstimado: 20000,
        niveisRecebidos: 1,
        dinheiroRecebido: 0,
      },
      {
        id: 4,
        descricao: 'Batalhar contra Ogro',
        tempoEstimado: 30000,
        niveisRecebidos: 2,
        dinheiroRecebido: 0,
      },
      {
        id: 5,
        descricao: 'Defender uma cidade de ataques de Worgs',
        tempoEstimado: 90000,
        niveisRecebidos: 3,
        dinheiroRecebido: 70,
      },
      {
        id: 6,
        descricao: 'Vencer o campeonato de quem bebe mais cerveija amanteigada',
        tempoEstimado: 120000,
        niveisRecebidos: 1,
        dinheiroRecebido: 100,
      },
      {
        id: 7,
        descricao: 'Derrotar o Lich',
        tempoEstimado: 300000,
        niveisRecebidos: 8,
        dinheiroRecebido: 170,
      },
      {
        id: 8,
        descricao: 'Capturar um Dragão',
        tempoEstimado: 1800000,
        niveisRecebidos: 17,
        dinheiroRecebido: 400,
      },
      {
        id: 9,
        descricao: 'Limpar a casa do Deathwing',
        tempoEstimado: 4000000000,
        niveisRecebidos: 0,
        dinheiroRecebido: 1000000000,
        idExpansao: 3,
      },
      {
        id: 10,
        descricao: "Alimentar a Al'ar",
        tempoEstimado: 40000,
        niveisRecebidos: 0,
        dinheiroRecebido: 150,
        idExpansao: 1,
      },
      {
        id: 11,
        descricao: 'Esquiar em Northrend',
        tempoEstimado: 60000,
        niveisRecebidos: 1,
        dinheiroRecebido: 100,
        idExpansao: 2,
      },
      {
        id: 12,
        descricao: 'Vencer torneio de Kung-Fu',
        tempoEstimado: 180000,
        niveisRecebidos: 5,
        dinheiroRecebido: 350,
        idExpansao: 4,
      },
      {
        id: 13,
        descricao: 'Limpar Warglaives de Ilidan',
        tempoEstimado: 30000,
        niveisRecebidos: 0,
        dinheiroRecebido: 200,
        idExpansao: 6,
      },
      {
        id: 14,
        descricao: 'Lutar em Azeroth',
        tempoEstimado: 1000000,
        niveisRecebidos: 10,
        dinheiroRecebido: 0,
        idExpansao: 7,
      },
    ];
  });

  it('Deve conseguir selecionar uma missao corretamente', () => {
    const missaoEscolhida = selecionarMissao(listaMissoes, 1);

    const missaoEsperada = {
      id: 1,
      descricao: 'Limpar os canecos da taberna',
      tempoEstimado: 5000,
      niveisRecebidos: 0,
      dinheiroRecebido: 10,
    };

    expect(missaoEscolhida).toEqual(missaoEsperada);
  });

  it('Não deve conseguir fazer a missao sem ter a expansão', () => {
    const HUMANO = JSON.parse(
      JSON.stringify({
        id: 2,
        raca: 'Humano',
        vidaBase: 5,
        vigorBase: 4,
        danoBase: 5,
        tipo: 'NORMAL',
      }),
    );
    expect(() =>
      realizarMissao(HUMANO, selecionarMissao(listaMissoes, 10), []),
    ).toThrowError(
      'Não possui a expansão necessária para realizar a missao escolhida',
    );
  });

  it('Deve conseguir concluir uma missão corretamente e receber seus prêmios', () => {
    const HUMANO = JSON.parse(
      JSON.stringify({
        id: 2,
        raca: 'Humano',
        vidaBase: 5,
        vigorBase: 4,
        danoBase: 5,
        tipo: 'NORMAL',
      }),
    );
    const personagem = criaPersonagem(HUMANO, 'Tavares', [], []);
    const personagemEsperado = {
      nome: 'Tavares',
      raca: 'Humano',
      tipo: 'NORMAL',
      equipamentos: [],
      inventario: [],
      dinheiro: 10,
      nivel: 1,
      vida: 5,
      vigor: 4,
      dano: 5,
    };

    const personagemAtualizado = receberRecompensa(
      personagem,
      selecionarMissao(listaMissoes, 1),
    );

    expect(personagemAtualizado).toEqual(personagemEsperado);
  });

  it('Deve conseguir concluir uma missão de expansão corretamente e receber seus prêmios se já possuir a expansão', async () => {
    const HUMANO = JSON.parse(
      JSON.stringify({
        id: 2,
        raca: 'Humano',
        vidaBase: 5,
        vigorBase: 4,
        danoBase: 5,
        tipo: 'NORMAL',
      }),
    );
    const personagem = criaPersonagem(HUMANO, 'Tavares', [], []);

    const personagemEsperado = {
      nome: 'Tavares',
      raca: 'Humano',
      tipo: 'NORMAL',
      equipamentos: [],
      inventario: [],
      dinheiro: 150,
      nivel: 1,
      vida: 5,
      vigor: 4,
      dano: 5,
    };

    const personagemAtualizado = await receberRecompensa(
      personagem,
      selecionarMissao(listaMissoes, 10),
    );

    expect(personagemAtualizado).toEqual(personagemEsperado);
  });
});
