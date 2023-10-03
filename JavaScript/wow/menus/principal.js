import sleep from '../src/services/delay/sleep';
import {
  buscaPersonagem,
  buscaPersonagens,
  criaPersonagem,
} from '../src/personagem';
import erro from './mensagens/erro';
import sucesso from './mensagens/sucesso';
import aviso from './mensagens/aviso';
import { menuInicialPersonagem } from './menuPersonagem';
import {
  getExpansoes,
  getPersonagens,
  setPersonagem,
  setPersonagens,
} from '../state/state';
import { lerInput } from './lerInput';
import { limpaDados } from '../src/db';

export const menuPrincipal = async () => {
  console.clear();

  console.log('\nBem-vindo de volta, o que deseja fazer hoje ?\n');
  const resposta = await lerInput(
    '1- Criar Personagem 2- Selecionar Personagem 3- Expansões obtidas 4- Excluir conta 5- Sair\n',
  );

  switch (resposta) {
    case '1':
      novoPersonagem();
      break;
    case '2':
      selecionarPersonagem();
      break;
    case '3':
      mostraExpansoesAdquiridas();
      break;
    case '4':
      excluirConta();
      break;
    case '5':
      return;
    default:
      await sleep(1000);
      menuPrincipal();
      break;
  }
};

const selecionarPersonagem = async () => {
  console.clear();
  const PERSONAGENS = getPersonagens();
  if (PERSONAGENS.length > 0) {
    console.table(PERSONAGENS, ['nome', 'raca', 'nivel', 'dinheiro']);
    const indexPersonagem = await lerInput(
      'Esses são seus personagens, informe o index do personagem que você deseja jogar! Se preferir digite S para voltar ao menu principal.',
    );
    if (isNaN(indexPersonagem)) {
      menuPrincipal();
    } else {
      setPersonagem(getPersonagens()[parseInt(indexPersonagem)]);
      menuInicialPersonagem();
    }
  } else {
    erro('Você ainda não criou nenhum personagem, crie um para poder jogar!\n');
    await sleep(3000);
    return menuPrincipal();
  }
};

const novoPersonagem = async () => {
  console.clear();
  const personagensAPI = await buscaPersonagens();
  console.table(personagensAPI);
  aviso('Digite S para voltar ao menu principal');
  const raca = await escolherRaca();
  if (raca) {
    const nomePersonagem = await escolherNome();
    geraPersonagem(raca, nomePersonagem);
  }
};

const escolherRaca = async () => {
  const idRaca = await lerInput(
    '\nPrimeiro escolha uma das raças acima para o seu personagem informando o ID da raça desejada.',
  );
  if (isNaN(idRaca)) {
    return menuPrincipal();
  } else {
    return await buscaPersonagem(parseInt(idRaca));
  }
};

const geraPersonagem = async (raca, nome) => {
  const PERSONAGENS = getPersonagens();
  const EXPANSOES = getExpansoes();
  try {
    const personagem = criaPersonagem(raca, nome, PERSONAGENS, EXPANSOES);
    setPersonagens([...PERSONAGENS, personagem]);
    sucesso(
      'Seu personagem foi criado com sucesso quer começar jogar com ele agora ? ',
    );
    const resp = await lerInput('1- Sim 2- Não');
    if (resp == '1') {
      setPersonagem(personagem);
      menuInicialPersonagem();
    } else {
      menuPrincipal();
    }
  } catch (error) {
    console.clear();
    erro(error.message);
    await sleep(4000);
    novoPersonagem();
  }
};

const escolherNome = async () => {
  return await lerInput(
    'Agora escolha um nome maneiro para o seu personagem, mas lembre-se, esse nome deve ser único entre seus personagens já criados.',
  );
};

const mostraExpansoesAdquiridas = async () => {
  console.clear();
  const expansoes = getExpansoes();
  expansoes.length > 0
    ? console.table(expansoes)
    : erro('Você ainda não comprou nenhuma expansão.');
  await lerInput('Digite qualquer tecla para voltar');
  menuPrincipal();
};

const excluirConta = async () => {
  console.clear();
  console.log(
    'Todos seus personagens e expansões serão excluídas, essa é uma ação irreversível, tem certeza ?',
  );
  const resp = await lerInput('1- SIM 2- NÂO');
  if (parseInt(resp) === 1) {
    return limpaDados();
  }
  return menuPrincipal();
};
