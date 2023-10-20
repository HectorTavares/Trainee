import './passenger-perfil.css'
import userPNG from '../../../assets/user.png'
export function PassengerPerfil({ passenger }) {
  return (
    <div className="passengerperfil">
      <img className="driverperfil--img" src={userPNG} alt="foto do usuario" />
      <h1>{passenger?.nome}</h1>
      <h1>{passenger?.cpf?.numero}</h1>
      <h1>{passenger?.dataNascimento}</h1>
      <h1>{passenger?.email}</h1>
    </div>
  )
}
