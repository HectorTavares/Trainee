import createGlobalState from 'react-create-global-state'

const userJSON = localStorage.getItem('user-discord')
const user = JSON.parse(userJSON)
const [_useGlobalUserDiscord, GlobalUserDiscordProvider] = createGlobalState(user)

const useGlobalUserDiscord = () => {
  const [globalUserDiscord, _setGlobalUserDiscord] = _useGlobalUserDiscord()

  const setGlobalUserDiscord = newGlobaUSer => {
    _setGlobalUserDiscord(newGlobaUSer)
    localStorage.setItem('user-discord', JSON.stringify(newGlobaUSer))
  }

  return [globalUserDiscord, setGlobalUserDiscord]
}

export { useGlobalUserDiscord, GlobalUserDiscordProvider }
