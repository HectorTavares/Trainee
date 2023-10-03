import { batalha } from '../src/batalha';
import sleep from '../src/services/delay/sleep';
import {
  getPersonagem,
  getPersonagens,
  setPersonagem,
  updatePersonagemInPersonagens,
} from '../state/state';

import { lerInput } from './lerInput';
import erroMensagem from './mensagens/erro';
import { menuInicialPersonagem } from './menuPersonagem';

export const menuBatalha = async () => {
  console.clear();
  const personagemEscolhido = getPersonagem();
  const personagens = getPersonagens().filter(
    (personagem) => personagemEscolhido.nome !== personagem.nome,
  );

  if (personagens.length < 1) {
    erroMensagem('Não tem personagens suficientes para batalhar!!!');
    await sleep(2000);
    return menuInicialPersonagem();
  }

  console.log('-----------------------------------------------------');
  console.log('|> BEM VINDO AO MENU DE BATALHA');
  console.log('-----------------------------------------------------');
  console.table(personagens, [
    'nome',
    'raca',
    'nivel',
    'vida',
    'vigor',
    'dano',
  ]);

  const resposta = await lerInput(
    '|> Escolha um dos personagens acima para lutar contra informando o index ou digite S para voltar!',
  );
  if (isNaN(resposta)) {
    return menuInicialPersonagem();
  }

  const personagemOponente = personagens[resposta];

  if (!personagemOponente) {
    erroMensagem('Personagem não existe!');
    await sleep(2000);
    menuBatalha();
  }

  try {
    const vencedor = batalha(personagemEscolhido, personagemOponente);
    console.log(`\nVENCEDOR FOI: ${vencedor.nome}`);
    await sleep(2000);
    updatePersonagemInPersonagens(vencedor);
    if (vencedor.nome === personagemEscolhido.nome) {
      setPersonagem(vencedor);
    }
    menuInicialPersonagem();
  } catch (error) {
    erroMensagem(error.message);
    await sleep(2000);
    menuInicialPersonagem();
  }
};
