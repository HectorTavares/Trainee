import './store-button-menu.css'

export const StoreButtonMenu = ({ buttonTitle, onClickAction, selected }) => {
  const classSelection = selected ? 'able' : 'disabled'

  return (
    <button onClick={onClickAction} className={`store-button-menu store-button-${classSelection}`}>
      <span>{buttonTitle}</span>
    </button>
  )
}
