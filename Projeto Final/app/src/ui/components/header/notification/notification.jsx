import { useHistory } from 'react-router'
import { useCwiRespostasApi } from '../../../../api/Projetofinal-api/Projetofinal-api'
import { toast } from 'react-toastify'
import { paths, errorMessage } from '../../../../constants/index'
import './notification.css'

export function Notification({ notification, setNotificationLoader, setNotificationWindow }) {
  const cwiRespostasApi = useCwiRespostasApi()
  const history = useHistory()

  async function handleNotificationClck() {
    setNotificationLoader(true)
    setNotificationWindow(false)
    try {
      const post = await cwiRespostasApi.getNotificationPost(notification.idPergunta)
      await cwiRespostasApi.deleteNotification(notification.idNotificacao)
      setNotificationLoader(false)
      history.push(paths.POST, { post })
    } catch (error) {
      toast.error(errorMessage.NOTIFICATION_ERROR)
      setNotificationLoader(false)
    }
  }

  return (
    <div className="notification_main">
      <div className="notification_container">
        <button onClick={handleNotificationClck} className="notification_button">
          {notification.mensagem}
        </button>
      </div>
    </div>
  )
}
