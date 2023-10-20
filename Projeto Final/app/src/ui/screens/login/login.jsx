import './login.css'
import { login } from '../../../constants/index'
import logo from '../../../assets/image/logo.svg'

export function Login() {
  localStorage.removeItem('user-discord')

  return (
    <div className="login_main">
      <img src={logo} alt="CWI Respostas" className="login_logo" />
      <a href={login.JENKINS} className="login_discord-link">
        Entrar pelo Discord
      </a>
    </div>
  )
}
