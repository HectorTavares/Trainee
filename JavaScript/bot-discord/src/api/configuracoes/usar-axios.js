import axios from 'axios'

export function usarAxios(baseURL, headers) {
  return axios.create({
    baseURL,
    headers,
  })
}
