import "./index.css";
import { padrao } from "../../herois";

export function Card({
  name = padrao[0].name,
  description = padrao[0].description,
  color = padrao[0].color,
  image = padrao[0].image,
  background = padrao[0].background,
}) {
  return (
    <section className="card">
      <div className="background-img">
        <img src={background} alt="fundo de super heroi" />
        <div className={`color ${color}`}>
          <div className="content">
            <div className="hero-img">
              <img src={image} alt="" />
            </div>
            <div className="name-description">
              <h2 className="name">{name}</h2>
              <p className="description">{description}</p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
