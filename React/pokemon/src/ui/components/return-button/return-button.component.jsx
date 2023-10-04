import './return-button.style.css'
export function ReturnButton({ onReturn, disabled }) {
  return <button className="return-button" onClick={onReturn} disabled={disabled}></button>
}

ReturnButton.defaultProps = {
  disabled: false,
}
