import './driverperfil.style.css'
import userPNG from '../../../assets/user.png'
export function DriverPerfil({ driver }) {
  return (
    <div className="driverperfil">
      <img className="driverperfil--img" src={userPNG} alt="foto do usuario" />
      <h1>{driver?.nome}</h1>
      <h1>{driver?.cpf?.numero}</h1>
      <h1>{driver?.dataNascimento}</h1>
      <h1>{driver?.email}</h1>
      <div className="driverperfil--cnh">
        <h1>CNH</h1>
        <h1>{driver?.cnh?.categoria}</h1>
        <h1>{driver?.cnh?.dataVencimento}</h1>
        <h1>{driver?.cnh?.numero}</h1>
      </div>
    </div>
  )
}
