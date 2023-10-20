import './store.css'
import { useEffect, useState } from 'react'
import { StoreButtonMenu } from '../../components/store-button-menu/store-button-menu.component'
import { Link } from 'react-router-dom'
import { StoreConfirmationBox, StoreItemCard, StoreWarningBox, Loader, Error } from '../../components'
import { useWowApi } from '../../../api/wow/use-wow-api'
import { useGlobalCharacter } from '../../../context'

const SELECTED_ACTIONS = {
  SELECT_BUY: 'compra',
  SELECT_SELL: 'venda',
}

const ERROR_MESSAGES_API = {
  NOT_ENOUGH_MONEY: 'Você não tem dinheiro suficiente para comprar este item',
  ALREADY_BOUGHT: 'Você já comprou este item',
  EXPANSION_REQUIRED: 'Você não possui a expansão necessária para comprar o item',
  CHARACTER_BUSY: 'Seu personagem está com uma missão em andamento e não pode usar o shop.',
  MISSING_ITEM_SELL: 'Você não comprou este item',
}

const SUCCESS_WARNINGS = {
  API_SUCCESS_BUY: 'Compra realizada com sucesso!',
  API_SUCCESS_SELL: 'Venda realizada com sucesso!',
}

const verifyReasonError = errorData => {
  const { message } = errorData

  switch (message) {
    case ERROR_MESSAGES_API.ALREADY_BOUGHT:
      return 'O personagem já possui este item!'

    case ERROR_MESSAGES_API.CHARACTER_BUSY:
      return 'O persanagem está ocupado em missão!'

    case ERROR_MESSAGES_API.EXPANSION_REQUIRED:
      return 'Falta de expansão necessária!'

    case ERROR_MESSAGES_API.NOT_ENOUGH_MONEY:
      return 'Dinheiro insuficiente!'

    case ERROR_MESSAGES_API.MISSING_ITEM_SELL:
      return 'Impossível vender itens que você não possui!'

    default:
      return 'Não foi possível!'
  }
}

const ItemsList = ({ itemsList, selectedAction, onClickAction }) => {
  const allItems = itemsList ? itemsList : []
  return (
    <ul className="store-items-list">
      {allItems.map(item => (
        <li className="store-items-list__card" key={item.id}>
          <StoreItemCard onClickAction={onClickAction} selectedAction={selectedAction} item={item} />
        </li>
      ))}
    </ul>
  )
}

export const StoreScreen = () => {
  const [globalIdCharacter] = useGlobalCharacter()
  const [isLoading, setIsLoading] = useState(true)
  const [hasError, setHasError] = useState()
  const [warning, setWarning] = useState('')
  const [selectedItem, setSelectedItem] = useState(null)
  const [confirmRequest, setConfirmRequest] = useState('')
  const [character, setCharacter] = useState(null)
  const [selectedAction, setSelectedAction] = useState(SELECTED_ACTIONS.SELECT_BUY)
  const [itemsShown, setItemsShown] = useState([])

  const wowApi = useWowApi()

  useEffect(() => {
    const getItemsBuy = async () => {
      setIsLoading(true)

      try {
        const updatedCharacter = await wowApi.getMyCharacterById(globalIdCharacter.id)
        setCharacter(updatedCharacter)
        const allItensShop = await wowApi.getShop()

        setItemsShown(allItensShop)

        setIsLoading(false)
        setHasError(false)
      } catch (error) {
        setHasError(true)
        setIsLoading(false)
      }
    }

    const getItemsSell = async () => {
      setIsLoading(true)

      try {
        const updatedCharacter = await wowApi.getMyCharacterById(globalIdCharacter.id)

        setCharacter(updatedCharacter)
        setItemsShown(updatedCharacter.items)
        setIsLoading(false)
        setHasError(false)
      } catch (error) {
        setHasError(true)
        setIsLoading(false)
      }
    }

    if (warning === '') {
      if (selectedAction === SELECTED_ACTIONS.SELECT_BUY) {
        getItemsBuy()
      } else {
        getItemsSell()
      }
    }
  }, [selectedAction, wowApi, warning])

  const handleCancel = () => {
    setConfirmRequest('')
  }

  const handleConfirmAction = idItem => {
    setConfirmRequest(selectedAction)
    setSelectedItem(idItem)
  }

  const buyItem = async idItem => {
    setIsLoading(true)
    try {
      await wowApi.buyItem(idItem, globalIdCharacter.id)
      setWarning(SUCCESS_WARNINGS.API_SUCCESS_BUY)

      setIsLoading(false)
      setHasError(false)
    } catch (error) {
      setIsLoading(false)
      const newWarning = verifyReasonError(error.response.data)
      setWarning(newWarning)
    }
  }

  const sellItem = async idItem => {
    setIsLoading(true)
    try {
      await wowApi.sellItem(idItem, globalIdCharacter.id)
      setWarning(SUCCESS_WARNINGS.API_SUCCESS_SELL)

      setIsLoading(false)
      setHasError(false)
    } catch (error) {
      setIsLoading(false)
      const newWarning = verifyReasonError(error.response.data)
      setWarning(newWarning)
    }
  }

  const handleItemAction = idItem => {
    switch (selectedAction) {
      case SELECTED_ACTIONS.SELECT_BUY:
        buyItem(idItem)
        break

      case SELECTED_ACTIONS.SELECT_SELL:
        sellItem(idItem)
        break

      default:
        break
    }

    setConfirmRequest('')
    setSelectedItem(null)
  }

  const handleWarningOkClick = () => {
    setWarning('')
  }

  return (
    <div className="store">
      <div className="store__container">
        <div className="store__content">
          <header className="store__header">
            <div className="store__character-money">
              <span>$ {character?.money}</span>
            </div>

            <StoreButtonMenu
              buttonTitle="Comprar"
              onClickAction={() => setSelectedAction(SELECTED_ACTIONS.SELECT_BUY)}
              selected={selectedAction === SELECTED_ACTIONS.SELECT_BUY}
            />
            <StoreButtonMenu
              buttonTitle="Vender"
              onClickAction={() => setSelectedAction(SELECTED_ACTIONS.SELECT_SELL)}
              selected={selectedAction === SELECTED_ACTIONS.SELECT_SELL}
            />

            <Link to="/world">
              <button className="store__button-goBack">Voltar</button>
            </Link>
          </header>

          <main className="store__main">
            {isLoading ? <Loader /> : null}
            {!hasError && !isLoading ? (
              <ItemsList
                itemsList={itemsShown}
                selectedAction={selectedAction}
                onClickAction={handleConfirmAction}
              />
            ) : null}
          </main>
        </div>
      </div>

      {confirmRequest !== '' ? (
        <StoreConfirmationBox
          title={`Confirmar ${confirmRequest}?`}
          onCancelClick={handleCancel}
          onConfirmClick={() => handleItemAction(selectedItem)}
        />
      ) : null}

      {warning !== '' ? <StoreWarningBox warning={warning} onClickOk={handleWarningOkClick} /> : null}

      {hasError || !itemsShown ? <Error /> : null}
    </div>
  )
}
