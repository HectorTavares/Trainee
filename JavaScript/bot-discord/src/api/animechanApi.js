import { usarHttp } from './configuracoes/usar-http'

export function animechanApi() {
  const http = usarHttp('https://animechan.vercel.app/api/')

  async function pegarUmaCitacaoAleatoria() {
    const resposta = await http.get('random')

    return resposta
  }

  async function pegarUmaCitacaoPeloTituloDoAnime(titulo) {
    const resposta = await http.get(`quotes/anime?title=${titulo}`)
    const numeroAleatorio = Math.floor(Math.random() * 9)
    const citacaoSelecionada = resposta[numeroAleatorio]

    return citacaoSelecionada
  }

  async function pegarUmaCitacaoPeloNomeDoPersonagem(nome) {
    const resposta = await http.get(`quotes/character?name=${nome}`)
    const numeroAleatorio = Math.floor(Math.random() * 9)
    const citacaoSelecionada = resposta[numeroAleatorio]

    return citacaoSelecionada
  }

  return {
    pegarUmaCitacaoAleatoria,
    pegarUmaCitacaoPeloTituloDoAnime,
    pegarUmaCitacaoPeloNomeDoPersonagem,
  }
}
