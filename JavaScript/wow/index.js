import { menuPrincipal } from './menus/principal';
import { buscaPersonagensSalvos, buscarExpansoesSalvas } from './src/db';
import { setExpansoes, setPersonagens } from './state/state';

const main = async () => {
  await carregaDados();
  await menuPrincipal();
};

const carregaDados = async () => {
  setPersonagens(buscaPersonagensSalvos());
  setExpansoes(buscarExpansoesSalvas());
};

main();
