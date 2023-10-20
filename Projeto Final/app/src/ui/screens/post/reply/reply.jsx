import './reply.css'
import { useState, useEffect } from 'react'
import { useCwiRespostasApi } from '../../../../api/Projetofinal-api/Projetofinal-api'
import { Loader, Image } from '../../../components/index'
import { variables, errorMessage } from '../../../../constants/index'
import { toast } from 'react-toastify'
import defaultAvatar from '../../../../assets/image/defaultAvatar.png'
import ok from '../../../../assets/image/ok.png'

export function Reply({
  reply,
  user,
  cardRefresh,
  setRepliesRefresh,
  repliesRefresh,
  setRepliesLoader,
  repliesLoader,
}) {
  const [userRole, setUserRole] = useState('')
  const [nonAuthor, setNonAuthor] = useState(false)
  const [isMonitor, setIsMonitor] = useState('')
  const [isVerified, setIsVerified] = useState('')
  const [replyRep, setReplyRep] = useState()
  const [upVoted, setUpVoted] = useState()
  const [downVoted, setDownVoted] = useState()
  const [voteLoader, setVoteLoader] = useState(false)
  const [voteDisabled, setVoteDisabled] = useState(false)
  const [repRefresh, setRepRefresh] = useState(false)
  const [verifyLoader, setVerifyLoader] = useState(false)
  const images = JSON.parse(reply.foto)

  const cwiRespostasApi = useCwiRespostasApi()

  useEffect(() => {
    reply.isAprovado && !reply.autor.isMonitor && setIsVerified(variables.VERIFIED)
  }, [])

  useEffect(() => {
    if (reply.autor.isMonitor) {
      setUserRole(variables.MONITOR)
      setIsMonitor(variables.REPLY_MONITOR)
    } else {
      setUserRole(variables.USER)
    }
    reply.isAprovada && setIsVerified(variables.REPLY_VERIFIED)
  }, [cardRefresh])

  useEffect(() => {
    user && validateAutor(user)
  }, [user])

  useEffect(() => {
    async function getVoteInfo() {
      try {
        const response = await cwiRespostasApi.replyRep(reply.id)
        setReplyRep(response)
        console.log(response)
        const interactResponse = await cwiRespostasApi.replyUserInteraction(reply.id)
        interactResponse ? interaction(interactResponse.tipoInteracao) : interaction(variables.CLEAR)
        setVoteLoader(false)
        setVoteDisabled(false)
      } catch (error) {
        toast.error(errorMessage.VOTE_LOAD_ERROR)
        setVoteLoader(false)
        setVoteDisabled(false)
      }
    }
    getVoteInfo()
  }, [repRefresh])

  function interaction(type) {
    switch (type) {
      case variables.POSITIVO:
        setUpVoted(variables.UPVOTED)
        setDownVoted('')
        break
      case variables.NEGATIVO:
        setDownVoted(variables.DOWNVOTED)
        setUpVoted('')
        break
      case variables.CLEAR:
        setUpVoted('')
        setDownVoted('')
        break
    }
  }

  async function handleDownVoteClick() {
    setVoteLoader(true)
    setVoteDisabled(true)
    try {
      await cwiRespostasApi.replyDownVote(reply.id)
      setRepRefresh(!repRefresh)
    } catch (error) {
      toast.error(errorMessage.VOTE_ERROR)
      setVoteLoader(false)
      setVoteDisabled(false)
    }
  }
  async function handleUpVoteClick() {
    setVoteLoader(true)
    setVoteDisabled(true)
    try {
      await cwiRespostasApi.replyUpvote(reply.id)
      setRepRefresh(!repRefresh)
    } catch (error) {
      toast.error(errorMessage.VOTE_ERROR)
      setVoteLoader(false)
      setVoteDisabled(false)
    }
  }
  function validateAutor(user) {
    if (reply.autor.email !== user?.email) {
      setNonAuthor(true)
    }
  }
  async function handleVerifyClick() {
    setVerifyLoader(true)
    try {
      await cwiRespostasApi.replyVerifyChange(reply.id)
      setVerifyLoader(false)
      setRepliesRefresh(!repliesRefresh)
      setRepliesLoader(!repliesLoader)
    } catch (error) {
      toast.error(errorMessage.VERIFY_ERROR)
      setVerifyLoader(false)
    }
  }

  return (
    <div className="reply_main">
      <div className="reply_container">
        <div className={`reply_card ${isMonitor} ${isVerified}`}>
          <div className="reply_body">
            <div className="reply_content">{reply.descricao}</div>
            <div className="reply_images-section">
              {images.length > 0 &&
                images.map(image => <Image key={image} image={image} cssType="reply_image" />)}
            </div>
          </div>
          <div className="reply_footer">
            <div className="reply_user">
              <div className="reply_avatar-section">
                <img
                  src={reply.autor.avatar}
                  onError={e => {
                    e.target.src = defaultAvatar
                  }}
                  alt="Avatar do usuario"
                  className="reply_avatar"
                />
              </div>
              <div className="reply_username">
                <div> {reply.autor.username}</div>
                <div className="reply_date-time">
                  {reply.dataHora.substr(0, 10).split('-').reverse().join('/') +
                    ' - ' +
                    reply.dataHora.substr(11, 5)}
                </div>
              </div>
              <div className="reply_verify-section">
                {reply.autor.isMonitor && (
                  <div className="reply_verify">
                    <img src={ok} alt="Marcação de verificado" className="reply_verify-icon" />
                    Monitor
                  </div>
                )}
                {reply.isAprovado &&
                  !reply.autor.isMonitor &&
                  (verifyLoader ? (
                    <Loader size="small" />
                  ) : (
                    <div className="reply_monitor-ok reply_verify">
                      <button
                        disabled={!user?.isMonitor}
                        onClick={handleVerifyClick}
                        className="reply_verify-button"
                      >
                        <img src={ok} alt="Marcação de verificado" className="reply_verify-icon" />
                      </button>
                      Resposta Verificada
                    </div>
                  ))}
                {!reply.isAprovado &&
                  !reply.autor.isMonitor &&
                  user?.isMonitor &&
                  (verifyLoader ? (
                    <Loader size="small" />
                  ) : (
                    <div className="reply_monitor-ok reply_verify">
                      <button onClick={handleVerifyClick} className="reply_verify-button">
                        <img src={ok} alt="Marcação de verificado" />
                      </button>
                      Verificar Resposta
                    </div>
                  ))}
              </div>
            </div>
            <div className="reply_data">
              <div className="reply_rep-counter-info">
                {nonAuthor && (
                  <button
                    disabled={voteDisabled}
                    onClick={handleDownVoteClick}
                    className={`reply_rep-button downvote ${downVoted} ${userRole}`}
                  >
                    -
                  </button>
                )}
                <div className={`reply_counter ${userRole}`}>
                  {' '}
                  {voteLoader ? <Loader size="tiny" /> : replyRep}
                </div>
                {nonAuthor && (
                  <button
                    disabled={voteDisabled}
                    onClick={handleUpVoteClick}
                    className={`reply_rep-button upvote ${upVoted} ${userRole}`}
                  >
                    +
                  </button>
                )}
              </div>
              Relevância
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
