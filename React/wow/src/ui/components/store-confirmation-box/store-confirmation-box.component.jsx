import './store-confirmation-box.css'
import { StoreWarningContainer } from '../store-warning-container/store-warning-container.component'
import { StoreButtonWarning } from '..'

export const StoreConfirmationBox = ({ title, onConfirmClick, onCancelClick }) => {
  return (
    <StoreWarningContainer>
      <h2 className="store-confirmation-box__title">{title}</h2>

      <div className="store-confirmation-box__btns">
        <StoreButtonWarning title="Confirmar" onClickAction={onConfirmClick} />
        <StoreButtonWarning title="Cancelar" onClickAction={onCancelClick} />
      </div>
    </StoreWarningContainer>
  )
}
