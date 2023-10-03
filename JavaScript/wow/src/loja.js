import possuiExpansao from './services/possui-expansao/possuiExpansao';
import {
  removerDinheiro,
  adicionarItemInventario,
  equiparItem,
  desequiparItem,
  removerItemInventario,
  verificaSeOPersonagemJaTemUmItemDoTipo,
  adicionarDinheiro,
} from './personagem';
import indiceObjeto from './services/indice-objeto/indiceObjeto';

import axios from 'axios';

function PersonagemSemDinheiro(itemNome) {
  this.name = 'PersonagemSemDinheiro';
  this.message = `Personagem sem dinheiro suficiente para comprar o item ${itemNome}!`;
}

function PersonagemSemNivelSuficienteParaComprarOItem(itemLvlMinimo) {
  this.name = 'PersonagemSemNivelSuficiente';
  this.message = `Personagem sem nivel suficiente, nivel esperado é ${itemLvlMinimo}!`;
}

export const validarSeTemDinheiroSuficiente = (item, personagem) => {
  if (personagem.dinheiro < item.preco) {
    throw new PersonagemSemDinheiro(item.nome);
  }
  return true;
};

export const substituirAntigoPeloItemComprado = (personagem, novoItem) => {
  try {
    verificaSeOPersonagemJaTemUmItemDoTipo(personagem, novoItem);
    return personagem;
  } catch (error) {
    const itemAntigo = personagem.equipamentos.find((equipamento) => {
      return equipamento.tipo === novoItem.tipo;
    });
    const indiceNovoItem = indiceObjeto(novoItem,personagem.inventario);
    const tirarItemNovoDoInventario = removerItemInventario(
      personagem,
      indiceNovoItem,
    );
    const tirarItemAntigo = desequiparItem(
      tirarItemNovoDoInventario.personagem,
      itemAntigo.tipo,
    );
    const itemAntigoNoInventario = adicionarItemInventario(
      tirarItemAntigo.personagem,
      itemAntigo,
    );
    const itemNovoEquipado = equiparItem(itemAntigoNoInventario, novoItem);
    return itemNovoEquipado;
  }
};

export const validarSeTemNivelSuficienteParaComprarOItem = (
  item,
  personagem,
) => {
  if (personagem.nivel < item.lvlMinimo) {
    throw new PersonagemSemNivelSuficienteParaComprarOItem(item.lvlMinimo);
  }
};

export const efetuarCompraDeItem = (item, personagem, expansoes = []) => {
  const EXPANSAO = 'EXPANSAO';
  validarSeTemDinheiroSuficiente(item, personagem);
  validarSeTemNivelSuficienteParaComprarOItem(item, personagem);

  const personagemDinheiroReduzido = removerDinheiro(personagem, item.preco);

  if (item.tipo === EXPANSAO) {
    return { personagem: personagemDinheiroReduzido, expansao: item };
  }

  possuiExpansao(
    expansoes,
    item.idExpansao,
    'Jogador não possui expansão necessária !!',
  );


  const personagemItemAdiquirido = adicionarItemInventario(
    personagemDinheiroReduzido,
    item,
  );
  const personagemComItemSubstituido = substituirAntigoPeloItemComprado(
     personagemItemAdiquirido,
    item,
  );

  return personagemComItemSubstituido;
}

export const efetuarVendaDeItem = (personagem, item) => {
  validarSeTemNivelSuficienteParaComprarOItem(item, personagem);
  const indice = personagem.inventario.findIndex(
    (itemInventario) => itemInventario.nome === item.nome,
  );
  let { personagem: personagemAtualizado, itemRemovido } =
    removerItemInventario(personagem, indice);
  personagemAtualizado = adicionarDinheiro(
    personagemAtualizado,
    itemRemovido.preco / 2,
  );

  return personagemAtualizado;
};

export const buscaItens = async () => {
  const { data } = await axios.get(
    'https://hectortavares.github.io/Trainee/Conteudo/woe/store.json',
  );

  return data;
};

export const retornarItensNaoAdquiridos = (personagem, expansoes, itens) => {
  const itensAdquiridos = [
    ...personagem.equipamentos,
    ...personagem.inventario,
    ...expansoes,
  ];
  const idItensAdquiridos = itensAdquiridos.map((item) => item.id);

  return itens.filter((item) => !idItensAdquiridos.includes(item.id));
};
