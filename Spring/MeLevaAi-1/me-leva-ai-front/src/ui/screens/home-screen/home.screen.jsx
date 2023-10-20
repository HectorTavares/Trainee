import { useHistory } from 'react-router'
import './home.style.css'
import { ROUTES } from '../../../routes'

export function HomeScreen() {
  const { push } = useHistory()

  function onSelectMotorista() {
    push({
      pathname: ROUTES.login,
      type: 'driver',
    })
  }

  function onSelectPassageiro() {
    push({
      pathname: ROUTES.login,
      type: 'passenger',
    })
  }

  return (
    <section className="homeScreen">
      <h1>
        Seja bem vindo ao <b> Me leva ai</b>
      </h1>
      <p>O seu site de transporte.</p>
      <h2>Você é :</h2>
      <div className="homeScreen__buttons">
        <button onClick={onSelectMotorista} className="homeScreen__button">
          <p>Motorista</p>
        </button>
        <button onClick={onSelectPassageiro} className="homeScreen__button">
          <p>Passageiro</p>
        </button>
      </div>
    </section>
  )
}
