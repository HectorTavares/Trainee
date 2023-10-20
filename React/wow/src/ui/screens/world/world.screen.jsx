import './index.css'
import logoWow from '../../../assets/img/foto-wow.png'
import { useHistory } from 'react-router'
import { useState } from 'react'
import { Input, Chat } from '../../components'
import { useWowApi } from '../../../api'
import { useGlobalCharacter } from '../../../context'
import { getCheatMessage } from '../../../core'

const CHEATS = [
  'WILLIDAN',
  'GUSTHRALL',
  'ANDREWRYNN',
  'JULICHKING',
  'FABYOGGSARON',
  'PABLOTHAR',
  'VITOREXXAR',
  'ZORZARTHAS',
  'DIANDRAKA',
  'SERGIORGRIM',
  'MURILOKK',
  'CAMILLEISHEN',
  'LUANDUIN',
  'EMERSONERZHUL',
]

const verifyCheat = word => {
  return CHEATS.some(cheat => cheat === word)
}

export const WorldScreen = () => {
  const history = useHistory()
  const [character] = useGlobalCharacter()
  const [welcomeChat] = useState('BEM VINDO AO CHAT')
  const [inputData, setInputData] = useState()
  const [chatData, setChatData] = useState([])
  const [npc1Msg] = useState(['Bem vindo de volta!'])
  const [achieves, setAchieves] = useState([])
  const wowApi = useWowApi()
  const [logError, setLogError] = useState()

  const isCheat = async msgInput => {
    if (verifyCheat(msgInput)) {
      try {
        await wowApi.getAchieveFromCheats(msgInput, character.id)
        setAchieves([...achieves, getCheatMessage(msgInput)])
      } catch (error) {
        setLogError('Deu pau no seu cheat')
      }
    }
  }

  const handleClickChat = event => {
    event.preventDefault()

    if (inputData) {
      setChatData([...chatData, inputData])
      isCheat(inputData)
      setInputData('')
    }
  }

  const handleClick = event => {
    if (event.target.name === 'battle') {
      history.push('/battle')
    } else if (event.target.name === 'store') {
      history.push('/store')
    } else if (event.target.name === 'quests') {
      history.push('/quests')
    } else {
      history.push('/')
    }
  }

  return (
    <>
      <div className="world-of-react-screen">
        <button name="back" onClick={handleClick} className="btn-options back-char-screen">
          VOLTAR
        </button>
        <h1 className="title-world">
          BEM VINDO AO WORLD OF REACT! {<div className="achieves">[ {character.name} ]</div>}
        </h1>
        <h2 className="title-world">SELECIONE O QUE GOSTARIA DE FAZER!</h2>
        <img className="img-logo-world" src={logoWow} alt="logo-wow.png" />

        <section>
          <button name="battle" onClick={handleClick} className="btn-options">
            BATALHAR
          </button>
          <button name="store" onClick={handleClick} className="btn-options">
            LOJA
          </button>
          <button name="quests" onClick={handleClick} className="btn-options">
            MISSÕES
          </button>
        </section>

        <section className="chat-container">
          <h1 className="title-chat">{welcomeChat}</h1>
          <div className="content-chat">
            <p className="green">{`Deomen, o Vórtice: ${npc1Msg}`}</p>
            {chatData.map(chatmsg => (
              <Chat contentMsg={chatmsg} />
            ))}
            {achieves.map(achieve => (
              <p className="achieves">{achieve}</p>
            ))}
            <p className="error">{logError}</p>
          </div>
          <form onSubmit={handleClickChat}>
            <Input
              placeholder="Digite algo aqui e seja feliz!"
              onChange={setInputData}
              autoComplete="off"
              value={inputData}
              type="text"
              name="chat"
              className="input-chat-container"
            />
          </form>
        </section>
      </div>
    </>
  )
}
