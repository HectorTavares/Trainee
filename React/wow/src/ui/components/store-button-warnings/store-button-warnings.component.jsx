import './store-button-warnings.css'

export const StoreButtonWarning = ({ title, onClickAction }) => {
  return (
    <button onClick={onClickAction} className="store-button-warning">
      {title}
    </button>
  )
}
