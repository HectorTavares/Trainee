import { useEffect, useState } from 'react'
import { useMeLevaAiAPI } from '../../../api'
import { useGlobalUser } from '../../../context'
import { PassengerPerfil, PassengerVirtualAccount, Race } from '../../components'
import './passenger.style.css'

function millisToMinutesAndSeconds(millis) {
  var minutes = Math.floor(millis / 60000)
  var seconds = ((millis % 60000) / 1000).toFixed(0)
  return minutes + ':' + (seconds < 10 ? '0' : '') + seconds
}

export function PassengerScreen() {
  const [cpf] = useGlobalUser()
  const [passenger, setPassenger] = useState({})
  const [responseChamarCorrida, setResponseChamarCorrida] = useState(null)
  const [responseIniciaCorrida, setResponseIniciaCorrida] = useState(null)
  const [responseTerminaCorrida, setResponseTerminaCorrida] = useState(false)

  const meLevaAPI = useMeLevaAiAPI()
  useEffect(() => {
    async function getPassenger() {
      const passenger1 = await meLevaAPI.getPassageiroByCpf(cpf.cpf)
      setPassenger(passenger1)
    }
    getPassenger()
  }, [meLevaAPI])

  useEffect(() => {
    async function chamarCorrida() {
      if (responseChamarCorrida !== null) {
        const response = await meLevaAPI.iniciarCorrida(responseChamarCorrida.idCorrida)
        setResponseIniciaCorrida(response)
      }
    }
    chamarCorrida()
  }, [responseChamarCorrida])

  console.log(responseChamarCorrida)
  console.log(responseIniciaCorrida)
  console.log(responseTerminaCorrida)

  return (
    <section className="passengerscreen">
      <div className="div--container">
        <PassengerPerfil passenger={passenger} />
      </div>
      <div className="div--container passengerscreen--race">
        <Race setResponseChamarCorrida={setResponseChamarCorrida} />
        {responseChamarCorrida ? (
          <h1 className="tempo--motorista">
            Tempo para chegada do Motorista: {millisToMinutesAndSeconds(responseChamarCorrida.tempoCorrida)}
          </h1>
        ) : null}
        {responseIniciaCorrida ? (
          <div className="inicia--corrida">
            <h1 className="tempo--motorista">
              Tempo estimado de chegada (segundos): {responseIniciaCorrida.tempoEstimadoDoPercursoEmSegundos}
            </h1>
            <h1 className="tempo--motorista">
              Valor Estimado da Corrida: {responseIniciaCorrida.valorEstimadoCorrida} reais
            </h1>
          </div>
        ) : null}
        {responseTerminaCorrida === true ? <h1>Corrida Finalizada</h1> : null}
      </div>
      <div className="div--container--passenger">
        <h1 className="driverscreen--contavirtual--title">ContaVirtual</h1>
        <PassengerVirtualAccount passenger={passenger} setPassenger={setPassenger} />
      </div>
    </section>
  )
}
/**
 * useEffect(() => {
    async function terminaCorrida() {
      if (responseIniciaCorrida !== null) {
        await meLevaAPI.terminarCorrida(responseChamarCorrida.idCorrida)
        setResponseTerminaCorrida(true)
      }
    }
    terminaCorrida()
  }, [responseIniciaCorrida])

 */
