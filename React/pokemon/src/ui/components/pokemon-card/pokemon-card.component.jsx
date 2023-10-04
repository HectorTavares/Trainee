import './pokemon-card.style.css'
export function PokemonCard({ nickname, id, name, image, onClick, life, disabled, type, onClickDisable, isChoosed }) {
  function handleClick() {
    if (onClickDisable) return

    onClick(id)
  }

  return (
    <button className={`pokemonCard__button ${type} ${isChoosed ? 'choosed': ''}`} disabled={disabled} onClick={handleClick}>
      {nickname ? (
        <h2 className={`pokemonCard__name  ${type}`}>{nickname}</h2>
      ) : (
        <h2 className={`pokemonCard__name  ${type}`}>{name}</h2>
      )}
      <img className="pokemonCard__image" src={image} alt={name} />
      {life !== null && <h2 className={`pokemonCard__name  ${type}`}>Vida:{life}</h2>}
    </button>
  )
}

PokemonCard.defaultProps = {
  life: null,
  disabled: false,
  type: '',
  onClick: null,
  onClickDisable: false,
  nickname: '',
}
