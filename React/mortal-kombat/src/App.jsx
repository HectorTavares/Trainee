import './App.css'
import { CharacterSelectionScreen } from './ui/screen'

function App() {
  return (
    <div className="app">
      <header className="app__header">
        <img
          className="app__logo"
          src="https://images-ext-1.discordapp.net/external/NpzmaNb8KcrbvIMRTgVw3goPiVROm9g9oRoUqtvx2gE/%3Fcb%3D20140315235821/https/vignette.wikia.nocookie.net/logopedia/images/8/89/Mk2_logo.png/revision/latest"
          alt="logo"
        />
      </header>
      <CharacterSelectionScreen />
    </div>
  )
}

export default App
