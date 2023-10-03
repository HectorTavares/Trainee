import axios from 'axios';
import possuiExpansao from './services/possui-expansao/possuiExpansao';

export const buscaPersonagens = async () => {
  const { data } = await axios.get(
    'https://hectortavares.github.io/Trainee/Conteudo/woe/races.json',
  );

  return data;
};

export const buscaPersonagem = async (id) => {
  const personagens = await buscaPersonagens();
  return personagens.find((personagem) => personagem.id === id);
};

export const criaPersonagem = (
  personagem,
  nome,
  personagens = [],
  expansoes = [],
) => {
  checaNomeUnico(personagens, nome);
  const {
    vidaBase,
    vigorBase,
    danoBase,
    raca,
    tipo,
    lvlMinimoParaObter,
    idExpansao,
  } = personagem;
  const novoPersonagem = {
    nome,
    raca,
    equipamentos: [],
    dinheiro: 0,
    nivel: 1,
    vida: vidaBase,
    vigor: vigorBase,
    dano: danoBase,
    tipo,
    inventario: [],
  };

  checaLevelMinimo(personagens, lvlMinimoParaObter);
  possuiExpansao(
    expansoes,
    idExpansao,
    'UM MOMENTO AMIGO! Você ainda não tem a expansão necessária para utilizar essa raça, escolha outra raça ou então compre a expansão com o Tiozinho da Loja.',
  );

  return novoPersonagem.tipo === 'NORMAL'
    ? novoPersonagem
    : subirNivel(novoPersonagem, 9);
};

const checaLevelMinimo = (personagens, lvlMinimo) => {
  let personagemComNivelSuficiente = false;
  const msg =
    'Você ainda não possui nível para utilizar essa raça, mas não se preocupe, ainda existem outras raças para você jogar até desbloquear essa :D';
  if (lvlMinimo === undefined) {
    return;
  }
  personagens.forEach((personagem) => {
    if (personagem.nivel >= lvlMinimo) {
      personagemComNivelSuficiente = true;
    }
  });
  if (!personagemComNivelSuficiente) {
    throw new Error(msg);
  }
};

export const subirNivel = (personagem, niveis = 1) => {
  const personagemAtualizado = { ...personagem };

  if (niveis > 1) {
    const upVigor = Math.floor(niveis / 2) * 1;
    const upVida = Math.floor(niveis / 2) * 2;
    personagemAtualizado.vigor += upVigor;
    personagemAtualizado.vida += upVida;
    personagemAtualizado.nivel += niveis;
  } else if (niveis === 1) {
    personagemAtualizado.nivel += niveis;
    if (personagemAtualizado.nivel % 2 != 0) {
      personagemAtualizado.vigor += 1;
      personagemAtualizado.vida += 2;
    }
  }

  return personagemAtualizado;
};

export const adicionarDinheiro = (personagem, quantia) => {
  const personagemAtualizado = { ...personagem };
  personagemAtualizado.dinheiro += quantia;
  return personagemAtualizado;
};

export const removerDinheiro = (personagem, quantia) => {
  const personagemAtualizado = { ...personagem };
  personagemAtualizado.dinheiro -= quantia;
  return personagemAtualizado;
};

export const equiparItem = (personagem, item) => {
  const personagemAtualizado = { ...personagem };
  const { id, nome, tipo, preco, aprimoramento, lvlMinimo } = item;
  const novoItem = {
    id,
    nome,
    tipo,
    preco,
    aprimoramento,
    lvlMinimo: lvlMinimo || 1,
  };

  verificaSeOPersonagemJaTemUmItemDoTipo(personagem, novoItem);

  if (personagemAtualizado.nivel < novoItem.lvlMinimo) {
    throw new Error(
      'O seu personagem não tem nível suficiente para equipar esse item.',
    );
  }
  const atributo = novoItem.tipo.toLowerCase();

  personagemAtualizado.equipamentos.push(novoItem);

  personagemAtualizado[atributo] += parseInt(novoItem.aprimoramento);

  return personagemAtualizado;
};

export const desequiparItem = (personagem, tipo) => {
  const personagemAtualizado = { ...personagem };

  const index = personagemAtualizado.equipamentos.findIndex(
    (equipamento) => equipamento.tipo === tipo,
  );

  const atributo = tipo.toLowerCase();
  const itemDesequipado = personagemAtualizado.equipamentos.splice(index, 1);

  personagemAtualizado[atributo] -= parseInt(itemDesequipado[0].aprimoramento);

  return {
    personagem: personagemAtualizado,
    itemDesequipado: itemDesequipado[0],
  };
};

export const adicionarItemInventario = (personagem, item) => {
  const personagemAtualizado = { ...personagem };
  const { id, nome, tipo, preco, aprimoramento, lvlMinimo } = item;
  const novoItem = {
    id: id || undefined,
    nome,
    tipo,
    preco: preco || 0,
    aprimoramento,
    lvlMinimo: lvlMinimo || 1,
  };

  if (
    personagemAtualizado.equipamentos.length +
      personagemAtualizado.inventario.length <
    6
  ) {
    personagemAtualizado.inventario.push(novoItem);
  } else {
    throw new Error(
      'Número máximo de itens atingido, venda algum item antes de adquirir um novo.',
    );
  }

  return personagemAtualizado;
};

export const removerItemInventario = (personagem, indice) => {
  const personagemAtualizado = { ...personagem };

  const itemRemovido = personagemAtualizado.inventario.splice(indice, 1);

  return {
    personagem: { ...personagemAtualizado },
    itemRemovido: itemRemovido[0],
  };
};

const checaNomeUnico = (personagens, nome) => {
  const nomeJaExiste = personagens.find(
    (personagem) => personagem.nome === nome,
  )
    ? true
    : false;
  if (nomeJaExiste) {
    throw new Error(
      'Já existe um personagem com esse nome, por favor escolha outro nome.',
    );
  }
};

export const verificaSeOPersonagemJaTemUmItemDoTipo = (personagem, item) => {
  if (
    personagem.equipamentos.find(
      (equipamento) => equipamento.tipo === item.tipo,
    ) !== undefined
  ) {
    throw new Error(
      `Você já possui um item do tipo ${item.tipo} equipado, mova-o para o seu inventário antes de tentar equipar um novo item desse tipo.`,
    );
  }
};
