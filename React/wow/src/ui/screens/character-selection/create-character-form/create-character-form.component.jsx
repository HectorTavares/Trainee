import { useEffect, useState } from 'react'
import { useWowApi } from '../../../../api/wow/use-wow-api'
import './create-character-form.style.css'
import { Button } from '../../../components'

const validInputs = inputs => {
  if (inputs.name.length < 0 || inputs.name.length > 20) return false
  if (inputs.raceId.length === 0) return false
  if (inputs.faction.length === 0) return false

  return true
}

export const CreateCharacterForm = ({ toggleCreateCharacter }) => {
  const [selectedRace, setSelectedRace] = useState(null)
  const [races, setRaces] = useState([])
  const [inputValues, setInputValues] = useState({ name: '', raceId: '', faction: '' })
  const [errorMessage, setErrorMessage] = useState()
  const [submitIsAble, setSubmitIsAble] = useState(validInputs(inputValues))

  const wowApi = useWowApi()

  useEffect(() => {
    const getRaces = async () => {
      try {
        const response = await wowApi.getRaces()
        setRaces(response)
      } catch (error) {}
    }

    getRaces()
  }, [wowApi])

  useEffect(() => {
    const raceSelected = races.find(race => race.id === parseInt(inputValues.raceId))
    setSelectedRace(raceSelected)
  }, [inputValues.raceId])

  useEffect(() => {
    setSubmitIsAble(validInputs(inputValues))
  }, [inputValues])

  const handleSubmit = async submitEvent => {
    submitEvent.preventDefault()

    const newCharacter = { ...inputValues, raceId: parseInt(inputValues.raceId) }

    try {
      await wowApi.createNewCharacter(newCharacter)
      toggleCreateCharacter()
    } catch (error) {
      setErrorMessage(error.response.data.message)
    }
  }

  const handleChange = changeEvent => {
    const { name, value } = changeEvent.target

    setInputValues(currentValues => ({ ...currentValues, [name]: value.trim() }))
  }

  return (
    <div className="createCharacterForm">
      <div className="createCharacterForm__exit-form-button">
        <Button onClick={toggleCreateCharacter}>
          <p className="characterSelectionScreen__button-text">X</p>
        </Button>
      </div>

      <form className="createCharacterForm__form" onSubmit={handleSubmit}>
        <div className="createCharacterForm__Inputs">
          <label>
            Nome :
            <input
              className="createCharacterForm__input createCharacterForm__input-name"
              value={inputValues.name}
              onChange={handleChange}
              autoComplete="off"
              placeholder="Escolha o nome"
              name="name"
            />
          </label>
          <label>
            Facção :
            <select
              className="createCharacterForm__input createCharacterForm__input-faction"
              onChange={handleChange}
              name="faction"
              value={inputValues.faction}
            >
              <option value="" disabled></option>
              <option value="Horda">Horda</option>
              <option value="Alianca">Aliança</option>
            </select>
          </label>
          <label>
            Raça :
            <select
              className="createCharacterForm__input createCharacterForm__input-race"
              onChange={handleChange}
              name="raceId"
              value={inputValues.raceId}
            >
              <option value="" disabled></option>
              {races &&
                races.map(race => (
                  <option key={race.id} value={race.id}>
                    {race.name}
                  </option>
                ))}
            </select>
          </label>
        </div>

        {selectedRace && (
          <div className="createCharacterForm__info_div">
            <h3 className="createCharacterForm__info">{selectedRace.name}</h3>
            <h2 className="createCharacterForm__info">Atributos Base</h2>
            <p className="createCharacterForm__info">Vida : {selectedRace.baseLife}</p>
            <p className="createCharacterForm__info">Vigor : {selectedRace.baseVigor}</p>
            <p className="createCharacterForm__info">Dano : {selectedRace.baseDamage}</p>
            <h2 className="createCharacterForm__info">Requisitos</h2>
            <p className="createCharacterForm__info">Level : {selectedRace.minLvl || 0}</p>
            <p className="createCharacterForm__info">Expanção : {selectedRace.expansionId || 'nenhuma'}</p>
          </div>
        )}
        <div className="createCharacterForm__button-submit">
          <Button disabled={!submitIsAble}>
            <p className="createCharacterForm__button-submit"> Criar</p>
          </Button>
          {errorMessage && <p className="createCharacterForm__error ">{errorMessage}</p>}
        </div>
      </form>
    </div>
  )
}
