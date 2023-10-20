import './driver.style.css'
import { useMeLevaAiAPI } from '../../../api'
import { useEffect, useState } from 'react'
import { DriverPerfil, DriverCars, DriverVirtualAccount } from '../../components'
import { useGlobalUser } from '../../../context'

export function DriverScreen() {
  const [cpf] = useGlobalUser()
  const [driver, setDriver] = useState({})
  const [cars, setCars] = useState([])

  const meLevaAPI = useMeLevaAiAPI()
  useEffect(() => {
    async function getDriver() {
      const driver = await meLevaAPI.getMotoristaByCpf(cpf.cpf)
      setDriver(driver)
    }
    async function getCars() {
      const cars = await meLevaAPI.getVeiculos()
      setCars(cars)
    }
    getDriver()
    getCars()
  }, [meLevaAPI])

  return (
    <section className="driverscreen">
      <div className="perfil--motorista div--container">
        <DriverPerfil driver={driver} />
      </div>
      <div className="contavirtual--motorista div--container">
        <DriverCars cars={cars} driver={driver} />
      </div>
      <div className="veiculos--motoristas div--container">
        <h1 className="driverscreen--contavirtual--title">ContaVirtual</h1>
        <DriverVirtualAccount driver={driver} setDriver={setDriver} />
      </div>
    </section>
  )
}
