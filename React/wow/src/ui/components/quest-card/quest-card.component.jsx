import './quest-card.css'

export const QuestCard = ({ onClickAction, selectedQuestId, questId, questDescription, disabledButton }) => {
  const isSelected = selectedQuestId === questId ? 'selected' : 'not-selected'

  const handleClick = () => {
    onClickAction(questId)
  }

  return (
    <div className="quest-card">
      <button
        disabled={disabledButton}
        onClick={handleClick}
        className={`quest-card__button quest-card__button-${isSelected}`}
      >
        <span>{questDescription}</span>
      </button>
    </div>
  )
}
