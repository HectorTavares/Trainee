import { useHttp } from '../_base/use-http'
import { useGlobalUserDiscord } from "../../context/user-discord/user-discord-context";
import { useMemo } from 'react'
import {api} from '../../constants/index'


export function useCwiRespostasApi() {
  const [userDiscord] = useGlobalUserDiscord()
  const http = useHttp(api.JENKINS, {
    authorization: `Bearer ${userDiscord}`,
  })

  async function login() {

    return await http.post("/usuario/login")
  }

  async function user() {

    return await http.get("/usuario/me")
  }

  async function validateAdmin() {

    return await http.get("/admin/validate")
  }

  async function userSearch(search, page) {

    return await http.get(`/admin/busca?busca=${search}&page=${page}&size=${9}`)
  }

  async function delegate(email) {

    return await http.post(`/admin/delegar?email=${email}`)
  }

  async function revoke(email) {

     return await http.post(`/admin/revogar?email=${email}`)
  }

  async function ranking() {

     return await http.get(`/usuario/ranking?page=${0}&size=${5}`)
  }

  async function uploadPicture(payload) {

    return await http.post(`/storage/upload`, payload)
  }

  async function deletePicture(fileName) {

     return await http.remove(`/storage/delete?file=${fileName}`)
  }

  async function tagList() {

    return await http.get(`/tag/listar`)
  }

  async function createTag(nome) {

    return await http.post(`/tag/criar?nome=${nome}`)
  }

  async function createPost(payload) {

    return await http.post(`/pergunta/criar`, payload)
  }

  async function listAllPosts(page) {
  
    return await http.get(`/pergunta/listar?page=${page}&size=${3}`, )
  }

  async function searchPosts(page, payload) {

  return await http.post(`/pergunta/pesquisar?page=${page}&size=${3}`, payload)
  }

  async function listAllReplies(id) {

  return await http.get(`/resposta/listar?idPergunta=${id}`, )
  }

  async function createReply(payload) {

    return await http.post(`/resposta/criar`, payload )
  }

  async function postDownVote(id) {

    return await http.post(`/interacaoPergunta/negativa?idPergunta=${id}`)
  }

  async function postUpvote(id) {

    return await http.post(`/interacaoPergunta/positiva?idPergunta=${id}`)
  }

  async function postRep(id) {

     return await http.get(`/interacaoPergunta/relevancia?idPergunta=${id}`)
  }

  async function postUserInteraction(id) {

    return await http.get(`/interacaoPergunta/interacaoUsuario?idPergunta=${id}`)
  }

  async function replyDownVote(id) {

    return await http.post(`/interacaoResposta/negativa?idResposta=${id}`)
  }

  async function replyUpvote(id) {

    return await http.post(`/interacaoResposta/positiva?idResposta=${id}`)
  }

  async function replyRep(id) {

    return await http.get(`/interacaoResposta/relevancia?idResposta=${id}`)
  }

  async function replyUserInteraction(id) {
    
    return await http.get(`/interacaoResposta/interacaoUsuario?idResposta=${id}`)
  }

  async function replyVerifyChange(id) {

    return await http.post(`/resposta/aprovacao?idResposta=${id}`)
  }

  async function getNotifications() {

    return await http.get(`/notificacao/pegar`)
  }

  async function getNotificationPost(id) {

    return await http.get(`/pergunta/buscarPergunta?idPergunta=${id}`)
  }

  async function deleteNotification(id) {

    return await http.remove(`/notificacao/visualizar?idNotificacao=${id}`)
  }

  async function clearNotifications() {

    return await http.remove(`/notificacao/visualizarTodos`)
  }
 
  return useMemo(
    () => ({
      login,
      user,
      validateAdmin,
      userSearch,
      delegate,
      revoke,
      ranking,
      uploadPicture,
      deletePicture,
      tagList,
      createTag,
      createPost,
      listAllPosts,
      listAllReplies,
      createReply,
      postDownVote,
      postUpvote,
      postRep,
      replyDownVote,
      replyUpvote,
      replyRep,
      postUserInteraction,
      replyUserInteraction,
      replyVerifyChange,
      getNotifications,
      getNotificationPost,
      deleteNotification,
      clearNotifications,
      searchPosts

    }),
    [userDiscord]
  )
}
