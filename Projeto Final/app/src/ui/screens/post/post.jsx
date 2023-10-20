import './post.css'
import { useEffect, useState } from 'react'
import { useLocation } from 'react-router'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { Header, Reply, Loader, Image, ReplyForm } from '../../components/index'
import { toast } from 'react-toastify'
import { variables, errorMessage } from '../../../constants/index'
import defaultAvatar from '../../../assets/image/defaultAvatar.png'

export function Post() {
  const {
    state: { post },
  } = useLocation()

  console.log('post')
  console.log(post)

  const [user, setUser] = useState()
  const [userRole, setUserRole] = useState()
  const [nonAuthor, setNonAuthor] = useState(false)

  const [postRep, setPostRep] = useState()
  const [upVoted, setUpVoted] = useState()
  const [downVoted, setDownVoted] = useState()
  const [voteDisabled, setVoteDisabled] = useState(false)

  const [replies, setReplies] = useState([])

  const [voteLoader, setVoteLoader] = useState(false)
  const [repliesLoader, setRepliesLoader] = useState(true)
  const [repRefresh, setRepRefresh] = useState(false)
  const [repliesRefresh, setRepliesRefresh] = useState(false)
  const [cardRefresh] = useState(false)

  const postImages = JSON.parse(post.foto)
  const cwiRespostasApi = useCwiRespostasApi()

  useEffect(() => {
    post.autor.isMonitor ? setUserRole(variables.MONITOR) : setUserRole(variables.USER)
  }, [])

  function validateAutor(user) {
    if (post.autor.email !== user?.email) {
      setNonAuthor(true)
    }
  }

  useEffect(() => {
    async function getUser() {
      try {
        const response = await cwiRespostasApi.user()
        setUser(response)
        validateAutor(response)
      } catch (error) {
        toast.error(errorMessage.USER_ERROR)
      }
    }
    getUser()
  }, [])

  useEffect(() => {
    async function getReplies() {
      try {
        const response = await cwiRespostasApi.listAllReplies(post.id)
        setReplies(response)
        setRepliesLoader(false)
        console.log(response)
      } catch (error) {
        toast.error(errorMessage.REPLY_LOAD_ERROR)
        setRepliesLoader(false)
      }
    }
    getReplies()
  }, [repliesRefresh])

  useEffect(() => {
    async function getVoteInfo() {
      try {
        const response = await cwiRespostasApi.postRep(post.id)
        setPostRep(response)
        const interactResponse = await cwiRespostasApi.postUserInteraction(post.id)
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
      await cwiRespostasApi.postDownVote(post.id)
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
      await cwiRespostasApi.postUpvote(post.id)
      setRepRefresh(!repRefresh)
    } catch (error) {
      toast.error(errorMessage.VOTE_ERROR)
      setVoteLoader(false)
      setVoteDisabled(false)
    }
  }

  return (
    <div className="post_main">
      <Header />
      <div className="post_container">
        <div className="post_card">
          <div className="post_top">
            <div className="post_titulo">{post.titulo}</div>
            <div className="post_tags-section">
              {post.tags.map(tag => (
                <div key={tag.id} className={`post_tag ${userRole}`}>
                  {tag.nome}
                </div>
              ))}
            </div>
          </div>
          <div className="post_bottom">
            <div className="post_content">{post.descricao}</div>
            <div className="post_images-section">
              {postImages.map(image => (
                <Image key={image.id} image={image} cssType="post_image" />
              ))}
            </div>
          </div>
          <div className="post_footer">
            <div className="post_user">
              <div className="post_avatar-section">
                <img
                  src={post.autor.avatar}
                  onError={e => {
                    e.target.src = defaultAvatar
                  }}
                  alt=""
                  className="post_avatar"
                />
              </div>
              <div className="post_username">
                <div> {post.autor.username}</div>
                <div className="post_date-time">
                  {' '}
                  {post.dataHora.substr(0, 10).split('-').reverse().join('/') +
                    ' - ' +
                    post.dataHora.substr(11, 5)}
                </div>
              </div>
            </div>
            <div className="post_data">
              <div className="post_topic-counter-section">
                <div className={`post_counter ${userRole}`}>{replies?.length}</div>
                Respostas
              </div>
              <div className="post_rep-counter-section">
                <div className="post_rep-counter-info">
                  {nonAuthor && (
                    <button
                      disabled={voteDisabled}
                      onClick={handleDownVoteClick}
                      className={`post_rep-button downvote ${downVoted} ${userRole}`}
                    >
                      -
                    </button>
                  )}
                  <div className={`post_counter ${userRole}`}>
                    {voteLoader ? <Loader size="tiny" /> : postRep}
                  </div>
                  {nonAuthor && (
                    <button
                      disabled={voteDisabled}
                      onClick={handleUpVoteClick}
                      className={`post_rep-button upvote ${upVoted} ${userRole}`}
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
        <div className="post_replies">
          {repliesLoader ? (
            <Loader size="big" />
          ) : (
            replies.length > 0 &&
            replies
              .sort((a, b) => a.id - b.id)
              .sort((a, b) => b.relevancia - a.relevancia)
              .sort((a, b) => Number(b.isAprovado) - Number(a.isAprovado))
              .sort((a, b) => Number(b.autor.isMonitor) - Number(a.autor.isMonitor))
              .map(reply => (
                <Reply
                  key={reply.id}
                  reply={reply}
                  user={user}
                  cardRefresh={cardRefresh}
                  setRepliesRefresh={setRepliesRefresh}
                  repliesRefresh={repliesRefresh}
                  setRepliesLoader={setRepliesLoader}
                  repliesLoader={repliesLoader}
                />
              ))
          )}
        </div>
        <div className="post_reply-form">
          <ReplyForm
            user={user}
            post={post}
            setRepliesRefresh={setRepliesRefresh}
            repliesRefresh={repliesRefresh}
          />
        </div>
      </div>
    </div>
  )
}
