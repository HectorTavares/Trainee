import './battle-card.style.css'

export const BattleCard = ({ id, name, level, race, onClick, selected }) => {
  const isSelected = selected ? 'selected' : ''
  const handleClick = () => {
    onClick(id)
  }

  return (
    <button onClick={handleClick} className={`battleCard ${isSelected}`}>
      <h2 className="battleCard__name battleCard__info">{name}</h2>
      <p className="battleCard__name battleCard__info">Level {level}</p>
      <p className="battleCard__name battleCard__info">{race}</p>
    </button>
  )
}

BattleCard.defaultProps = {
  selected: false,
}
