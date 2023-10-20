import './store-item-card.css'

export const StoreItemCard = ({ onClickAction, selectedAction, item }) => {
  const { id, name, image, type, enhancement, price, sellPrice } = item

  const itemPrice = selectedAction === 'compra' ? price : sellPrice

  const buttonTitle = selectedAction === 'compra' ? 'Comprar' : 'Vender'

  const handleOnClick = () => {
    onClickAction(id)
  }

  return (
    <>
      <div className="store-item-card">
        <div className="store-item-card__image-container">
          <img className="store-item-card__image" src={`${image}`} alt={`name`} />
        </div>

        <p className="store-item-card__title">{name}</p>

        <p className="store-item-card__stats">
          {type} {type !== 'EXPANSAO' ? '+' + enhancement : null}
        </p>

        <p className="store-item-card__price">${itemPrice}</p>

        <button className="store-item-card__button" onClick={handleOnClick}>
          {buttonTitle}
        </button>
      </div>
    </>
  )
}
