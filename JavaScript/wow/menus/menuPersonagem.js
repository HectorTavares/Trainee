import sleep from '../src/services/delay/sleep';
import { getPersonagem, setPersonagem } from '../state/state';
import { menuBatalha } from './batalha';
import { menuInventario } from './inventario';
import { lerInput } from './lerInput';
import { menuLoja } from './loja';
import { menuMissoes } from './missao';
import { menuPrincipal } from './principal';

export const menuInicialPersonagem = async () => {
  console.clear();
  const PERSONAGEM = getPersonagem();
  console.log(`Olá ${PERSONAGEM.nome}, o que vamos fazer hoje ?\n`);
  const resp = await lerInput(
    '1- Batalhar 2- Missões 3- Loja 4- Inventário 5- Informações Personagem 6- Voltar',
  );
  switch (resp) {
    case '1':
      menuBatalha();
      break;
    case '2':
      menuMissoes();
      break;
    case '3':
      menuLoja();
      break;
    case '4':
      menuInventario();
      break;
    case '5':
      infoPersonagem();
      break;
    case '6':
      setPersonagem(null);
      menuPrincipal();
      break;
    default:
      await sleep(1000);
      menuInicialPersonagem();
      break;
  }
};

const infoPersonagem = async () => {
  console.log('-----------------------------------------------------');
  console.log('|> BEM VINDO A INFORMAÇÕES DO PERSONAGEM');
  console.log('-----------------------------------------------------');
  const { equipamentos, inventario, ...personagem } = getPersonagem();
  console.log('PERSONAGEM');
  console.table(personagem);
  console.log('EQUIPAMENTOS');
  console.table(equipamentos);
  console.log('INVENTÁRIO');
  console.table(inventario);
  await lerInput('Digite qualquer tecla para voltar ao menu anterior!');
  menuInicialPersonagem();
};
