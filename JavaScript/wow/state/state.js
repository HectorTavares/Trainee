import { salvaDados } from '..';
import { salvarExpansao, salvarPersonagens } from '../src/db';

let EXPANSOES = [];
let PERSONAGENS = [];
let PERSONAGEM = null;

export const setPersonagem = (personagem) => {
  PERSONAGEM = JSON.parse(JSON.stringify(personagem));
  if (personagem) {
    updatePersonagemInPersonagens(personagem);
  }
};

export const setPersonagens = (personagens) => {
  const personagensOrdenados = personagens.sort((p1, p2) => {
    return p2.nivel - p1.nivel;
  });
  PERSONAGENS = JSON.parse(JSON.stringify(personagensOrdenados));
  salvarPersonagens(PERSONAGENS);
};

export const setExpansoes = (expansoes) => {
  EXPANSOES = JSON.parse(JSON.stringify(expansoes));
};

export const getPersonagem = () => {
  return JSON.parse(JSON.stringify(PERSONAGEM));
};

export const getPersonagens = () => {
  return JSON.parse(JSON.stringify(PERSONAGENS));
};

export const getExpansoes = () => {
  return JSON.parse(JSON.stringify(EXPANSOES));
};

export const updatePersonagemInPersonagens = async (personagem) => {
  const personagens = getPersonagens();
  const index = personagens.findIndex(
    (personagemSalvo) => personagemSalvo.nome === personagem.nome,
  );
  if (index >= 0) {
    personagens.splice(index, 1);
  }
  personagens.push(personagem);
  setPersonagens(personagens);
};
