import {
  calcularODanoRecebido,
  verificarSeVigorDoPersonagensEhMaisAltoQueODano,
  batalha,
  definirOsTurnos,
  definirQuemAtacaPrimeiro,
  executarLuta,
  receberDano
} from '../src/batalha';

describe('Suite teste das batalhas', () => {
  it('Deve calcular o dano a ser recebido corretamente', () => {
    const danoDoPersonagemAtacante = 10;
    const vigorDoPersonagemDefensivo = 8;
    const danoRecebido = calcularODanoRecebido(
      danoDoPersonagemAtacante,
      vigorDoPersonagemDefensivo,
    );

    const resultadoEsperado = 2;

    expect(danoRecebido).toEqual(resultadoEsperado);
  });

  it('Deve retornar 0 caso o dano seja menor que o vigor', () => {
    const danoDoPersonagemAtacante = 2;
    const vigorDoPersonagemDefensivo = 10;
    const danoRecebido = calcularODanoRecebido(
      danoDoPersonagemAtacante,
      vigorDoPersonagemDefensivo,
    );

    const resultadoEsperado = 0;

    expect(danoRecebido).toEqual(resultadoEsperado);
  });

  it('Deve retornar false caso os dois personagens consiguao causar dano na batalha', () => {
    const personagemA = {
      dano: 4,
      vigor: 2,
    };

    const personagemB = {
      dano: 5,
      vigor: 1,
    };

    const resultadoObito = verificarSeVigorDoPersonagensEhMaisAltoQueODano(
      personagemA,
      personagemB,
    );

    expect(resultadoObito).toBeFalsy();
  });

  it('Deve lancar um erro quando os dois tiverem o vigor mais alto que o dano', () => {
    const personagemA = {
      dano: 4,
      vigor: 20,
    };

    const personagemB = {
      dano: 5,
      vigor: 10,
    };

    expect(() => {
      verificarSeVigorDoPersonagensEhMaisAltoQueODano(personagemA, personagemB);
    }).toThrowError('Empate, nenhum personagem consegue dar dano!');
  });

  it('Deve retornar false quando pelo menos um personagem tiver o dano maior que vigor inimigo', () => {
    const personagemA = {
      dano: 4,
      vigor: 10,
    };

    const personagemB = {
      dano: 20,
      vigor: 8,
    };

    const resultadoObitido = verificarSeVigorDoPersonagensEhMaisAltoQueODano(
      personagemA,
      personagemB,
    );

    expect(resultadoObitido).toBeFalsy();
  });

  it('Deve conseguir declarar empate em uma batalha', () => {
    const personagemA = {
      dano: 4,
      vigor: 20,
    };

    const personagemB = {
      dano: 5,
      vigor: 10,
    };

    expect(() => {
      batalha(personagemA, personagemB);
    }).toThrowError('Empate, nenhum personagem consegue dar dano!');
  });

  it('Deve conseguir finalizar a batalha e obter um vencedor com sucesso', () => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 10,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
    };

    expect(batalha(personagemA, personagemB).nome).toEqual(personagemB.nome);
  });

  it('Deve retornar o personagem com o dano recebido corretamente', () => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 10,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
    };

    const resultadoRecebido = receberDano(personagemA, personagemB);

    const resultadoEsperado = {
      nome: 'Personagem2',
      vida: 9,
      dano: 2,
      vigor: 1,
    };

    expect(resultadoRecebido).toEqual(resultadoEsperado);
  });

  it('Deve definir o turno corretamente', () => {
    const resultadoObitido = definirOsTurnos(0);
    expect(resultadoObitido).not.toEqual(0);
  });

  it('Deve definir o primeiro a atacar corretamente', () => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 10,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
    };
    const resultadoObitido = definirQuemAtacaPrimeiro(
      personagemA,
      personagemB,
      2,
    );

    expect(resultadoObitido).toEqual(personagemB);
  });

  it('Deve retornar corretamente o dano recebido pelos personagens na ordem correta', () => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 10,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
    };

    const personagens = [personagemA, personagemB];

    const resultadoEsperado = [
      { nome: 'Personagem2', vida: 9, dano: 2, vigor: 1 },
      { nome: 'Personagem1', vida: 8, dano: 2, vigor: 0 },
    ];

    expect(executarLuta(personagens, personagemA)).toEqual(resultadoEsperado);
  });

  it('Deve subir o nivel do personagem corretamente após vencer uma batalha', () => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 10,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
      nivel: 3,
    };

    const resultadoEsperado = {
      ...personagemB,
      vida: 10,
      vigor: 1,
      nivel: 4,
    };

    expect(batalha(personagemA, personagemB)).toEqual(resultadoEsperado);
  });


  it('Deve retonar quem ataca primeiro corretamente se for o personagemOPrimeiro ',() => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 10,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
    };
    const resultadoObitido = definirQuemAtacaPrimeiro(
      personagemA,
      personagemB,
      1,
    );

    expect(resultadoObitido).toEqual(personagemA);
  })


  it('Deve impedir que ataque se tiver finda igual a zero',() => {
    const personagemA = {
      nome: 'Personagem1',
      vida: 0,
      dano: 2,
      vigor: 0,
    };

    const personagemB = {
      nome: 'Personagem2',
      vida: 10,
      dano: 2,
      vigor: 1,
    };

    const resultadoObito = receberDano(personagemA, personagemB);

    expect(resultadoObito).toEqual(personagemB);

  })
});
