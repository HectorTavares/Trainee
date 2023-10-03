import axios from 'axios';
import { subirNivel, adicionarDinheiro } from './personagem';
import possuiExpansao from './services/possui-expansao/possuiExpansao';

export const buscarMissoes = async () => {
  const { data } = await axios.get(
    'https://hectortavares.github.io/Trainee/Conteudo/woe/quests.json',
  );

  return data;
};

export const selecionarMissao = (missoes, id) => {
  const missaoSelecionada = missoes.find((missao) => {
    return missao.id === id;
  });

  return missaoSelecionada;
};

export const realizarMissao = (personagem, missao, expansoes = []) => {
  let personagemAtualizado = { ...personagem };

  possuiExpansao(
    expansoes,
    missao.idExpansao,
    'Não possui a expansão necessária para realizar a missao escolhida',
  );

  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(receberRecompensa(personagemAtualizado, missao));
    }, missao.tempoEstimado);
  });
};

export const receberRecompensa = (personagem, missao) => {
  let personagemAtualizado = { ...personagem };
  personagemAtualizado = adicionarDinheiro(
    personagemAtualizado,
    missao.dinheiroRecebido,
  );
  personagemAtualizado = subirNivel(
    personagemAtualizado,
    missao.niveisRecebidos,
  );
  return personagemAtualizado;
};
