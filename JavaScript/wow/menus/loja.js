import {
  efetuarCompraDeItem,
  efetuarVendaDeItem,
  retornarItensNaoAdquiridos,
  buscaItens,
} from '../src/loja';
import sleep from '../src/services/delay/sleep';
import { lerInput } from './lerInput';
import erroMensagem from './mensagens/erro';
import avisoMensagem from './mensagens/aviso';
import {
  getPersonagem,
  getExpansoes,
  setPersonagem,
  setExpansoes,
} from '../state/state';
import { menuInicialPersonagem } from './menuPersonagem';
import { salvarExpansao } from '../src/db';
import informacao from './mensagens/informacao';

export const menuLoja = async () => {
  console.clear();
  console.log('-----------------------------');
  console.log('|> BEM VINDO AO MENU DA LOJA');
  console.log('----------------------------');

  const personagemEscolhido = getPersonagem();
  const expansoes = getExpansoes();
  const itens = await buscaItens();

  const resposta = await lerInput('1- Comprar  2- Vender  3- Sair');

  switch (resposta) {
    case '1':
      const itensASeremOfertados = retornarItensNaoAdquiridos(
        personagemEscolhido,
        expansoes,
        itens,
      );
      await menuCompra(expansoes, itensASeremOfertados);
      break;
    case '2':
      await menuVenda(personagemEscolhido);
      break;

    case '3':
      avisoMensagem('Saindo da loja');
      await menuInicialPersonagem();
      break;

    default:
      erroMensagem(`A opção ${resposta} não existe!`);
      menuLoja(personagemEscolhido, expansoes, itens);
      break;
  }
};

const menuCompra = async (expansoes, itensASeremOfertados) => {
  console.table(itensASeremOfertados, [
    'nome',
    'tipo',
    'preco',
    'aprimoramento',
    'lvlMinimo',
    'idExpansao',
  ]);
  const PERSONAGEM = getPersonagem()
  informacao(`Atualmente você tem ${PERSONAGEM.dinheiro} de dinheiro`);

  const resposta = await lerInput(
    'Digite o index do item a ser comprado ou digite S para voltar',
  );
  if (isNaN(resposta)) {
    return menuLoja();
  }

  const itemASerComprado = itensASeremOfertados[parseInt(resposta)];

  if (!itemASerComprado) {
    erroMensagem('Item não existe!');
    await sleep(2000);
    return menuCompra(expansoes, itensASeremOfertados);
  }

  try {
    const personagemItemAdiquirido = efetuarCompraDeItem(
      itemASerComprado,
      getPersonagem(),
      expansoes,
    );
    if (personagemItemAdiquirido.expansao) {
      console.log('---EXPANSAO ADIQUIRIDA---')
      console.table(personagemItemAdiquirido.expansao);
      setPersonagem(personagemItemAdiquirido.personagem);
      setExpansoes([...getExpansoes(), personagemItemAdiquirido.expansao]);
      salvarExpansao(getExpansoes());
    } else {
      console.log('---ITEM ADIQUIRIDO---')
      setPersonagem(personagemItemAdiquirido);
    }
    await sleep(2000);
    menuLoja();
  } catch (error) {
    erroMensagem(error.message);
    await sleep(2000);
    return menuLoja();
  }
};

const menuVenda = async (personagemEscolhido) => {
  console.clear();
  console.table(personagemEscolhido.inventario, [
    'nome',
    'tipo',
    'preco',
    'aprimoramento',
    'lvlMinimo',
  ]);
  if (personagemEscolhido.inventario < 1) {
    erroMensagem('Você não possui itens no inventário !');
    await sleep(2000);
    return menuLoja();
  }
  const resposta = await lerInput(
    'Digite o index do item a ser vendido, você receberá metade do valor dele ou digite S para voltar.',
  );
  if (isNaN(resposta)) {
    return menuLoja();
  }

  const itemASerVendido = personagemEscolhido.inventario[parseInt(resposta)];

  if (!itemASerVendido) {
    erroMensagem('Item a ser vendido não encontrado!!');
    await sleep(2000);
    return await menuVenda(personagemEscolhido);
  }

  try {
    const personagemItemVendido = efetuarVendaDeItem(
      personagemEscolhido,
      itemASerVendido,
    );
    setPersonagem(personagemItemVendido);
    console.log('---ITEM VENDIDO---');
    await sleep(2000);
    return menuLoja();
  } catch (error) {
    erroMensagem(error.message);
    await sleep(2000);
    return menuLoja();
  }
};
