import './confirmation-box.style.css'
import { Button } from '../../../components'

export const ConfirmationBox = ({ onAccept, onDecline, tittle, paragraph }) => {
  return (
    <div className="confirmationBox">
      <h2>{tittle}</h2>
      <p>{paragraph}</p>
      <div className="confirmationBox__buttons">
        <Button onClick={onAccept}>Aceitar</Button>
        <Button onClick={onDecline}>Recusar</Button>
      </div>
    </div>
  )
}
