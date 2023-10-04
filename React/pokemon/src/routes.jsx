import { Switch, Route, Redirect } from 'react-router-dom'
import {
  BattleScreen,
  InitialPokemonsScreen,
  MenuScreen,
  PokecenterScreen,
  PokedexScreen,
  PokemonInfoScreen,
} from './ui/screens'

export const ROUTES = {
  INITIAL_POKEMON: '/initial-pokemon',
  MENU: '/menu',
  POKEDEX: '/pokedex',
  POKEMON: '/pokemon/:pokemonId',
  BATTLE: '/batalha',
  POKECENTER: '/pokecenter',
}

function InitialRoute({ children, ...props }) {
  const initialWasChosen = localStorage.getItem('initial_was_chosen')

  if (initialWasChosen) {
    return <Redirect to={ROUTES.MENU} />
  }

  return (
    <>
      <Route {...props}>{children}</Route>
    </>
  )
}

function NormalRoute({ children, ...props }) {
  const initialWasChosen = localStorage.getItem('initial_was_chosen')
  if (!initialWasChosen) {
    return <Redirect to={ROUTES.INITIAL_POKEMON} />
  }

  return (
    <>
      <Route {...props}>{children}</Route>
    </>
  )
}

export function Routes() {
  return (
    <Switch>
      <InitialRoute path={ROUTES.INITIAL_POKEMON} exact>
        <InitialPokemonsScreen />
      </InitialRoute>
      <NormalRoute path={ROUTES.MENU} exact>
        <MenuScreen />
      </NormalRoute>
      <NormalRoute path={ROUTES.POKEDEX} exact>
        <PokedexScreen />
      </NormalRoute>
      <NormalRoute path={ROUTES.BATTLE} exact>
        <BattleScreen />
      </NormalRoute>
      <NormalRoute path={ROUTES.POKECENTER} exact>
        <PokecenterScreen />
      </NormalRoute>
      <NormalRoute path={ROUTES.POKEMON} exact>
        <PokemonInfoScreen />
      </NormalRoute>
      <Route path="/">
        <Redirect to="/menu" />
      </Route>
    </Switch>
  )
}
