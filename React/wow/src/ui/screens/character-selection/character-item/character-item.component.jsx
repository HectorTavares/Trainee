import './character-item.style.css'

export const CharacterItem = ({ onClick, name, race, level, id, faction, selected }) => {
  const handleClick = () => {
    onClick(id)
  }

  const selectedConfirmation = selected ? 'selected' : ''
  return (
    <button onClick={handleClick} className={`character-item ${selectedConfirmation}`}>
      <h3 className="character-item__name">{name}</h3>
      <p className="character-item__level-race">
        level: {level} {race}
      </p>
      <p className="character-item__faction">{faction}</p>
    </button>
  )
}

CharacterItem.defaultProps = {
  faction: '',
}
