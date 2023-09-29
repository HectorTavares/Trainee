export function filtarPorAnoERetornarNome(series, ano) {

  const resultado = [];

  const seriesFiltradas = series.filter(series => {
    return series['anoEstreia'] >= ano
  })

  seriesFiltradas.forEach(seriesFiltradas => {
    resultado.push(seriesFiltradas.titulo);
  })

  return resultado
}

export function verificarSeAtorEstaEmSeriado(series, nomeAtor) {

  let resultado = false;

  series.forEach(serie => {
    serie['elenco'].forEach(ator => {
      if (ator == nomeAtor) {
        resultado = true;
      }
    })
  })


  return resultado
}

export function calcularMediaTotalDeEpisodios(series) {

  let contador = 0;

  for (let i = 0; i < series.length; i++) {
    contador += series[i].numeroEpisodios
  }

  const media = contador / series.length

  return media
}

export function agruparTituloDasSeriesPorPropriedade(series, propriedade) {

  const resultado = series.reduce((valorAnterior, valorAtual) => {

    let key = valorAtual[propriedade];

    if (!valorAnterior[key]) {
      valorAnterior[key] = [];
    }

    valorAnterior[key].push(valorAtual);
    
    return valorAnterior;
  }, {});


  return resultado
}
