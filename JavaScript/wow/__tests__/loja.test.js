import {
  adicionarItemInventario,
  equiparItem,
  criaPersonagem,
} from '../src/personagem';
import {
  efetuarVendaDeItem,
  efetuarCompraDeItem,
  substituirAntigoPeloItemComprado,
  retornarItensNaoAdquiridos
} from '../src/loja';

describe('Suite teste da loja', () => {
  it('Deve conseguir vender um item e receber metade do preço de volta', () => {
    const humano = {
      id: 2,
      raca: 'Humano',
      vidaBase: 5,
      vigorBase: 4,
      danoBase: 5,
      tipo: 'NORMAL',
    };
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      preco: 2000,
      aprimoramento: 90,
    };
    let personagem = criaPersonagem(humano, 'Menitles');
    personagem = adicionarItemInventario(personagem, item);

    personagem = efetuarVendaDeItem(personagem, item);

    expect(personagem.dinheiro).toBe(1000);
  });

  it('Deve validar o nível do personagem para permitir a venda de itens com um nível mínimo necessário', () => {
    const humano = {
      id: 2,
      raca: 'Humano',
      vidaBase: 5,
      vigorBase: 4,
      danoBase: 5,
      tipo: 'NORMAL',
    };
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      preco: 2000,
      aprimoramento: 90,
      lvlMinimo: 10,
    };
    let personagem = criaPersonagem(humano, 'Menitles');
    personagem = adicionarItemInventario(personagem, item);

    personagem = expect(() => efetuarVendaDeItem(personagem, item)).toThrow();
  });

  it('Deve conseguir comprar um item do tipo VIGOR com sucesso', () => {
    const item = {
      id: 2,
      nome: 'Brasa de bami',
      tipo: 'VIGOR',
      preco: 10,
      aprimoramento: 4,
      lvlMinimo: 0,
    };

    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [],
      equipamentos: [],
    };

    const respostaEsperada = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 10,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [
        {
          id: 2,
          nome: 'Brasa de bami',
          tipo: 'VIGOR',
          preco: 10,
          aprimoramento: 4,
          lvlMinimo: 1,
        },
      ],
      equipamentos: [],
    };

    const respostaObitida = efetuarCompraDeItem(item, personagem);

    expect(respostaObitida).toEqual(respostaEsperada);
  });

  it('Deve conseguir comprar um item do tipo VIDA com sucesso', () => {
    const item = {
      id: 2,
      nome: 'Warmog',
      tipo: 'VIDA',
      preco: 10,
      aprimoramento: '4',
      lvlMinimo: 0,
    };

    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [],
      equipamentos: [],
    };
    const respostaObitida = efetuarCompraDeItem(item, personagem);

    const respostaEsperada = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 10,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [
        {
          id: 2,
          nome: 'Warmog',
          tipo: 'VIDA',
          preco: 10,
          aprimoramento: '4',
          lvlMinimo: 1,
        },
      ],
      equipamentos: [],
    };

    expect(respostaObitida).toEqual(respostaEsperada);
  });

  it('Deve conseguir comprar um item do tipo DANO com sucesso', () => {
    const item = {
      id: 5,
      nome: 'Tiamat',
      tipo: 'DANO',
      preco: 10,
      aprimoramento: '3',
    };

    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [],
      equipamentos: [],
    };

    const respostaEsperada = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 10,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [
        {
          id: 5,
          nome: 'Tiamat',
          tipo: 'DANO',
          preco: 10,
          aprimoramento: '3',
          lvlMinimo: 1,
        },
      ],
      equipamentos: [],
    };
    expect(efetuarCompraDeItem(item, personagem)).toEqual(respostaEsperada);
  });

  it('Deve conseguir comprar um item do tipo EXPANSAO com sucesso', () => {
    const item = {
      nome: 'Burning Crusade',
      idExpansao: 1,
      tipo: 'EXPANSAO',
      preco: 200000,
    };

    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 400000,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [],
      equipamentos: [],
    };

    const resultadoEsperado = {
      "expansao":
        { "idExpansao": 1, "nome": "Burning Crusade", "preco": 200000, "tipo": "EXPANSAO" },
      "personagem":
        { "dano": 15, "dinheiro": 200000, "equipamentos": [], "id": 2, "inventario": [], "nivel": 3, "nome": "Shen", "vida": 20, "vigor": 4 }
    };

    expect(efetuarCompraDeItem(item, personagem)).toEqual(resultadoEsperado);
  });

  it('Deve conseguir comprar um equipamento de alguma expansão apenas se já tiver obitdo a expansão', () => {
    const item = {
      id: 5,
      nome: 'Death Dance',
      tipo: 'DANO',
      preco: 10,
      aprimoramento: '3',
      idExpansao: 1,
      lvlMinimo: 1,
    };

    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [],
      equipamentos: [],
    };

    const respostaEsperada = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 10,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [
        {
          id: 5,
          nome: 'Death Dance',
          tipo: 'DANO',
          preco: 10,
          aprimoramento: '3',
          lvlMinimo: 1,
        },
      ],
      equipamentos: [],
    };

    expect(efetuarCompraDeItem(item, personagem, [{ idExpansao: 1 }])).toEqual(
      respostaEsperada,
    );
  });

  it('Deve subtituir um item equipado se o item recém comprado for do mesmo tipo que o que já está sendo usado', () => {
    const item = {
      id: 5,
      nome: 'Death Dance',
      tipo: 'DANO',
      preco: 10,
      aprimoramento: '20',
      lvlMinimo: 1,
    };

    const item2 = {
      id: 3,
      nome: 'Tiamat',
      tipo: 'DANO',
      preco: 10,
      aprimoramento: '3',
    };

    const item3 = {
      id: 2,
      nome: 'Warmog',
      tipo: 'VIDA',
      preco: 10,
      aprimoramento: '4',
      lvlMinimo: 0,
    };

    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [],
      equipamentos: [],
    };

    const novoPersonagem = equiparItem(personagem, item2);
    const novoPersonagem2 = adicionarItemInventario(novoPersonagem, item3);

    const novoPersonagem3 = adicionarItemInventario(novoPersonagem2, item);
    const respostaEsperada = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 35,
      inventario: [
        {
          id: 2,
          nome: 'Warmog',
          tipo: 'VIDA',
          preco: 10,
          aprimoramento: '4',
          lvlMinimo: 1,
        },
        {
          id: 3,
          nome: 'Tiamat',
          tipo: 'DANO',
          preco: 10,
          aprimoramento: '3',
          lvlMinimo: 1,
        },
      ],
      equipamentos: [
        {
          id: 5,
          nome: 'Death Dance',
          tipo: 'DANO',
          preco: 10,
          aprimoramento: '20',
          lvlMinimo: 1,
        },
      ],
    };

    expect(substituirAntigoPeloItemComprado(novoPersonagem3, item)).toEqual(
      respostaEsperada,
    );
  });



  it('Deve retornar itens não adiquiridos', () => {
    const item = {
      id: 5,
      nome: 'Death Dance',
      tipo: 'DANO',
      preco: 10,
      aprimoramento: '20',
      lvlMinimo: 1,
    };

    const item2 = {
      id: 3,
      nome: 'Tiamat',
      tipo: 'DANO',
      preco: 10,
      aprimoramento: '3',
    };

    const item3 = {
      id: 2,
      nome: 'Warmog',
      tipo: 'VIDA',
      preco: 10,
      aprimoramento: '4',
      lvlMinimo: 0,
    };

    const todasExpansoes = [{
      nome: 'Burning Crusaders',
      id: 1
    }, {
      nome: 'Modern Crusaders',
      id: 2
    }, {
      nome: 'Stardust Crusaders',
      id: 3
    }
    ]

    const itens = [item, item2, item3, ...todasExpansoes];
    const expansoesJogador = [{ id: 1 }];
    const personagem = {
      id: 2,
      nome: 'Shen',
      nivel: 3,
      dinheiro: 20,
      vida: 20,
      vigor: 4,
      dano: 15,
      inventario: [item2],
      equipamentos: [],
    };

    const resultadoEsperado = [
      {
        id: 5,
        nome: 'Death Dance',
        tipo: 'DANO',
        preco: 10,
        aprimoramento: '20',
        lvlMinimo: 1
      },
      {
        id: 2,
        nome: 'Warmog',
        tipo: 'VIDA',
        preco: 10,
        aprimoramento: '4',
        lvlMinimo: 0
      },
      { nome: 'Modern Crusaders', id: 2 }
    ]

    expect(retornarItensNaoAdquiridos(personagem, expansoesJogador, itens)).toEqual(resultadoEsperado);


  })
});
