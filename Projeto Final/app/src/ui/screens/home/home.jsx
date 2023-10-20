import './home.css'
import { useEffect, useState } from 'react'
import { Header, PostCard, Loader, RankingCard } from '../../components'
import { useHistory } from 'react-router'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { toast } from 'react-toastify'
import { paths, errorMessage } from '../../../constants/index'
import leftArrow from '../../../assets/image/leftArrow.png'
import rightArrow from '../../../assets/image/rightArrow.png'

export function Home() {
  const [ranking, setRanking] = useState([])
  const [rankingLoad, setRankingLoad] = useState(false)

  const [posts, setPosts] = useState([])
  const [postsLoader, setPostsLoader] = useState(false)

  const [searchSwitch, setSearchSwitch] = useState(false)
  const [inSearch, setInSearch] = useState(false)
  const [textSearch, setTextSearch] = useState('')
  const [tags, setTags] = useState([])
  const [tagSearch, setTagSearch] = useState()
  const [selectedTags, setSelectedTags] = useState([])

  const [arrowBlock, setArrowBlock] = useState(false)
  const [nextArrowBlock, setNextArrowBlock] = useState(false)
  const [page, setPage] = useState(0)

  const history = useHistory()
  const cwiRespostasApi = useCwiRespostasApi(page)

  const payload = {
    busca: textSearch,
    tags: selectedTags.map(tag => tag.id),
  }

  useEffect(() => {
    async function getPosts() {
      setPostsLoader(true)
      try {
        const response = await cwiRespostasApi.listAllPosts(page)
        page + 1 === response.totalPages ? setNextArrowBlock(true) : setNextArrowBlock(false)
        setPosts(response.content)
        setPostsLoader(false)
        setArrowBlock(false)
      } catch (error) {
        toast.error(errorMessage.POST_LOAD_ERROR)
        setPostsLoader(false)
      }
    }
    async function searchPosts() {
      setPostsLoader(true)
      try {
        const response = await cwiRespostasApi.searchPosts(page, payload)
        page + 1 === response.totalPages ? setNextArrowBlock(true) : setNextArrowBlock(false)
        response.content.length === 0 && toast.info(errorMessage.EMPTY_SEARCH)
        setPosts(response.content)
        setPostsLoader(false)
        setArrowBlock(false)
      } catch (error) {
        toast.error(errorMessage.SEARCH_ERROR)
        setPostsLoader(false)
      }
    }
    inSearch ? searchPosts() : getPosts()
  }, [page, searchSwitch])

  useEffect(() => {
    async function getRanking() {
      try {
        const response = await cwiRespostasApi.ranking()
        setRanking(response.content)
        setRankingLoad(true)
      } catch (error) {
        toast.error(errorMessage.RANKING_LOAD_ERROR)
      }
    }
    getRanking()
  }, [])

  useEffect(() => {
    async function getTags() {
      try {
        const response = await cwiRespostasApi.tagList()
        setTags(response)
      } catch (error) {
        toast.error(errorMessage.TAG_LOAD_ERROR)
      }
    }
    getTags()
  }, [])

  function handleTextSearchChange(event) {
    setTextSearch(event.target.value.toLowerCase())
  }
  function handleTagSearchChange(event) {
    setTagSearch(event.target.value.toLowerCase())
  }
  function handleTagSelectClick(selectedTag) {
    setSelectedTags([...selectedTags, selectedTag])
    setTags(tags.filter(tag => tag.id !== selectedTag.id))
  }
  function handleRemoveTagClick(removedTag) {
    setTags([...tags, removedTag])
    setSelectedTags(selectedTags.filter(tag => tag.id !== removedTag.id))
  }
  function handleSearchClick() {
    setInSearch(true)
    setSearchSwitch(!searchSwitch)
    setPage(0)
  }
  function handleCreatePostClick() {
    history.push(paths.POSTING)
  }
  function handleRighArrowClick() {
    setArrowBlock(true)
    setPage(page + 1)
  }
  function handleLeftArrowClick() {
    setArrowBlock(true)
    setPage(page - 1)
  }

  return (
    <div className="home_main">
      <Header />
      <div className="home_container">
        <div className="home_top-section">
          <div className="home_top-container">
            <div className="home_title"></div>

            <div className="home_inputs-section">
              <input
                onChange={handleTagSearchChange}
                className="home_tag-search home_input"
                type="text"
                placeholder="Busca por Tags"
              />
              <input
                onChange={handleTextSearchChange}
                className="home_post-search home_input"
                type="text"
                placeholder="Busca por Texto"
              />
              <button onClick={handleSearchClick} className="home_search-button home_nav-buttons">
                Buscar
              </button>
              <button onClick={handleCreatePostClick} className="home_create-post-button home_nav-buttons">
                Crie uma Pergunta
              </button>
            </div>
            <div className="home_tag-section">
              {selectedTags.length > 0 && (
                <div className="home_selected-tags">
                  {selectedTags
                    .sort((a, b) => a.nome.localeCompare(b.nome))
                    .map(tag => (
                      <button
                        key={tag.id}
                        onClick={() => handleRemoveTagClick(tag)}
                        className="home_tag-button selected"
                      >
                        {tag.nome}
                      </button>
                    ))}
                </div>
              )}
              {tagSearch && tags && (
                <div className="home_tag-selection-section">
                  {tags
                    .filter(tag => tag.nome.toLowerCase().includes(tagSearch))
                    .sort((a, b) => a.nome.localeCompare(b.nome))
                    .map(tag => (
                      <button
                        key={tag.id}
                        disabled={selectedTags.length === 5}
                        onClick={() => handleTagSelectClick(tag)}
                        className="home_tag-button"
                      >
                        {tag.nome}
                      </button>
                    ))}
                </div>
              )}
            </div>
          </div>
        </div>
        <div className="home_bottom-section">
          <div className="home_left-section">
            {postsLoader ? (
              <div className="home_post-loader">
                <Loader size="big" />
              </div>
            ) : (
              <div className="home_post-section">
                {posts.length > 0 && posts.map(post => <PostCard key={post.id} post={post} />)}
                {posts.length === 0 && <div className="home_empty-list">Não há posts</div>}
              </div>
            )}

            <div className="home_page-nav">
              <button
                onClick={handleLeftArrowClick}
                disabled={page === 0 || arrowBlock}
                className="home_page-nav-buttons"
              >
                <img src={leftArrow} alt="Seta para a esquerda" className="home_page-nav-arrow" />
              </button>
              {page + 1}
              <button
                disabled={nextArrowBlock || arrowBlock}
                onClick={handleRighArrowClick}
                className="home_page-nav-buttons"
              >
                <img src={rightArrow} alt="Seta para a direita" className="home_page-nav-arrow" />
              </button>
            </div>
          </div>
          <div className="home_right-section">
            <div className="home_right-section-title">Participantes mais Ativos</div>
            {rankingLoad ? (
              ranking.map(user => <RankingCard key={user.email} user={user} />)
            ) : (
              <div className="home_ranking-loader">
                <Loader size="big" />
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  )
}
