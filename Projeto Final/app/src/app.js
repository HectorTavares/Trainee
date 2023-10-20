import './app.css';
import { Admin, Authentication, Home, Login, Post, Posting } from './ui/screens/index'
import { Switch, Route, Redirect } from 'react-router-dom'
import { useGlobalUserDiscord } from './context/user-discord/user-discord-context';
import {paths} from './constants/index'


function App() {
  function PrivateRoute({ path, children }) {
    const [user] = useGlobalUserDiscord();

    if (!user) {
      return <Redirect to={paths.DEFAULT} />;
    }
    return (
      <Route path={path} exact>
        {children}
      </Route>
    );
  }

  return (
    <Switch>
      <Route path={paths.DEFAULT} exact>
        <Login />
      </Route>
      <Route path={paths.AUTHENTICATION} exact>
        <Authentication />
      </Route>
      <PrivateRoute path={paths.HOME} exact>
        <Home />
      </PrivateRoute>
      <PrivateRoute path={paths.POSTING} exact>
        <Posting />
      </PrivateRoute>
      <PrivateRoute path={paths.ADMIN} exact>
        <Admin />
      </PrivateRoute>
      <PrivateRoute path={paths.POST} exact>
        <Post />
      </PrivateRoute>
      <Route path={paths.DEFAULT}>
        <Login />
      </Route>
    </Switch>
  );
}

export default App;
