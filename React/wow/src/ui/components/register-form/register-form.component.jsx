import { useState } from 'react'
import { useWowApi } from '../../../api'
import { useHistory } from 'react-router-dom'
import { Input } from '../form-input/form-input.component'
import './index.css'
import { Loader } from '../loader/loader.component'

export const RegisterForm = ({ onBackLogin }) => {
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')
  const [logError, setLogError] = useState('')
  const [logDone, setLogDone] = useState('')
  const [isLoading, setIsLoading] = useState(false)

  const wowApi = useWowApi()
  const history = useHistory()

  const handleSubmit = async event => {
    event.preventDefault()

    if (username && password && confirmPassword) {
      setIsLoading(true)
      try {

        await wowApi.register(username, password, confirmPassword)
        setIsLoading(false)
        setLogDone('Conta Criada! Redirecionando...')
        setTimeout(() => {
          history.push('/')
        }, 4000)
      } catch (error) {
        setIsLoading(false)
        setLogError(error.response.data.message)
        setTimeout(() => {
          setLogError('')
        }, 7000)
      }
    } else {
      setLogError('Os campos devem ser preenchidos')
      setTimeout(() => {
        setLogError('')
      }, 7000)
    }
  }
  return (
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
            <label className="align-input-pass pass-register" htmlFor="password">
              Senha:
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
          <div className="label-form-login">
            <label className="align-input-pass pass-register" htmlFor="confirmPassword">
              Senha:
            </label>
            <Input
              className="input-login-screen"
              value={confirmPassword}
              autoComplete="off"
              onChange={setConfirmPassword}
              name="confirmPassword"
              type="password"
            />
          </div>
        </div>
        <button className="btn-form-login">Registrar</button>
      </form>
      <button onClick={onBackLogin} className="btn-form-login">
        Voltar
      </button>
      {!!logError && <p className="erro">{logError}</p>}
      {<p className="created">{logDone}</p>}

      {isLoading ? <Loader /> : null}

    </>
  )
}
