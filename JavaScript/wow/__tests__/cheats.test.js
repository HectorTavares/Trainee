import { cheats } from '../src/cheats';
import { criaPersonagem } from '../src/personagem';

describe('Suite de teste dos cheats', () => {
  let HUMANO1;
  let HUMANO2;

  beforeEach(() => {
    HUMANO1 = JSON.parse(
      JSON.stringify({
        id: 2,
        raca: 'Humano',
        vidaBase: 5,
        vigorBase: 4,
        danoBase: 5,
        tipo: 'NORMAL',
      }),
    );

    HUMANO2 = JSON.parse(JSON.stringify(HUMANO1));
  });

  it('Deve conseguir aplicar o cheat WILLIDAN e subir +20 níveis do personagem selecionado', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');

    personagem = cheats('WILLIDAN', personagem).personagem;

    expect(personagem.nivel).toBe(21);
    expect(personagem.vida).toBe(25);
    expect(personagem.vigor).toBe(14);
  });

  it('Deve conseguir aplicar o cheat GUSTHRALL e dar +2000 de dinheiro para o personagem selecionado', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');

    personagem = cheats('GUSTHRALL', personagem).personagem;

    expect(personagem.dinheiro).toBe(2000);
  });

  it('Deve conseguir aplicar o cheat ANDUINNUNES e dar +20000 de dinheiro para todos os personagens', () => {
    let personagem1 = criaPersonagem(HUMANO1, 'Menitles');
    let personagem2 = criaPersonagem(HUMANO2, 'Menitles2');
    let personagens = [{ ...personagem1 }, { ...personagem2 }];

    personagens = cheats('ANDUINNUNES', personagem1, personagens).personagens;

    expect(personagens[0].dinheiro).toBe(20000);
    expect(personagens[1].dinheiro).toBe(20000);
  });

  it('Deve conseguir aplicar o cheat JULICHKING e subir +5 níveis de todos os personagens', () => {
    let personagem1 = criaPersonagem(HUMANO1, 'Menitles');
    let personagem2 = criaPersonagem(HUMANO2, 'Menitles2');
    let personagens = [{ ...personagem1 }, { ...personagem2 }];

    personagens = cheats('JULICHKING', personagem1, personagens).personagens;

    expect(personagens[0].nivel).toBe(6);
    expect(personagens[1].nivel).toBe(6);
  });

  it('Deve conseguir aplicar o cheat KEVINERZUL e receber o item Arco do callback infinito', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const arcoDoCallbackInfinito = {
      nome: 'Arco do callback infinito',
      tipo: 'DANO',
      lvlMinimo: 1,
      aprimoramento: '2000',
      preco: 0,
    };

    personagem = cheats('KEVINERZUL', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(arcoDoCallbackInfinito);
  });

  it('Deve conseguir aplicar o cheat FABYOGGSARON e receber o item Talismã do Polimorfismo', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const talismaDoPolimorfismo = {
      nome: 'Talismã do Polimorfismo',
      tipo: 'VIDA',
      aprimoramento: '2000',
      lvlMinimo: 1,
      preco: 0,
    };

    personagem = cheats('FABYOGGSARON', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(talismaDoPolimorfismo);
  });

  it('Deve conseguir aplicar o cheat PABLOTHAR e receber o item Talismã do Polimorfismo', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const talismaDoPolimorfismo = {
      nome: 'Talismã do Polimorfismo',
      tipo: 'VIDA',
      aprimoramento: '2000',
      lvlMinimo: 1,
      preco: 0,
    };

    personagem = cheats('PABLOTHAR', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(talismaDoPolimorfismo);
  });

  it('Deve conseguir aplicar o cheat VITOREXXAR e receber o item Talismã do Polimorfismo', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const talismaDoPolimorfismo = {
      nome: 'Talismã do Polimorfismo',
      tipo: 'VIDA',
      aprimoramento: '2000',
      lvlMinimo: 1,
      preco: 0,
    };

    personagem = cheats('VITOREXXAR', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(talismaDoPolimorfismo);
  });

  it('Deve conseguir aplicar o cheat ZORZARTHAS e receber o item Talismã Indexado', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const talismaIndexado = {
      nome: 'Talismã Indexado',
      tipo: 'VIDA',
      lvlMinimo: 1,
      aprimoramento: '2000',
      preco: 0,
    };

    personagem = cheats('ZORZARTHAS', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(talismaIndexado);
  });

  it('Deve conseguir aplicar o cheat DIANDRAKA e receber o item Talismã Indexado', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const talismaIndexado = {
      nome: 'Talismã Indexado',
      tipo: 'VIDA',
      aprimoramento: '2000',
      lvlMinimo: 1,
      preco: 0,
    };

    personagem = cheats('DIANDRAKA', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(talismaIndexado);
  });

  it('Deve conseguir aplicar o cheat SERGIORGRIM e receber o item Armadura de Flexbox', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const armaduraDeFlexbox = {
      nome: 'Armadura de Flexbox',
      tipo: 'VIGOR',
      aprimoramento: '2000',
      lvlMinimo: 1,
      preco: 0,
    };

    personagem = cheats('SERGIORGRIM', personagem).personagem;
    expect(personagem.inventario[0]).toEqual(armaduraDeFlexbox);
  });

  it('Não deve modificar o personagem caso o cheat utilizado não seja válido', () => {
    let personagem = criaPersonagem(HUMANO1, 'Menitles');
    const personagemAtualizado = cheats('CHEATERRADO', personagem).personagem;
    expect(personagem).toEqual(personagemAtualizado);
  });

  it('Deve lançar erro ao tentar utilizar cheat que necessita de personagem sem passar o personagem que o cheat deve ser aplicado', () => {
    expect(() => cheats('GUSTHRALL', null, [])).toThrow();
  });
});
