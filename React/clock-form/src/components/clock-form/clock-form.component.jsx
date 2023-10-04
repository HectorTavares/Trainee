import './clock-form.style.css'
import { useState } from 'react'

const ClockForm = ({ onChangeClockInfo, initialClockInfo }) => {
  const [clockInfo, setClockInfo] = useState(initialClockInfo)
  const [inputErrors, setInputErrors] = useState({
    description: false,
    hours: false,
    minutes: false,
    seconds: false,
  })
  console.log('initial: ', initialClockInfo)

  function handleSubmit(event) {
    event.preventDefault()

    if (clockInfo.description.lenth < 2 || clockInfo.description.lenth > 20) {
      setInputErrors({ ...inputErrors, description: true })
    }

    if (clockInfo.hours < 0 || clockInfo.hours > 24) {
      setInputErrors({ ...inputErrors, hours: true })
    }

    if (clockInfo.minutes < 0 || clockInfo.minutes > 60) {
      setInputErrors({ ...inputErrors, minutes: true })
    }

    if (clockInfo.seconds < 0 || clockInfo.seconds > 60) {
      setInputErrors({ ...inputErrors, seconds: true })
    }

    const dontHaveInputErrors =
      !inputErrors.description && !inputErrors.hours && !inputErrors.minutes && !inputErrors.seconds
    if (dontHaveInputErrors) {
      onChangeClockInfo(clockInfo)
    }
  }

  function handleChange(event) {
    const { name, value } = event.target
    const updatedClockInfo = { ...clockInfo, [name]: value }
    setClockInfo(updatedClockInfo)
  }
  console.log()
  return (
    <form className="clock-form">
      <input
        onChange={handleChange}
        value={clockInfo.description}
        autoComplete="off"
        className="clock-form__input"
        placeholder="Descrição"
        name="description"
      />
      <input
        onChange={handleChange}
        value={clockInfo.hours}
        autoComplete="off"
        className="clock-form__input"
        placeholder="Horas"
        name="hours"
      />
      <input
        onChange={handleChange}
        value={clockInfo.minutes}
        autoComplete="off"
        className="clock-form__input"
        placeholder="Minutos"
        name="minutes"
      />
      <input
        onChange={handleChange}
        value={clockInfo.seconds}
        autoComplete="off"
        className="clock-form__input"
        placeholder="Segundos"
        name="seconds"
      />

      <button onSubmit={handleSubmit} type="submit" className="clock-form__button">
        Atualizar
      </button>
    </form>
  )
}

export { ClockForm }
