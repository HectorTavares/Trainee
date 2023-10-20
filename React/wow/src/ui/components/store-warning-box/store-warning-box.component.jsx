import { StoreButtonWarning } from '..'
import { StoreWarningContainer } from '../store-warning-container/store-warning-container.component'
import './store-warning-box.css'

export const StoreWarningBox = ({ warning, onClickOk }) => {
  return (
    <StoreWarningContainer>
      <div className="store-warning-box__content">
        <h2 className="store-warning-box__title">{warning}</h2>
      </div>

      <StoreButtonWarning title="Ok" onClickAction={onClickOk} />
    </StoreWarningContainer>
  )
}
