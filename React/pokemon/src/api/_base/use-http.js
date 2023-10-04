import { useAxios } from './use-axios'

export function useHttp(baseURL, headers) {
  const instance = useAxios(baseURL, headers)

  async function get(url) {
    const response = await instance.get(url)

    return response.data
  }

  return {
    get,
  }
}
