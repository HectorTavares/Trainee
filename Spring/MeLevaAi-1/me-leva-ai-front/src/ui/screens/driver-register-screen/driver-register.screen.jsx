import { useState } from 'react'
import './driver-register.style.css'
import { ROUTES } from '../../../routes'
import { useMeLevaAiAPI } from '../../../api'
import { useHistory } from 'react-router'
import { useGlobalUser } from '../../../context'
import { FormButton } from '../../components'

export function DriverRegisterScreen() {
  const { push } = useHistory()
  const [, setUser] = useGlobalUser()
  const meLevaiAiApi = useMeLevaAiAPI()
  const [error, setError] = useState(false)
  const [inputValues, setInputValues] = useState({
    name: '',
    cpf: '',
    birthDate: '',
    email: '',
    cnhCategory: '',
    cnhNumber: '',
    cnhDate: '',
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
        cnh: {
          categoria: inputValues.cnhCategory,
          dataVencimento: inputValues.cnhDate,
          numero: inputValues.cnhNumber,
        },
      }
      await meLevaiAiApi.cadastrarMotorista(formatedInputValues)
      setError(false)
      setUser({ cpf: inputValues.cpf, type: 'driver' })
      setTimeout(() => {
        push(ROUTES.menu)
      }, 2000)
    } catch (error) {
      setError(true)
    }
  }

  return (
    <section className="driverRegisterScreen">
      <div className="driverRegisterScreen__content">
        <h1>Registrar</h1>
        <form onSubmit={handleSubmit}>
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
          <h2>Cnh</h2>
          <label>
            Numero :
            <input
              placeholder="123456789100"
              onChange={handleChange}
              name="cnhNumber"
              value={inputValues.cnhNumber}
              className="passengerRegisterScreen__input"
            />
          </label>
          <label>
            Data de vencimento :
            <input
              placeholder="dd/mm/aaa"
              onChange={handleChange}
              name="cnhDate"
              value={inputValues.cnhDate}
              className="passengerRegisterScreen__input"
            />
          </label>
          <label>
            Categoria :
            <select onChange={handleChange} value={inputValues.cnhCategory} name="cnhCategory">
              <option value="" selected hidden></option>
              <option value="A">A</option>
              <option value="B">B</option>
              <option value="C">C</option>
              <option value="D">D</option>
              <option value="E">E</option>
              <option value="ACC">ACC</option>
            </select>
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
