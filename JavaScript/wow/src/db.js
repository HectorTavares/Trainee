import { useLocalStorage } from './services/local-storage/use-local-storage';

const localStorage = useLocalStorage();
const PERSONAGENS = 'personagens';
const EXPANSOES = 'expansoes';

export const salvarPersonagens = (personagens) => {
  localStorage.setObject(PERSONAGENS, [...personagens]);
};

export const buscaPersonagemSalvo = (nome) => {
  const personagens = buscaPersonagensSalvos();
  const personagemBuscado = personagens.find((personagem) => personagem.nome === nome);
  return personagemBuscado ? personagemBuscado : null;
};

export const buscaPersonagensSalvos = () => {
  const personagens = localStorage.getObject(PERSONAGENS);
  return personagens ? personagens : [];
};

export const salvarExpansao = (expansoes) => {
  localStorage.setObject(EXPANSOES, expansoes);
};

export const buscarExpansoesSalvas = () => {
  const expansoes = localStorage.getObject(EXPANSOES);
  return expansoes ? expansoes : [];
};

export const limpaDados = () => {
  localStorage.setObject(PERSONAGENS, []);
  localStorage.setObject(EXPANSOES, []);
};
