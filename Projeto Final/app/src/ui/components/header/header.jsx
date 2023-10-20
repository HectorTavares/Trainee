import './header.css'
import { useEffect, useState } from 'react'
import { useHistory } from 'react-router'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { Loader, Notification } from '../index'
import { toast } from 'react-toastify'
import { variables, paths, errorMessage } from '../../../constants/index'
import logo from '../../../assets/image/logo.svg'
import bell from '../../../assets/image/bell.png'
import logout from '../../../assets/image/logout.png'
import defaultAvatar from '../../../assets/image/defaultAvatar.png'
import admin from '../../../assets/image/admin.svg'
import trash from '../../../assets/image/trash.png'

export function Header() {
  const [user, setUser] = useState()
  const [userRole, setUserRole] = useState()
  const [timer, setTimer] = useState(false)
  const [headerLoader, setHeaderLoader] = useState(true)

  const [notifications, setNotifications] = useState([])
  const [notificationLoader, setNotificationLoader] = useState(false)
  const [notificationWindow, setNotificationWindow] = useState(false)
  const [notificationWarning, setNotificationWarning] = useState(variables.HIDDEN_WARNING)

  const cwiRespostasApi = useCwiRespostasApi()
  const history = useHistory()

  useEffect(() => {
    setTimeout(() => {
      setTimer(!timer)
    }, 15000)
  }, [timer])

  useEffect(() => {
    async function getUser() {
      try {
        const userData = await cwiRespostasApi.user()
        setUser(userData)
        userData.isMonitor ? setUserRole(variables.AVATAR_MONITOR) : setUserRole(variables.AVATAR_USER)
        setHeaderLoader(false)
      } catch (error) {
        toast.error(errorMessage.USER_ERROR)
      }
    }
    getUser()
  }, [])

  useEffect(() => {
    async function getNotifications() {
      try {
        const response = await cwiRespostasApi.getNotifications()
        response.length > 0 ? setNotificationWarning('') : setNotificationWarning(variables.HIDDEN_WARNING)
        setNotifications(response)
      } catch (error) {
        toast.error(errorMessage.NOTIFICATION_LOAD_ERROR)
      }
    }
    getNotifications()
  }, [timer])

  function handleLogoClick() {
    history.push(paths.HOME)
    history.go(0)
  }
  function handleAdminClick() {
    history.push(paths.ADMIN)
  }

  function handleBellClick() {
    setNotificationWindow(!notificationWindow)
  }
  async function handleClearClick() {
    setNotificationLoader(true)
    setNotificationWindow(false)
    try {
      cwiRespostasApi.clearNotifications()
      setNotificationLoader(false)
      setNotifications([])
      setNotificationWarning(variables.HIDDEN_WARNING)
    } catch (error) {
      toast.error(errorMessage.NOTIFICATION_CLEAR_ERROR)
      setNotificationLoader(false)
    }
  }
  function HandleLogoutClick() {
    localStorage.removeItem('user-discord')
    history.push(paths.DEFAULT)
  }

  return (
    <div className="header_main">
      <div className="header_container">
        <div className="header_left-section">
          <button onClick={handleLogoClick} className="header_logo-button">
            <img src={logo} alt="Cwi Respostas" className="header_logo" />
          </button>
        </div>
        <div className="header_right-section">
          {headerLoader ? (
            <Loader size="small" />
          ) : (
            <div className="header_user-info">
              <div className="header-interface">
                <img
                  src={user?.avatar}
                  onError={e => {
                    e.target.src = defaultAvatar
                  }}
                  alt="Avatar do Usuario"
                  className={`header_avatar ${userRole}`}
                />
                Olá, {user.username}
                {user?.isMonitor && (
                  <div className="header_admin">
                    <button onClick={handleAdminClick} className="header_admin-button">
                      <img src={admin} alt="Botão da Administração" className="header_admin-button-image" />
                    </button>
                  </div>
                )}
                <div className="header_bell-section">
                  {notificationLoader ? (
                    <Loader size="small" />
                  ) : (
                    <button onClick={handleBellClick} className="header_bell-button ">
                      <img src={bell} alt="Sino de Notificação" className={`header_bell `} />
                      <div className={`header_notification-warning ${notificationWarning}`}></div>
                    </button>
                  )}
                  {notificationWindow && (
                    <div className="header_notification-window">
                      <button
                        className="header_clear-notifications-button"
                        disabled={notifications.length === 0}
                        onClick={handleClearClick}
                      >
                        <img className="header_trash-icon" src={trash} alt="Limpar Notificações" />
                      </button>
                      <div className="header_notification-container">
                        {notifications.length > 0 ? (
                          notifications.map(notification => (
                            <Notification
                              key={notification.id}
                              notification={notification}
                              setNotificationLoader={setNotificationLoader}
                              setNotificationWindow={setNotificationWindow}
                            />
                          ))
                        ) : (
                          <div className="header_nothing-new">Nada de novo por aqui</div>
                        )}
                      </div>
                    </div>
                  )}
                </div>
                <button onClick={HandleLogoutClick} className="header_logout-button">
                  <img src={logout} alt="Botão de Logout" className="header_logout" />
                </button>
              </div>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
