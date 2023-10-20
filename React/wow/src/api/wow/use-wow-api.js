import { useMemo } from 'react'
import { useHttp } from '../_base/use-http'
import { useGlobalUser } from '../../context'

export function useWowApi() {
  const [user] = useGlobalUser()

  const httpInstance = useHttp('https://wow-crescer-api.herokuapp.com', {
    authorization: user.token,
  })

  async function login(username, password) {
    const response = await httpInstance.post('/auth/login', { username, password })

    return response
  }

  async function register(username, password, confirmPassword) {
    const response = await httpInstance.post('/auth/register', { username, password, confirmPassword })

    return response
  }

  async function getRaces() {
    const response = await httpInstance.get('/races')

    return response
  }

  async function getMyCharacters() {
    const response = await httpInstance.get('/user/me/characters')

    return response
  }

  async function getAchieveFromCheats(code, characterId) {
    const response = await httpInstance.post('/cheat', { code, characterId })
    return response
  }

  async function getMyCharacterById(idCharacter) {
    const response = await httpInstance.get(`/user/me/characters/${idCharacter}`)

    return response
  }

  async function getUserInfo() {
    const response = await httpInstance.get(`/user/me`)

    return response
  }

  async function getShop() {
    const response = await httpInstance.get(`/shop`)

    return response
  }

  async function buyItem(idItem, characterId) {
    const response = await httpInstance.post(`/shop/${idItem}/buy`, { characterId })

    return response
  }

  async function sellItem(idItem, characterId) {
    const response = await httpInstance.post(`/shop/${idItem}/sell`, { characterId })

    return response
  }

  async function createNewCharacter(newCharacter) {
    const response = await httpInstance.post('/user/create-character', newCharacter)

    return response
  }

  async function getQuests() {
    const response = await httpInstance.get(`/quests`)

    return response
  }

  async function startQuest(idQuest, characterId) {
    const response = await httpInstance.post(`/quests/${idQuest}/start`, { characterId })

    return response
  }

  async function finishQuest(characterId) {
    const response = await httpInstance.post(`/quests/finish`, { characterId })

    return response
  }

  async function deleteCharacter(characterId) {
    const response = await httpInstance.post(`/user/me/characters/${characterId}/delete`)

    return response
  }

  async function getAllCharacters() {
    const response = await httpInstance.get('/characters/')

    return response
  }

  async function getBattleLog(myId, enemyId) {
    const response = await httpInstance.post(`/user/me/characters/${myId}/battle`, {
      opponentId: enemyId,
    })

    return response
  }

  return useMemo(
    () => ({
      login,
      getRaces,
      getMyCharacters,
      getMyCharacterById,
      getUserInfo,
      getShop,
      buyItem,
      sellItem,
      createNewCharacter,
      register,
      getQuests,
      deleteCharacter,
      getAchieveFromCheats,
      startQuest,
      finishQuest,
      getAllCharacters,
      getBattleLog,
    }),
    [user]
  )
}
