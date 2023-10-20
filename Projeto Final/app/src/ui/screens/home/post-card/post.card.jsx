import './post-card.css'
import { useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { variables, paths } from '../../../../constants/index'
import defaultAvatar from '../../../../assets/image/defaultAvatar.png'

export function PostCard({ post }) {
  const [userRole, setUserRole] = useState('')
  const history = useHistory()

  useEffect(() => {
    post.autor.isMonitor ? setUserRole(variables.MONITOR) : setUserRole(variables.USER)
  }, [])

  function handlePostCardClick() {
    history.push(paths.POST, { post })
  }

  return (
    <button onClick={handlePostCardClick} className="post-card_main">
      <div className="post-card_top-section">
        <div className="post-card_title">{post.titulo}</div>
        <div className="post-card_tags">
          {post.tags.map(tag => (
            <div key={tag.id} className={`post-card_tag-icon ${userRole}`}>
              {tag.nome}
            </div>
          ))}
        </div>
      </div>
      <div className="post-card_bottom-section">
        <div className="post-card_user-info">
          <img
            src={post.autor.avatar}
            onError={e => {
              e.target.src = defaultAvatar
            }}
            alt="Avatar do Usuario"
            className="post-card_avatar"
          />
          <h4>{post.autor.username}</h4>
        </div>
        <div className="post-card_counters">
          <div className="post-card_counter-container">
            <div className={`post-card_counter ${userRole}`}>{post.contadorRespostas}</div>
            Respostas
          </div>
          <div className="post-card_counter-container">
            <div className={`post-card_counter ${userRole}`}>{post.contadorRelevancia}</div>
            Relevancia
          </div>
        </div>
      </div>
    </button>
  )
}
