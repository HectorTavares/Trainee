import createGlobalState from 'react-create-global-state'

const [useGlobalCharacter, GlobalCharacterProvider] = createGlobalState(null)
export { useGlobalCharacter, GlobalCharacterProvider }
