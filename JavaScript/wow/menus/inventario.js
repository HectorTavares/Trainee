import {
  adicionarItemInventario,
  desequiparItem,
  equiparItem,
  removerItemInventario,
} from '../src/personagem';
import sleep from '../src/services/delay/sleep';
import { getPersonagem, setPersonagem } from '../state/state';
import { lerInput } from './lerInput';
import erro from './mensagens/erro';
import { menuInicialPersonagem } from './menuPersonagem';

export const menuInventario = async () => {
  console.clear();
  const personagem = getPersonagem();

  if (
    personagem.equipamentos.length == 0 &&
    personagem.inventario.length == 0
  ) {
    erro('Você ainda não possui itens');
    await sleep(3000);
    return menuInicialPersonagem();
  }

  console.log('-----------------------------------------------------');
  console.log('|> BEM VINDO AO INVENTÁRIO');
  console.log('-----------------------------------------------------');

  console.log('Itens Equipados Atualmente');
  console.table(personagem.equipamentos);
  console.log('Itens guardados no seu inventario');
  console.table(personagem.inventario);

  console.log('\nGostaria de mudar algum item?');
  const resposta = await lerInput('1- Sim 2-Não');

  switch (resposta) {
    case '1':
      menuTrocaItens();
      break;
    case '2':
      menuInicialPersonagem();
      break;
    default:
      menuInventario();
      break;
  }
};

const menuTrocaItens = async () => {
  console.log('\nO que você deseja fazer ?');
  const resposta = await lerInput(
    '1- Mover do inventário para equipamentos 2- Mover dos equipamentos para o inventário',
  );

  switch (resposta) {
    case '1':
      moverInventarioParaEquipamentos();
      break;
    case '2':
      moverEquipamentoParaInventario();
      break;
    default:
      menuInventario();
      break;
  }
};

const moverInventarioParaEquipamentos = async () => {
  console.clear();
  const { inventario } = getPersonagem();
  console.table(inventario);
  if (inventario.length < 1) {
    erro('Você não possui itens no inventário');
    await sleep(1500);
    return menuInventario();
  }
  const resposta = await lerInput(
    'Informe o index do item que você deseja equipar',
  );
  if (parseInt(resposta) > inventario.length - 1) {
    erro('Valor inválido');
    await sleep(1000);
    return moverInventarioParaEquipamentos();
  }
  const { personagem, itemRemovido } = removerItemInventario(
    getPersonagem(),
    parseInt(resposta),
  );
  try {
    const personagemComItemEquipado = equiparItem(personagem, itemRemovido);
    setPersonagem(personagemComItemEquipado);
    return menuInventario();
  } catch (error) {
    erro(error.message);
    await sleep(3000);
    return menuInventario();
  }
};

const moverEquipamentoParaInventario = async () => {
  console.clear();
  const { equipamentos } = getPersonagem();
  console.table(equipamentos);
  if (equipamentos.length < 1) {
    erro('Você não possui itens no inventário');
    await sleep(1500);
    return menuInventario();
  }
  const resposta = await lerInput(
    'Informe o index do item que você deseja mover para o inventário',
  );
  if (parseInt(resposta) > equipamentos.length - 1) {
    erro('Valor inválido');
    await sleep(1000);
    return moverEquipamentoParaInventario();
  }
  const tipo = getPersonagem().equipamentos[parseInt(resposta)].tipo;
  const { personagem, itemDesequipado } = desequiparItem(getPersonagem(), tipo);
  const personagemComItemNoInventario = adicionarItemInventario(
    personagem,
    itemDesequipado,
  );
  setPersonagem(personagemComItemNoInventario);
  return menuInventario();
};
