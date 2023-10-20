import { useState } from 'react'
import './driver-virtual-account.style.css'
import { useMeLevaAiAPI } from '../../../api'
import { useGlobalUser } from '../../../context'

export function DriverVirtualAccount({ driver, setDriver }) {
  const [inputValue, setInputValue] = useState(null)
  const [cpf] = useGlobalUser()

  const meLevaAPI = useMeLevaAiAPI()

  async function saque() {
    await meLevaAPI.sacar(cpf.cpf, inputValue)
  }

  function handleSubmit(submitEvent) {
    submitEvent.preventDefault()
    if (driver.conta > inputValue) {
      saque()
      setDriver({ ...driver, conta: driver.conta - inputValue })
    }
  }

  function handleChange(changeEvent) {
    const { value } = changeEvent.target
    setInputValue(value)
  }

  return (
    <div className="drivervirtualaccount">
      <h1 className="drivervirtualaccount--money">
        {driver?.conta?.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' })}
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
          <h1>Sacar</h1>
        </button>
      </form>
    </div>
  )
}
