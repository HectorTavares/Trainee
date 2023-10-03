import {
  adicionarDinheiro,
  adicionarItemInventario,
  criaPersonagem,
  desequiparItem,
  equiparItem,
  removerDinheiro,
  removerItemInventario,
  subirNivel,
} from '../src/personagem';

describe('Suite de testes do personagem', () => {
  let HUMANO;
  let FILHO_DA_NOITE;

  beforeEach(() => {
    HUMANO = JSON.parse(
      JSON.stringify({
        id: 2,
        raca: 'Humano',
        vidaBase: 5,
        vigorBase: 4,
        danoBase: 5,
        tipo: 'NORMAL',
      }),
    );

    FILHO_DA_NOITE = JSON.parse(
      JSON.stringify({
        id: 7,
        raca: 'Filho da noite',
        danoBase: 4,
        vidaBase: 5,
        vigorBase: 1,
        tipo: 'ALIADA',
        lvlMinimoParaObter: 23,
        idExpansao: 6,
      }),
    );
  });

  it('Deve conseguir criar um personagem de raça do tipo NORMAL com sucesso e ele deve estar no nível 1', () => {
    const personagem = criaPersonagem(HUMANO, 'Menitles', [], []);
    const personagemEsperado = {
      nome: 'Menitles',
      raca: 'Humano',
      tipo: 'NORMAL',
      equipamentos: [],
      inventario: [],
      dinheiro: 0,
      nivel: 1,
      vida: 5,
      vigor: 4,
      dano: 5,
    };

    expect(personagem).toEqual(personagemEsperado);
  });

  it('Deve conseguir criar um personagem de raça do tipo ALIADA com sucesso e ele deve estar no nível 10', () => {
    const personagem = criaPersonagem(
      FILHO_DA_NOITE,
      'Menitles',
      [{ nivel: 50 }],
      [{ idExpansao: 6 }],
    );

    const personagemEsperado = {
      nome: 'Menitles',
      raca: 'Filho da noite',
      tipo: 'ALIADA',
      equipamentos: [],
      inventario: [],
      dinheiro: 0,
      nivel: 10,
      vida: 13,
      vigor: 5,
      dano: 4,
    };

    expect(personagem).toEqual(personagemEsperado);
  });

  it('Deve lançar exceção ao tentar criar um personagem que necessita de expansão sem ter a expansão', () => {
    const HumanoLvl30 = HUMANO;
    HumanoLvl30.nivel = 30;
    expect(() =>
      criaPersonagem(FILHO_DA_NOITE, 'Menitles', [HumanoLvl30], []),
    ).toThrowError();
  });

  it('Deve ter sucesso ao criar um personagem que necessita de nivel minímo tendo personagem com o nível mínimo exigido', () => {
    const HumanoLvl30 = HUMANO;
    HumanoLvl30.nivel = 30;
    expect(() =>
      criaPersonagem(FILHO_DA_NOITE, 'Menitles', [HumanoLvl30]),
    ).toThrowError();
  });

  it('Deve lançar exceção ao tentar criar um personagem que necessita de nivel minímo sem ter nenhum personagem com o nível mínimo exigido', () => {
    expect(() => criaPersonagem(FILHO_DA_NOITE, 'Menitles')).toThrowError();
  });

  it('Personagem recém criado não deve possuir equipamentos', () => {
    const personagem = criaPersonagem(HUMANO, 'Menitles');

    expect(personagem.equipamentos).toEqual([]);
  });

  it('Personagem recém criado não deve possuir dinheiro', () => {
    const personagem = criaPersonagem(HUMANO, 'Menitles');

    expect(personagem.dinheiro).toBe(0);
  });

  it('Deve receber +2 de vida e +1 de vigor ao subir dois niveis', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    personagem = subirNivel(personagem, 2);

    expect(personagem.vida).toBe(7);
    expect(personagem.vigor).toBe(5);

    personagem = subirNivel(personagem);
    personagem = subirNivel(personagem);

    expect(personagem.vida).toBe(9);
    expect(personagem.vigor).toBe(6);
  });

  it('Deve conseguir criar um personagem com raça de expansão se já possuir a expansão', () => {
    const TROLL_ZANDALARI = {
      id: 12,
      raca: 'Troll Zandalari',
      danoBase: 3,
      vidaBase: 7,
      vigorBase: 5,
      tipo: 'ALIADA',
      lvlMinimoParaObter: 15,
      idExpansao: 5,
    };
    const personagem = criaPersonagem(
      TROLL_ZANDALARI,
      'Menitles',
      [{ nivel: 15 }],
      [{ idExpansao: 5 }],
    );
    const personagemEsperado = {
      nome: 'Menitles',
      raca: 'Troll Zandalari',
      tipo: 'ALIADA',
      equipamentos: [],
      inventario: [],
      dinheiro: 0,
      nivel: 10,
      vida: 15,
      vigor: 9,
      dano: 3,
    };

    expect(personagem).toEqual(personagemEsperado);
  });

  it('Deve conseguir criar um personagem com raça aliada se já possuir outro personagem com o lvl mínimo necessário', () => {
    const DRAENEIS = {
      id: 11,
      raca: 'Draeneis Forjados a Luz',
      danoBase: 4,
      vidaBase: 4,
      vigorBase: 5,
      tipo: 'ALIADA',
      lvlMinimoParaObter: 15,
      idExpansao: 6,
    };
    const personagem = criaPersonagem(
      DRAENEIS,
      'Menitles',
      [{ nivel: 15 }],
      [{ idExpansao: 6 }],
    );
    const personagemEsperado = {
      nome: 'Menitles',
      raca: 'Draeneis Forjados a Luz',
      tipo: 'ALIADA',
      equipamentos: [],
      inventario: [],
      dinheiro: 0,
      nivel: 10,
      vida: 12,
      vigor: 9,
      dano: 4,
    };

    expect(personagem).toEqual(personagemEsperado);
  });

  it('Deve calcular o vigor corretamente com o atributo base de sua raça + equipamentos', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 11,
      nome: 'Bracelete de Chessus',
      tipo: 'VIGOR',
      preco: 2000,
      aprimoramento: 90,
    };

    personagem = equiparItem(personagem, item);

    expect(personagem.vigor).toBe(94);
  });

  it('Deve calcular a vida corretamente com o atributo base de sua raça + equipamentos', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 7,
      nome: 'Talismã de Chessus',
      tipo: 'VIDA',
      preco: 2000,
      aprimoramento: 90,
    };
    personagem = equiparItem(personagem, item);

    expect(personagem.vida).toBe(95);
  });

  it('Deve calcular o dano corretamente com o atributo base de sua raça + equipamentos', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      preco: 2000,
      aprimoramento: 90,
    };
    personagem = equiparItem(personagem, item);

    expect(personagem.dano).toBe(95);
  });

  it('Deve lançar exceção ao tentar equipar um item com nível mínimo maior que o nível do personagem', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      preco: 2000,
      aprimoramento: 90,
      lvlMinimo: 10,
    };

    expect(() => equiparItem(personagem, item)).toThrow();
  });

  it('Personagem deve ter o item desequipado', () => {
    let itemDesequipado;
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      lvlMinimo: 1,
      preco: 2000,
      aprimoramento: 90,
    };
    personagem = equiparItem(personagem, item);
    const resp = desequiparItem(personagem, 'DANO');
    personagem = resp.personagem;
    itemDesequipado = resp.itemDesequipado;

    expect(personagem.equipamentos).toEqual([]);
    expect(itemDesequipado).toEqual(item);
  });

  it('Personagem deve colocar o item no inventario', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      lvlMinimo: 1,
      preco: 2000,
      aprimoramento: 90,
    };
    personagem = adicionarItemInventario(personagem, item);

    expect(personagem.inventario).toEqual([item]);
  });

  it('Personagem deve retirar o item do inventario', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item1 = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      lvlMinimo: 1,
      preco: 2000,
      aprimoramento: 90,
    };
    const item2 = {
      id: 5,
      nome: 'Talismã de vida P',
      tipo: 'VIDA',
      lvlMinimo: 1,
      preco: 40,
      aprimoramento: 3,
    };
    personagem = adicionarItemInventario(personagem, item1);
    personagem = adicionarItemInventario(personagem, item2);

    const res = removerItemInventario(personagem, 1);
    personagem = res.personagem;
    const itemRemovido = res.itemRemovido;

    expect(personagem.inventario).toEqual([item1]);
    expect(itemRemovido).toEqual(item2);
  });

  it('Deve adicionar 2000 de ouro ao personagem', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles', [], []);
    personagem = adicionarDinheiro(personagem, 2000);

    expect(personagem.dinheiro).toBe(2000);
  });

  it('Deve remover 1000 de ouro ao personagem', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles', [], []);
    personagem = adicionarDinheiro(personagem, 2000);
    personagem = removerDinheiro(personagem, 1000);

    expect(personagem.dinheiro).toBe(1000);
  });

  it('Deve lançar exceção ao tentar criar personagem com o mesmo nome de um personagem já existente', () => {
    let personagem1 = criaPersonagem(HUMANO, 'Menitles', [], []);
    expect(() =>
      criaPersonagem(HUMANO, 'Menitles', [{ ...personagem1 }], []),
    ).toThrow();
  });

  it('Deve lançar exceção ao tentar carregar mais itens do que o personagem suporta', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      lvlMinimo: 1,
      preco: 2000,
      aprimoramento: 90,
    };
    personagem = adicionarItemInventario(personagem, item);
    personagem = adicionarItemInventario(personagem, item);
    personagem = adicionarItemInventario(personagem, item);
    personagem = adicionarItemInventario(personagem, item);
    personagem = adicionarItemInventario(personagem, item);
    personagem = adicionarItemInventario(personagem, item);

    expect(() => adicionarItemInventario(personagem, item)).toThrow();
  });

  it('Deve lançar exceção ao tentar equipar dois itens do mesmo tipo', () => {
    let personagem = criaPersonagem(HUMANO, 'Menitles');
    const item = {
      id: 3,
      nome: 'Espada de Chessus',
      tipo: 'DANO',
      lvlMinimo: 1,
      preco: 2000,
      aprimoramento: 90,
    };

    personagem = equiparItem(personagem, item);
    expect(() => equiparItem(personagem, item)).toThrow();
  });
});
