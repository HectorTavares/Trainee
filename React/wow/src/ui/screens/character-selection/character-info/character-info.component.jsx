import './character-info.style.css'

export const CharacterInfo = ({ character }) => {
  return (
    <div className="characterInfo">
      <h2 className="characterInfo__title">
        Informações <br />
        do personagem
      </h2>

      <ul className="characterInfo__list">
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Nome: {character.name}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Raca : {character.race.name}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Level : {character.level}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Experiencia : {character.experience}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Experiencia faltando : {character.experienceToNextLevel}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Dinheiro : {character.money} G</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Dano : {character.totalDamage}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Vigor : {character.totalVigor}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Vida : {character.totalLife}</p>
        </li>
        <li className="characterInfo__Info">
          <p className="characterInfo__paragraph">Ocupado : {character.busy ? 'Sim' : 'Não'}</p>
        </li>
      </ul>
    </div>
  )
}
