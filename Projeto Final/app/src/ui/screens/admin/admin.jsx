import './admin.css'
import { AdminCard, Header,Loader } from '../../components/index'
import { useEffect, useState } from 'react'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { useHistory } from 'react-router'
import { toast } from 'react-toastify'
import { paths, errorMessage } from '../../../constants/index'
import leftArrow from '../../../assets/image/leftArrow.png'
import rightArrow from '../../../assets/image/rightArrow.png'

export function Admin() {
  const [authorized, setAuthorized] = useState(false)
  const [searchInput, setSearchInput] = useState()
  const [searchPayload, setSearchPayload] = useState('@')
  const [loader, setLoader] = useState(false)
  const [users, setUsers] = useState()

  const [nextArrowBlock, setNextArrowBlock] = useState(false)
  const [arrowBlock, setArrowBlock] = useState(false)
  const [page, setPage] = useState(0)

  const cwiRespostasApi = useCwiRespostasApi()
  const history = useHistory()

  useEffect(() => {
    async function validateAdmin() {
      try {
        await cwiRespostasApi.validateAdmin()
        setAuthorized(true)
      } catch (error) {
        toast.error(errorMessage.UNAUTHORIZED)
        history.push(paths.DEFAULT)
      }
    }
    validateAdmin()
  }, [])

  useEffect(() => {
    setLoader(true)
    async function getSearch() {
      try {
        const response = await cwiRespostasApi.userSearch(searchPayload, page)
        page + 1 === response.totalPages ? setNextArrowBlock(true) : setNextArrowBlock(false)
        console.log(response)
        response.content.length === 0 && toast.info(errorMessage.EMPTY_SEARCH)
        setUsers(response.content)
        setArrowBlock(false)
        setLoader(false)
      } catch (error) {
        toast.error(errorMessage.SEARCH_ERROR)
        setLoader(false)
      }
    }
    getSearch()
  }, [searchPayload, page])

  function handleSearchChange(event) {
    setSearchInput(event.target.value)
  }

  function handleNextClick() {
    const nextPage = page + 1
    setPage(nextPage)
    setArrowBlock(true)
  }

  function handlePreviousClick() {
    const previousPage = page - 1
    setPage(previousPage)
  }

  function handleSearchClick() {
    setSearchPayload(searchInput)
    setPage(0)
  }
  console.log(users)
  return (
    <div className="admin_loader">
      {authorized ? (
        <div className="admin_main">
          <Header />
          <div className="admin_container">
            <div className="admin_list-section">
              <div className="admin_search-inputs">
                <input
                  type="text"
                  placeholder="Nome ou Email"
                  onChange={handleSearchChange}
                  className="admin_input"
                />
                <button onClick={handleSearchClick} className="admin_search-button">
                  Buscar
                </button>
              </div>
              <div className="admin_search-results">
                {loader ? (
                  <Loader size="medium" />
                ) : (
                  users && users.map(user => <AdminCard key={user.email} user={user} />)
                )}
              </div>
              <div className="admin_nav">
                <button onClick={handlePreviousClick} disabled={page === 0} className="admin_page-button">
                  <img src={leftArrow} alt="Página Anterior" className="admin_page-arrow" />
                </button>
                {page + 1}
                <button
                  onClick={handleNextClick}
                  disabled={nextArrowBlock || arrowBlock}
                  className="admin_page-button"
                >
                  <img src={rightArrow} alt="Próxima Página" className="admin_page-arrow" />
                </button>
              </div>
            </div>
          </div>
        </div>
      ) : (
        <Loader size="big" />
      )}
    </div>
  )
}
