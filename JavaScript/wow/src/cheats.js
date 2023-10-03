import {
  adicionarDinheiro,
  adicionarItemInventario,
  subirNivel,
} from './personagem';

export const cheats = (cheat, personagem, personagens) => {
  let personagensAtualizado = { ...personagens };
  let personagemAtualizado = { ...personagem };
  let cheatAtivado = false;
  if (cheat === 'WILLIDAN') {
    necessitaDePersonagem(personagem);
    personagemAtualizado = subirNiveis([{ ...personagem }], 20)[0];
    cheatAtivado = 1;
  } else if (cheat === 'GUSTHRALL') {
    necessitaDePersonagem(personagem);
    personagemAtualizado = darDinheiro([{ ...personagem }], 2000)[0];
    cheatAtivado = 1;
  } else if (cheat === 'ANDUINNUNES') {
    personagensAtualizado = darDinheiro(personagens, 20000);
    cheatAtivado = 2;
  } else if (cheat === 'JULICHKING') {
    personagensAtualizado = subirNiveis(personagens, 5);
    cheatAtivado = 2;
  } else if (cheat === 'KEVINERZUL') {
    necessitaDePersonagem(personagem);
    personagemAtualizado = darArcoCallbackInfinito(personagem);
    cheatAtivado = 1;
  } else if (
    cheat === 'FABYOGGSARON' ||
    cheat === 'PABLOTHAR' ||
    cheat === 'VITOREXXAR'
  ) {
    necessitaDePersonagem(personagem);
    personagemAtualizado = darTalismaDoPolimorfismo(personagem);
    cheatAtivado = 1;
  } else if (cheat === 'ZORZARTHAS' || cheat === 'DIANDRAKA') {
    necessitaDePersonagem(personagem);
    personagemAtualizado = darTalismaIndexado(personagem);
    cheatAtivado = 1;
  } else if (cheat === 'SERGIORGRIM') {
    necessitaDePersonagem(personagem);
    personagemAtualizado = darArmaduraDeFlexbox(personagem);
    cheatAtivado = 1;
  }

  return {
    personagem: personagemAtualizado,
    personagens: personagensAtualizado,
    cheatAtivado,
  };
};

const subirNiveis = (personagens, niveis) => {
  return personagens.map((personagem) => subirNivel(personagem, niveis));
};

const darDinheiro = (personagens, quantia) => {
  return personagens.map((personagem) =>
    adicionarDinheiro(personagem, quantia),
  );
};

const darArcoCallbackInfinito = (personagem) => {
  const arcoDoCallbackInfinito = {
    nome: 'Arco do callback infinito',
    tipo: 'DANO',
    aprimoramento: '2000',
  };

  return adicionarItemInventario(personagem, arcoDoCallbackInfinito);
};

const darTalismaDoPolimorfismo = (personagem) => {
  const talismaDoPolimorfismo = {
    nome: 'Talismã do Polimorfismo',
    tipo: 'VIDA',
    aprimoramento: '2000',
  };

  return adicionarItemInventario(personagem, talismaDoPolimorfismo);
};

const darTalismaIndexado = (personagem) => {
  const talismaIndexado = {
    nome: 'Talismã Indexado',
    tipo: 'VIDA',
    aprimoramento: '2000',
  };

  return adicionarItemInventario(personagem, talismaIndexado);
};

const darArmaduraDeFlexbox = (personagem) => {
  const armaduraDeFlexbox = {
    nome: 'Armadura de Flexbox',
    tipo: 'VIGOR',
    aprimoramento: '2000',
  };

  return adicionarItemInventario(personagem, armaduraDeFlexbox);
};

const necessitaDePersonagem = (personagem) => {
  if (!personagem) {
    throw new Error(
      'Para usar esse cheat é necessário estar utilizando um personagem!',
    );
  }
};
