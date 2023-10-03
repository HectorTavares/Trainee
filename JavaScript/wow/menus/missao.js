import { buscarMissoes, realizarMissao, selecionarMissao } from '../src/missao';
import sleep from '../src/services/delay/sleep';
import { getExpansoes, getPersonagem, setPersonagem } from '../state/state';
import { lerInput } from './lerInput';
import erro from './mensagens/erro';
import informacao from './mensagens/informacao';
import { menuInicialPersonagem } from './menuPersonagem';

export const menuMissoes = async () => {
  const personagem = getPersonagem();
  const expansoes = getExpansoes();
  console.clear();
  console.log('-----------------------------------------------------');
  console.log('|> BEM VINDO AO MENU DE MISSÕES');
  console.log('-----------------------------------------------------');
  const missoes = await buscarMissoes();
  console.table(missoes);

  const resposta = await lerInput(
    'Digite o ID da missão que você quer fazer, se quiser voltar ao menu anterior digite S.',
  );
  if (isNaN(resposta)) {
    return menuInicialPersonagem();
  }
  const missaoSelecionada = selecionarMissao(missoes, parseInt(resposta));

  try {
    const personagemAposMissao = await realizarMissao(
      personagem,
      missaoSelecionada,
      expansoes,
    );
    informacao(`Você evoluiu ${missaoSelecionada.niveisRecebidos} niveis e recebeu ${missaoSelecionada.dinheiroRecebido} de dinheiro por completar a missão`)
    setPersonagem(personagemAposMissao);
    console.log('Gostaria de fazer mais missões?');
    const novaMissao = await lerInput('1- Sim  2-Não');

    switch (novaMissao) {
      case '1':
        menuMissoes();
        break;

      case '2':
        menuInicialPersonagem();
        break;

      default:
        break;
    }
  } catch (error) {
    erro(error.message);
    await sleep(3000);
    menuMissoes();
  }
};
