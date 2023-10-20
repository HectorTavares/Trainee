import './store-warning-container.css'

export const StoreWarningContainer = ({ children }) => {
  return (
    <div className="store-warning-container">
      <div className="store-warning-container-content">{children}</div>
    </div>
  )
}
