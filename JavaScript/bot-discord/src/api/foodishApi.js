import { usarHttp } from './configuracoes/usar-http'

export function foodishApi() {
  const http = usarHttp('https://foodish-api.herokuapp.com/')

  async function pegarUmaImagemAleatoriaDeComida() {
    const resposta = await http.get(`api/`)

    return resposta.image
  }

  return {
    pegarUmaImagemAleatoriaDeComida,
  }
}
