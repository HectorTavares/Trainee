import './menu-container.style.css'

export const MenuContainer = ({ children, hasScroll }) => {
  const scroll = hasScroll ? 'scroll' : ''
  return <div className={`menu ${scroll}`}>{children}</div>
}
