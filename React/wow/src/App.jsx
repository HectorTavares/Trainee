import './App.css'
import { Route, Switch, Redirect, useHistory } from 'react-router-dom'
import { useGlobalUser, useGlobalCharacter } from './context'
import {
  LoginScreen,
  BattleScreen,
  StoreScreen,
  WorldScreen,
  CharacterSelectionScreen,
  QuestsScreen,
  Battlefield,
} from '../src/ui'
import { GlobalCharacterProvider } from './context'
import { PATHS } from '././constants'

function PrivateRoute({ path, children }) {
  const history = useHistory()

  const [user] = useGlobalUser()

  if (!user?.token) {
    history.push(PATHS.LOGIN)
  }

  return (
    <Route path={path} exact>
      {children}
    </Route>
  )
}

function WorldRoute({ path, children }) {
  const [character] = useGlobalCharacter()

  if (!character) {
    return <Redirect to={PATHS.CHARACTER_SELECTION} />
  }

  return (
    <PrivateRoute path={path} exact>
      {children}
    </PrivateRoute>
  )
}

function App() {
  return (
    <div className="App">
      <Switch>
        <Route path={PATHS.LOGIN} exact>
          <LoginScreen />
        </Route>
        <GlobalCharacterProvider>
          <PrivateRoute path={PATHS.CHARACTER_SELECTION} exact>
            <CharacterSelectionScreen />
          </PrivateRoute>
          <WorldRoute path={PATHS.WORLD} exact>
            <WorldScreen />
          </WorldRoute>
          <WorldRoute path={PATHS.STORE} exact>
            <StoreScreen />
          </WorldRoute>
          <WorldRoute path={PATHS.BATTLE} exact>
            <BattleScreen />
          </WorldRoute>
          <WorldRoute path={PATHS.QUESTS} exact>
            <QuestsScreen />
          </WorldRoute>
          <WorldRoute path={PATHS.BATTLEFIELD} exact>
            <Battlefield />
          </WorldRoute>
        </GlobalCharacterProvider>
        <Route path="/">
          <Redirect to={PATHS.LOGIN} />
        </Route>
      </Switch>
    </div>
  )
}

export default App
