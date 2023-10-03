const possuiLevel = (personagem, level, msg) => {
  if (personagem.nivel < level) {
    throw new Error(msg);
  }
};

export default possuiLevel;
