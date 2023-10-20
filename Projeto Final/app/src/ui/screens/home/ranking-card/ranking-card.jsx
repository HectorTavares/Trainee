import './ranking-card.css'
import defaultAvatar from '../../../../assets/image/defaultAvatar.png'

export function RankingCard({ user }) {
  return (
    <div className="ranking-card_main">
      <div className="ranking-card_container">
        <div className="ranking-card_left-section">
          <img
            src={user?.avatar}
            onError={e => {
              e.target.src = defaultAvatar
            }}
            alt="Avatar do Usuario"
            className="ranking-card_avatar"
          />
          <div>{user.username}</div>
        </div>
        <div className="ranking-card_right-section">{<div>Reputação: {user.reputacao.toFixed(1)}</div>}</div>
      </div>
    </div>
  )
}
