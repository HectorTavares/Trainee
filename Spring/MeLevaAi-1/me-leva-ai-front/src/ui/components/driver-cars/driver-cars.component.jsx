import './driver-cars.style.css'
import caricon from '../../../assets/car.png'
import { Link } from 'react-router-dom'

export function DriverCars({ cars, driver }) {
  const filteredCars = cars.filter(car => car.proprietario.cpf === driver?.cpf?.numero)

  const filteredCarsList = filteredCars.map(car => {
    return (
      <li className={`drivercars--li ${car.cor}`} key={car.placa}>
        <img className="drivercars--img" src={caricon} alt="icone do carro" />
        <h1>
          {car.placa} / {car.marca} / {car.modelo}
        </h1>
      </li>
    )
  })

  if (cars.length === 0) {
    return (
      <Link to="/cadastro/veiculo">
        <button className="drivervirtualaccount--button">
          <h1>Cadastrar Veiculo</h1>
        </button>
      </Link>
    )
  }
  return <ul className="drivercars--ul">{filteredCarsList}</ul>
}
