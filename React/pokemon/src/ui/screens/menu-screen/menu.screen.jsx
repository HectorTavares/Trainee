import './menu.style.css'
import { MenuButton } from './menu-button/menu-button.component'
import { useHistory } from 'react-router-dom'

export function MenuScreen() {
  const { push } = useHistory()

  function onGoToBattleScreen() {
    push('/batalha')
  }

  function onGoToPokedex() {
    push('/pokedex')
  }

  function onGoToPokecenter() {
    push('/pokecenter')
  }

  return (
    <section className="menuScreen__content">
      <MenuButton onClick={onGoToPokedex}>
        <h2>POKÉDEX</h2>
      </MenuButton>
      <MenuButton onClick={onGoToBattleScreen}>
        <h2>BATALHA</h2>
      </MenuButton>
      <MenuButton onClick={onGoToPokecenter}>
        <h2>POKECENTER</h2>
      </MenuButton>
    </section>
  )
}
