import './selectedCharacter.style.css'
export function SelectedCharacter({ character, onDeselectCharacter }) {
  function handleClick() {
    onDeselectCharacter(character.id)
  }
  const isEmpity = character || false
  return (
    <div className="selectedCharacter">
      {!!isEmpity ? (
        <>
          {' '}
          <h2 className="selectedCharacter__name">{character.nome}</h2>{' '}
          <img className="selectedCharacter__img" src={character.imagemDetalhe} alt={character.nome} />{' '}
          <button className="selectedCharacter__button" onClick={handleClick}>
            Remover
          </button>{' '}
        </>
      ) : (
        <> </>
      )}
    </div>
  )
}
