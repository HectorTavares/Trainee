import './menu-button.style.css'

export function MenuButton({ children, onClick }) {
  return (
    <button className="button-menu" onClick={onClick}>
      {children}
    </button>
  )
}

MenuButton.defaultProps = {
  onClick: null,
}
