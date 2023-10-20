import { useState } from 'react'
import { useWowApi } from '../../../api'
import { useHistory } from 'react-router-dom'
import { useGlobalUser } from '../../../context'
import logoWow from '../../../assets/img/foto-wow.png'
import './index.css'
import { RegisterForm, Input, Loader } from '../../components'
import { PATHS } from '../../../constants'

export const LoginScreen = () => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [logError, setLogError] = useState('')
  const [user, setUser] = useGlobalUser()
  const [register, setRegister] = useState(true)
  const [displayNone, setDisplayNone] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  const wowApi = useWowApi()
  const history = useHistory()

  if (user.token) history.push(PATHS.LOGIN)

  const handleBackLogin = () => {
    setRegister(true)
    setDisplayNone('')
  }

  const handleRegister = () => {
    setRegister(false)
    setDisplayNone('none')
  }

  const handleSubmit = async event => {
    event.preventDefault()

    if (username && password) {
      setIsLoading(true)
      try {
        const user = await wowApi.login(username, password)
        setUser(user)
        setIsLoading(false)
        localStorage.setItem('user', JSON.stringify(user))
        history.push('/')
      } catch (error) {
        setIsLoading(false)
        setLogError(error.response.data.message)
        setTimeout(() => {
          setLogError('')
        }, 8000)
      }
    } else {
      setLogError('Os campos devem ser preenchidos')
      setTimeout(() => {
        setLogError('')
      }, 8000)
    }
  }

  return (
    <>
      <div className="align-with-background">
        <img src={logoWow} alt="wowLogo" className="img-logo-login" />

        <section className="login-screen">
          {register ? (
            <>
              <form onSubmit={handleSubmit} className="form-login-screen">
                <div className="align-content-inputs">
                  <div className="label-form-login">
                    <label htmlFor="username">Usuário: </label>
                    <Input
                      className="input-login-screen"
                      value={username}
                      autoComplete="off"
                      onChange={setUsername}
                      name="username"
                    />
                  </div>
                  <div className="label-form-login">
                    <label className="align-input-pass" htmlFor="password">
                      Senha:{' '}
                    </label>
                    <Input
                      className="input-login-screen"
                      value={password}
                      autoComplete="off"
                      onChange={setPassword}
                      name="password"
                      type="password"
                    />
                  </div>
                </div>
                <button className="btn-form-login">Jogar</button>
              </form>
            </>
          ) : (
            <RegisterForm onBackLogin={handleBackLogin} />
          )}
          <button className="btn-form-login" style={{ display: `${displayNone}` }} onClick={handleRegister}>
            Registrar
          </button>
          {!!logError && <p className="erro">{logError}</p>}
          {isLoading ? <Loader /> : null}
        </section>
      </div>
    </>
  )
}
