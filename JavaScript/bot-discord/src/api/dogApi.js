import { usarHttp } from './configuracoes/usar-http'

export function dogApi() {
  const http = usarHttp('https://dog.ceo/api/')

  async function pegarUmaImagemAleatoriaDeCachorro() {
    const resposta = await http.get('breeds/image/random')
    return resposta.message
  }

  return {
    pegarUmaImagemAleatoriaDeCachorro,
  }
}
