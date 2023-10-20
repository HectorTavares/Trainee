import { useEffect, useState } from 'react'
import { Link, useHistory } from 'react-router-dom'
import { useMeLevaAiAPI } from '../../../api'
import { useGlobalUser } from '../../../context/cpf-type/cpf-type.context'
import { FormButton } from '../../components'
import './login.style.css'
import { ROUTES } from '../../../routes'

export function LoginScreen() {
  const meLevaAiApi = useMeLevaAiAPI()
  const [, setUser] = useGlobalUser()
  const [error, setError] = useState(false)
  const [inputValues, setInputValues] = useState({
    name: '',
    cpf: '',
  })
  const { push, location } = useHistory()
  const type = location?.type

  useEffect(() => {
    if (!type) {
      push(ROUTES.home)
    }
  }, [push, type])

  function handleChange(changeEvent) {
    const { name, value } = changeEvent.target

    setInputValues(currentValues => ({ ...currentValues, [name]: value }))
  }

  async function handleSubmit(submitEvent) {
    submitEvent.preventDefault()

    try {
      if (type === 'driver') {
        await meLevaAiApi.getMotoristaByCpf(inputValues.cpf)
      } else {
        await meLevaAiApi.getPassageiroByCpf(inputValues.cpf)
      }

      setUser({ cpf: inputValues.cpf, type: type })
      setError(false)
      push(ROUTES.menu)
    } catch (error) {
      setError(true)
    }
  }

  return (
    <section className="loginScreen">
      <div className="loginScreen__content">
        <h1 className="loginScreen__tittle">Login</h1>
        <form onSubmit={handleSubmit} className="loginScreen__form">
          <label className="loginScreen__label">
            Nome :
            <input
              placeholder="Seu Nome"
              onChange={handleChange}
              name="name"
              value={inputValues.name}
              className="loginScreen__input"
            />
          </label>
          <label className="loginScreen__label">
            CPF :
            <input
              placeholder="xxx-xxx-xxx.xx"
              onChange={handleChange}
              name="cpf"
              value={inputValues.cpf}
              className="loginScreen__input"
            />
          </label>
          <FormButton tittle="entrar"></FormButton>
          <div className="loginScreen__error">
            {error ? <p className="loginScreen__error-msg">Nome ou CPF inválidos</p> : null}
          </div>
          <Link to={`/register/${type}`}>
            <p>Ainda não tem conta?</p>
          </Link>
        </form>
      </div>
    </section>
  )
}
