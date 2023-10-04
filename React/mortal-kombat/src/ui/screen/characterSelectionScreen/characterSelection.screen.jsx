import characters from '../../../characters.json'
import { useState } from 'react'
import { Character, SelectedCharacter } from '../../components'
import './characterSelection.style.css'

export function CharacterSelectionScreen() {
  const [firstCharacter, setFirtsCharacter] = useState(null)
  const [secondCharacter, setSecondCharacter] = useState(null)

  function handleSelectCharacter(id) {
    const charactersSelected = !!firstCharacter && !!secondCharacter
    const sameFirstCharacter = firstCharacter?.id === id
    const sameSecondCharacter = secondCharacter?.id === id

    const cantSelectCharacters = charactersSelected || sameFirstCharacter || sameSecondCharacter

    if (cantSelectCharacters) {
      return
    }

    const selectedCharacter = characters.find(char => id === char.id)

    if (firstCharacter === null) {
      setFirtsCharacter(selectedCharacter)
    } else if (secondCharacter === null) {
      setSecondCharacter(selectedCharacter)
    }
  }

  function handleDeselectCharacter(id) {
    const firstWasDeselected = firstCharacter?.id === id

    if (firstWasDeselected) {
      setFirtsCharacter(null)
    } else {
      setSecondCharacter(null)
    }
  }

  return (
    <section className="selectionScreen__content">
      <SelectedCharacter
        key={1}
        className="selectionScreen__left"
        character={firstCharacter}
        onDeselectCharacter={handleDeselectCharacter}
      />
      <div className="selectionScreen__middle">
        <h1 className="selectionScreen__title">CHOOSE YOUR FIGHTER</h1>
        <div className="selectionScreen__characterslist">
          {characters.map(character => (
            <Character
              key={character.id}
              id={character.id}
              img={character.imagemListagem}
              onSelectCharacter={handleSelectCharacter}
            />
          ))}
        </div>
      </div>

      <SelectedCharacter
        handleClick={2}
        className="selectionScreen__right"
        character={secondCharacter}
        onDeselectCharacter={handleDeselectCharacter}
      />
    </section>
  )
}
