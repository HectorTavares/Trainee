import { useState } from 'react'
import { FormButton } from '../../components'
import './passenger-register.style.css'
import { useMeLevaAiAPI } from '../../../api'
import { useHistory } from 'react-router'
import { useGlobalUser } from '../../../context'
import { ROUTES } from '../../../routes'

export function PassengerRegisterScreen() {
  const [error, setError] = useState(false)
  const [, setUser] = useGlobalUser()
  const meLevaiAiApi = useMeLevaAiAPI()
  const { push } = useHistory()
  const [inputValues, setInputValues] = useState({
    name: '',
    cpf: '',
    birthDate: '',
    email: '',
  })

  function handleChange(changeEvent) {
    const { name, value } = changeEvent.target

    setInputValues(currentValues => ({ ...currentValues, [name]: value }))
  }

  async function handleSubmit(submitEvent) {
    submitEvent.preventDefault()

    try {
      const formatedInputValues = {
        nome: inputValues.name,
        dataNascimento: inputValues.birthDate,
        email: inputValues.email,
        cpf: inputValues.cpf,
      }
      await meLevaiAiApi.cadastrarPassageiro(formatedInputValues)
      setError(false)
      setUser({ cpf: inputValues.cpf, type: 'passenger' })
      setTimeout(() => {
        push(ROUTES.menu)
      }, 2000)
    } catch (error) {
      setError(true)
    }
  }

  return (
    <section className="passengerRegisterScreen">
      <div className="passengerRegisterScreen__content">
        <h1 className="passengerRegisterScreen__tittle">Registrar</h1>
        <form onSubmit={handleSubmit} className="passengerRegisterScreen__form">
          <label className="passengerRegisterScreen__label">
            Nome :
            <input
              placeholder="Seu nome"
              onChange={handleChange}
              name="name"
              value={inputValues.name}
              className="passengerRegisterScreen__input"
            />
          </label>
          <label className="passengerRegisterScreen__label">
            Cpf :
            <input
              placeholder="xxx.xxx.xxx-xx"
              onChange={handleChange}
              name="cpf"
              value={inputValues.cpf}
              className="passengerRegisterScreen__input"
            />
          </label>
          <label className="passengerRegisterScreen__label">
            Data de Nascimento :
            <input
              placeholder="dd/mm/aaaa"
              onChange={handleChange}
              name="birthDate"
              value={inputValues.birthDate}
              className="passengerRegisterScreen__input"
            />
          </label>
          <label className="passengerRegisterScreen__label">
            Email :
            <input
              placeholder="exemplo@exemplo.com"
              onChange={handleChange}
              name="email"
              value={inputValues.email}
              className="passengerRegisterScreen__input"
            />
          </label>
          <div className="passengerRegisterScreen__error">
            {error ? (
              <p className="passengerRegisterScreen__error-msg">Erro no preenchimento dos campos.</p>
            ) : null}
          </div>
          <div className="passengerRegisterScreen__button">
            <FormButton tittle="Registar"></FormButton>
          </div>
        </form>
      </div>
    </section>
  )
}
