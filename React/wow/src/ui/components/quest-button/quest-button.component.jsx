import './quest-button.css'
export const QuestButton = ({ title, onClickAction }) => {
  return (
    <button className="quest-button" onClick={onClickAction}>
      <span>{title}</span>
    </button>
  )
}
