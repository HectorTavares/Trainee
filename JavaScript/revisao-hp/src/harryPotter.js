
export const criarBruxo = (nome, casa) => {
  const bruxo =  {
    nome,
    casa,
    materiasQueEstuda: ["Pocoes","Artes das Trevas"],
  }
  return setFalarMateriasQueEstuda(bruxo)
}

export const criarTrouxa = (nome) =>{
    const trouxa =  {
    nome,
    materiasQueEstuda: ["Matematica", "Geografia"],
  }
  return setFalarMateriasQueEstuda(trouxa)
}

export const falarMateriasQueEstuda = materiasQueEstudo =>{
  return "As matérias que estudo são ".concat(materiasQueEstudo.join(', '))
}

export const setFalarMateriasQueEstuda = (pessoa) => {
  return {
    ...pessoa,
    falarMateriasQueEstuda: () => falarMateriasQueEstuda(pessoa.materiasQueEstuda)
  }
}
