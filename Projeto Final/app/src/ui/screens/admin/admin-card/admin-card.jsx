import './admin-card.css'
import { useCwiRespostasApi } from '../../../../api/Projetofinal-api/Projetofinal-api'
import { useState } from 'react'
import { Loader } from '../../../components/index'
import { toast } from 'react-toastify'
import { errorMessage } from '../../../../constants/index'
import defaultAvatar from '../../../../assets/image/defaultAvatar.png'

export function AdminCard({ user }) {
  const [monitor, setMonitor] = useState(user.isMonitor)
  const [loader, setLoader] = useState(false)

  const cwiRespostasApi = useCwiRespostasApi()

  async function handleDelegateClick() {
    setLoader(true)
    try {
      await cwiRespostasApi.delegate(user.email)
      setMonitor(true)
      setLoader(false)
    } catch (error) {
      toast.error(errorMessage.DELEGATE_ERROR)
      setLoader(false)
    }
  }

  async function handleRevokeClick() {
    setLoader(true)
    try {
      await cwiRespostasApi.revoke(user.email)
      setMonitor(false)
      setLoader(false)
    } catch (error) {
      toast.error(errorMessage.REVOKE_ERROR)
      setLoader(false)
    }
  }

  return (
    <div className="admin-card_main">
      <div className="admin-card_container">
        <div className="admin-card_user">
          <img
            src={user?.avatar}
            onError={e => {
              e.target.src = defaultAvatar
            }}
            alt="Avatar do Usuario"
            className="admin-card_avatar"
          />
          <div className="admin-card_username">
            <h4>{user.username}</h4>
          </div>
        </div>
        <div className="admin-card_interface">
          {loader ? (
            <Loader size="small" />
          ) : (
            <div>
              <button
                onClick={handleRevokeClick}
                className="admin-card_button admin-card_user-color"
                disabled={!monitor}
              >
                Usuário
              </button>
              <button
                onClick={handleDelegateClick}
                className="admin-card_button admin-card_monitor-color"
                disabled={monitor}
              >
                Monitor
              </button>
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
