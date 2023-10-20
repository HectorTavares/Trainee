import { useAxios } from './use-axios'

export const useHttp = (baseURL, headers) => {
  const instance = useAxios(baseURL, headers)

  const get = async url => {
    const response = await instance.get(url)

    return response.data
  }

  const post = async (url, data) => {
    const response = await instance.post(url, data)

    return response.data
  }

  const put = async (url, data) => {
    const response = await instance.put(url, data)

    return response.data
  }

  const exclude = async url => {
    const response = await instance.delete(url)

    return response.data
  }

  return {
    get,
    post,
    put,
    exclude,
  }
}
