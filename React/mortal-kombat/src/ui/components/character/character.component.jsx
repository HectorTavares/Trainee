import './character.style.css'
export function Character({ id, img, onSelectCharacter }) {
  function handleClick() {
    onSelectCharacter(id)
  }
  return (
    <button onClick={handleClick} className="character" style={{ backgroundImage: `url(${img})` }}></button>
  )
}
