import card from '../../../assets/img/card-char.png'
import './index.css'

export const CardCharacter = ({ name, raceName, image, level }) => {
  return (
    <>
      <div className="card-holder">
        <div className="container-pai">
          <img className="format-img-char" src={image} alt="img-char" />
          <div className="container-filho">
            <img src={card} alt="card-char.png" />
          </div>
          <div className="atributos-card">
            <p>Nome: {name}</p>
            <p>Raça: {raceName}</p>
            <p>Level: {level}</p>
          </div>
        </div>
      </div>
    </>
  )
}
