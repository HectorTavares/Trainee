import { useQuestion } from '../src/services/question/use-question';
import erro from './mensagens/erro';
import { cheats } from '../src/cheats';
import {
  getPersonagem,
  getPersonagens,
  setPersonagem,
  setPersonagens,
} from '../state/state';
import sleep from '../src/services/delay/sleep';

export const lerInput = async (msg) => {
  const resposta = await useQuestion(msg);
  if (resposta === '') {
    return await lerInput('Por favor, digite algo.');
  }
  const PERSONAGEM = getPersonagem();
  const PERSONAGENS = getPersonagens();
  let xitado;
  try {
    xitado = cheats(resposta, PERSONAGEM, PERSONAGENS);
  } catch (error) {
    erro(error.message);
    return await lerInput(
      'Digite outra trapaça ou siga o fluxo da aplicação normalmente',
    );
  }
  if (xitado?.cheatAtivado) {
    if (xitado.cheatAtivado === 1) {
      setPersonagem(xitado.personagem);
    } else {
      setPersonagens(xitado.personagens);
      if (PERSONAGEM) {
        const personagemAtualizado = getPersonagens().find(
          (personagem) => personagem.nome === PERSONAGEM.nome,
        );
        setPersonagem(personagemAtualizado);
      }
    }
    console.log('Trapaça ativada');
    await sleep(2000);
    return await lerInput(
      'Digite outra trapaça ou continue o fluxo da aplicação normalmente',
    );
  }

  return resposta;
};
