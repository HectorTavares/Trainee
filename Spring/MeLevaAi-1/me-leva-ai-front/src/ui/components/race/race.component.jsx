import { useState } from 'react'
import { useMeLevaAiAPI } from '../../../api'
import { useGlobalUser } from '../../../context'
import './race.style.css'

export function Race({ setResponseChamarCorrida }) {
  const [xInicial, setXInicial] = useState(null)
  const [yInicial, setYInicial] = useState(null)
  const [xFinal, setXFinal] = useState(null)
  const [yFinal, setYFinal] = useState(null)

  const [cpf] = useGlobalUser()
  const meLevaAPI = useMeLevaAiAPI()

  async function chamarCorrida() {
    const inicial = [xInicial, yInicial]
    const final = [xFinal, yFinal]
    const response = await meLevaAPI.chamarCorrida({ iniciais: inicial, finais: final }, cpf.cpf)
    setResponseChamarCorrida(response)
  }

  function handleSubmit(submitEvent) {
    submitEvent.preventDefault()
    chamarCorrida()
  }

  function handleXInicial(changeEvent) {
    const { value } = changeEvent.target
    setXInicial(value)
  }
  function handleYInicial(changeEvent) {
    const { value } = changeEvent.target
    setYInicial(value)
  }
  function handleXFinal(changeEvent) {
    const { value } = changeEvent.target
    setXFinal(value)
  }
  function handleYFinal(changeEvent) {
    const { value } = changeEvent.target
    setYFinal(value)
  }

  return (
    <div>
      <form className="race--form" onSubmit={handleSubmit} action="">
        <div className="race--form--div">
          <h1>Inicial</h1>

          <label htmlFor="">
            x:
            <input placeholder="0" onChange={handleXInicial} value={xInicial} type="text" />
          </label>

          <label htmlFor="">
            y:
            <input placeholder="0" onChange={handleYInicial} value={yInicial} type="text" />
          </label>
        </div>
        <div className="race--form--div">
          <h1>Final</h1>

          <label htmlFor="">
            x:
            <input placeholder="0" onChange={handleXFinal} value={xFinal} type="text" />
          </label>

          <label htmlFor="">
            y:
            <input placeholder="0" onChange={handleYFinal} value={yFinal} type="text" />
          </label>
        </div>
        <button>Iniciar Corrida</button>
      </form>
    </div>
  )
}
