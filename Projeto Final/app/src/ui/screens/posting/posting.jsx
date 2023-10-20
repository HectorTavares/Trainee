import './posting.css'
import { Header, ImageCard, Loader, TagButton, Drag } from '../../components/index'
import { useEffect, useState } from 'react'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { useHistory } from 'react-router'
import { toast } from 'react-toastify'
import { variables, paths, errorMessage } from '../../../constants/index'

export function Posting() {
  const [title, setTitle] = useState('')

  const [tagList, setTags] = useState([])
  const [filteredTags, setFilteredTags] = useState([])
  const [selectedTags, setSelectedTags] = useState([])
  const [newTagName, setNewTagName] = useState('')

  const [content, setContent] = useState('')
  const [images, setImages] = useState([])

  const [pageLoader, setPageLoader] = useState(true)
  const [photoLoader, setPhotoLoader] = useState(false)
  const [postLoader, setPostLoader] = useState(false)
  const [tagLoader, setTagLoader] = useState(false)
  const [tagRefresh, setTagRefresh] = useState(false)

  const [userRole, setUserRole] = useState()
  const cwiRespostasApi = useCwiRespostasApi()
  const history = useHistory()

  const payload = {
    titulo: title,
    descricao: content,
    foto: JSON.stringify(images),
    tags: selectedTags,
  }

  useEffect(() => {
    async function getUser() {
      try {
        const userData = await cwiRespostasApi.user()
        userData.isMonitor ? setUserRole(variables.MONITOR) : setUserRole(variables.USER)
        setPageLoader(false)
      } catch (error) {
        toast.error(errorMessage.USER_ERROR)
      }
    }
    getUser()
  }, [])

  useEffect(() => {
    async function getTags() {
      try {
        const response = await cwiRespostasApi.tagList()
        const availableTags = response.filter(tag => !selectedTags.some(selected => selected.id === tag.id))
        setTags(availableTags)
        setFilteredTags(availableTags)
      } catch (error) {
        toast.error(errorMessage.TAG_LOAD_ERROR)
      }
    }
    getTags()
  }, [tagRefresh])

  function handleTitleChange(event) {
    setTitle(event.target.value)
    console.log(title)
  }
  function handleTagSearchChange(event) {
    const value = event.target.value
    const formatedValue = value.trim().toLowerCase()
    const result = tagList.filter(tag => tag.nome.toLowerCase().includes(formatedValue))
    setFilteredTags(result)
    setNewTagName(event.target.value)
  }
  function handleSelectedTagClick(tag) {
    const newTags = selectedTags.filter(t => {
      return t.id !== tag.id
    })
    setSelectedTags(newTags)
    tagList.push(tag)
    filteredTags.push(tag)
  }

  async function handleCreateTagClick() {
    setTagLoader(true)
    try {
      await cwiRespostasApi.createTag(newTagName)
      setTagRefresh(!tagRefresh)
      setTagLoader(false)
    } catch (error) {
      toast.error(errorMessage.NEW_TAG_ERROR)
      setTagLoader(false)
    }
  }
  function handleContentChange(event) {
    setContent(event.target.value)
    console.log(content)
  }

  async function uploadFile(file) {
    const payload = new FormData()
    payload.append('file', file)

    try {
      const response = await cwiRespostasApi.uploadPicture(payload)
      setPhotoLoader(false)
      setImages([...images, response])
    } catch (error) {
      toast.error(errorMessage.IMAGE_ERROR)
      setPhotoLoader(false)
    }
  }

  function setPostToast() {
    if (title.length < 10 || title.length > 100) {
      toast.error(errorMessage.TITLE_INFO)
    } else if (selectedTags.length === 0 || selectedTags.length > 5) {
      toast.error(errorMessage.TAG_INFO)
    } else if (content.length < 10 || content.length > 1000) {
      toast.error(errorMessage.CONTENT_INFO)
    } else {
      toast.error(errorMessage.POST_ERROR)
    }
  }

  async function handlePostClick() {
    setPostLoader(true)
    try {
      await cwiRespostasApi.createPost(payload)
      history.push(paths.HOME)
    } catch (error) {
      setPostToast()
      setPostLoader(false)
    }
  }

  return (
    <div className="posting_main">
      <Header />
      {pageLoader ? (
        <div className="posting_page-loader">
          <Loader size="big" />
        </div>
      ) : (
        <div className="posting_container">
          <div className="posting_card">
            <div className="posting_top-section">
              <div className="posting_title">
                <input
                  onChange={handleTitleChange}
                  type="text"
                  placeholder="Titulo"
                  className="posting_title-input"
                />
              </div>
              <div className="posting_tags">
                <div className="posting_tag-inputs">
                  <div className="posting_tag-search">
                    {tagLoader ? (
                      <Loader size="tiny" />
                    ) : (
                      <div>
                        <input
                          onChange={handleTagSearchChange}
                          className="posting_search-input"
                          type="text"
                          placeholder="Filtro de tags"
                        />
                        <button onClick={handleCreateTagClick} className={`posting_search-input-button`}>
                          Nova tag
                        </button>
                      </div>
                    )}
                  </div>
                </div>
                <div className="posting_tags">
                  <div className="posting_selected-tags">
                    {selectedTags.map(tag => (
                      <button
                        key={tag.id}
                        onClick={() => handleSelectedTagClick(tag)}
                        className={`posting_selected-tag-button`}
                      >
                        {tag.nome}
                      </button>
                    ))}

                    <div className="posting_tag-list">
                      {filteredTags.length > 0 &&
                        filteredTags
                          .sort((a, b) => a.nome.localeCompare(b.nome))
                          .map(tag => (
                            <TagButton
                              key={tag.id}
                              tag={tag}
                              tags={tagList}
                              setTags={setTags}
                              selectedTags={selectedTags}
                              setSelectedTags={setSelectedTags}
                              filteredTags={filteredTags}
                              setFilteredTags={setFilteredTags}
                            />
                          ))}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="posting_bottom-section">
              <div className="posting_content">
                <textarea
                  name=""
                  id=""
                  cols="30"
                  rows="10"
                  placeholder="Conteudo"
                  onChange={handleContentChange}
                  className="posting_content-input"
                />
              </div>
              <div className="posting_images">
                <div className="posting_images-container">
                  {images.length > 0 &&
                    images.map(image => (
                      <ImageCard key={image} image={image} images={images} setImages={setImages} />
                    ))}
                  {photoLoader && <Loader size="medium" />}
                </div>
              </div>

              <div className="posting_footer">
                <Drag
                  setPhotoLoader={setPhotoLoader}
                  uploadFile={uploadFile}
                  css1="posting_drag"
                  css2="posting_drag-message"
                  images={images}
                />

                {postLoader ? (
                  <Loader size="medium" />
                ) : (
                  <button onClick={handlePostClick} className={`posting_post-button ${userRole}`}>
                    Postar
                  </button>
                )}
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}
