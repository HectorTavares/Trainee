import './passenger-virtual-account.style.css'

import { useState } from 'react'
import { useMeLevaAiAPI } from '../../../api'
import { useGlobalUser } from '../../../context'

export function PassengerVirtualAccount({ passenger, setPassenger }) {
  const [inputValue, setInputValue] = useState(null)
  const [cpf] = useGlobalUser()

  const meLevaAPI = useMeLevaAiAPI()

  async function depositar() {
    await meLevaAPI.depositar(cpf.cpf, inputValue)
  }

  function handleSubmit(submitEvent) {
    submitEvent.preventDefault()
    if (passenger) {
      depositar()
      setPassenger({ ...passenger, conta: passenger.conta + parseFloat(inputValue) })
    }
  }

  function handleChange(changeEvent) {
    const { value } = changeEvent.target
    setInputValue(value)
  }

  return (
    <div className="drivervirtualaccount">
      <h1 className="drivervirtualaccount--money">
        {passenger?.conta?.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}
      </h1>
      <form className="drivervirtualaccount--form" onSubmit={handleSubmit} action="">
        <input
          className="drivervirtualaccount--input"
          onChange={handleChange}
          value={inputValue}
          type="text"
          placeholder="0"
        />
        <button className="drivervirtualaccount--button">
          <h1>Depositar</h1>
        </button>
      </form>
    </div>
  )
}
