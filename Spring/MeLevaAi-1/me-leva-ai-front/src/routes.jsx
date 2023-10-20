import { Redirect, Route, Switch } from 'react-router'
import {
  HomeScreen,
  LoginScreen,
  PassengerRegisterScreen,
  DriverRegisterScreen,
  MenuScreen,
} from './ui/screens'

export const ROUTES = {
  login: '/login',
  registerPassenger: '/register/passenger',
  registerDriver: '/register/driver',
  menu: '/menu',
  home: '/home',
}

export function Routes() {
  return (
    <Switch>
      <Route path={ROUTES.login} exact>
        <LoginScreen />
      </Route>
      <Route path={ROUTES.registerDriver} exact>
        <DriverRegisterScreen />
      </Route>
      <Route path={ROUTES.registerPassenger} exact>
        <PassengerRegisterScreen />
      </Route>
      <Route path={ROUTES.menu} exact>
        <MenuScreen />
      </Route>
      <Route path={ROUTES.home} exact>
        <HomeScreen />
      </Route>
      <Route path="/">
        <Redirect to={ROUTES.home} />
      </Route>
    </Switch>
  )
}
