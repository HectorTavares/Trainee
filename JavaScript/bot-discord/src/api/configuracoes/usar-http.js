import { usarAxios } from './usar-axios'

export function usarHttp(baseURL, headers) {
  const instanciarAxios = usarAxios(baseURL, headers)

  async function get(url) {
    const resposta = await instanciarAxios.get(url)

    return resposta.data
  }

  return {
    get,
  }
}
