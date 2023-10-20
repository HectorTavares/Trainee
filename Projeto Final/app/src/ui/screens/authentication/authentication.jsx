import './authentication.css'
import { useEffect } from 'react'
import { useHistory } from 'react-router'
import { useGlobalUserDiscord } from '../../../context/user-discord/user-discord-context'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { Loader } from '../../components'
import { toast } from 'react-toastify'
import { variables, paths, errorMessage } from '../../../constants/index'

export function Authentication() {
  const [userDiscord, setUserDiscord] = useGlobalUserDiscord()
  const cwiRespostasApi = useCwiRespostasApi()
  const history = useHistory()

  const fragment = new URLSearchParams(window.location.hash.slice(1))
  const accessToken = fragment.get(variables.ACCESS_TOKEN)
  accessToken ? setUserDiscord(accessToken) : history.push(paths.DEFAULT)

  useEffect(() => {
    async function login() {
      try {
        await cwiRespostasApi.login()
        history.push(paths.HOME)
      } catch (error) {
        toast.error(errorMessage.AUTHENTICATION_ERROR)
        history.push(paths.DEFAULT)
      }
    }

    userDiscord && login()
  }, [userDiscord])

  return (
    <div className="authentication_main">
      <Loader size="big" />
    </div>
  )
}
