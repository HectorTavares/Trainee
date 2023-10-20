import './quest-selected.css'
import { QuestButton } from '..'

const MS_IN_A_DAY = 86400000
const MS_IN_A_HOUR = 3600000
const MS_IN_A_MINUTE = 60000
const MS_IN_A_SECOND = 1000

const getTimeFormatted = duration => {
  if (duration > MS_IN_A_DAY) {
    const days = Math.round(duration / MS_IN_A_DAY)
    return days + ' dias'
  } else if (duration > MS_IN_A_HOUR) {
    const minutes = Math.round(duration / MS_IN_A_HOUR)
    return minutes + ' horas'
  } else if (duration > MS_IN_A_MINUTE) {
    const minutes = Math.round(duration / MS_IN_A_MINUTE)
    return minutes + ' minutos'
  } else {
    const seconds = Math.round(duration / MS_IN_A_SECOND)
    return seconds + ' segundos'
  }
}

export const QuestSelected = ({ quest, onClickAction, buttonTitle }) => {
  const { description, duration, experience, money, image } = quest

  const time = getTimeFormatted(duration)

  return (
    <div className="quest-selected">
      <div className="quest-selected__img-container">
        <img src={image} alt={`Missão ${description}`} />
      </div>

      <span className="quest-selected__description">{description}</span>

      <div className="quest-selected__rewards">
        <div className="quest-selected__rewards-header">
          <span>Recompensas:</span>
        </div>

        <div className="quest-selected__rewards-content">
          <span>+ {experience} XP</span>
          <span>+ ${money}</span>
        </div>
      </div>

      <span className="quest-selected__duration">Duração: {time}</span>

      <QuestButton title={buttonTitle} onClickAction={onClickAction} />
    </div>
  )
}
