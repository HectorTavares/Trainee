import { useState } from 'react'
import { Clock, ClockForm } from './components'
import { buildCurrentTimeObject } from './utils'

import './App.css'

const App = () => {
  const [clockInfo, setClockInfo] = useState(buildCurrentTimeObject())

  const handleChangeClockInfo = info => {
    setClockInfo(info)
  }

  return (
    <div className="app">
      <ClockForm onChangeClockInfo={handleChangeClockInfo} initialClockInfo={clockInfo} />
      <Clock {...clockInfo} />
    </div>
  )
}

export { App }
