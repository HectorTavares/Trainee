const possuiExpansao = (expansoes, id, msg) => {
  if (id === undefined) {
    return;
  }
  if (expansoes.find((expansao) => expansao.idExpansao === id) === undefined) {
    throw new Error(msg);
  }
};

export default possuiExpansao;
