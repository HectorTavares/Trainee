import './reply-form.css'
import { Loader, ImageCard, Drag } from '../../../components/index'
import { useCwiRespostasApi } from '../../../../api/Projetofinal-api/Projetofinal-api'
import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import { errorMessage, variables } from '../../../../constants/index'

export function ReplyForm({ user, post, setRepliesRefresh, repliesRefresh }) {
  const [userRole, setUserRole] = useState()

  const [content, setContent] = useState('')
  const [images, setImages] = useState([])

  const [postLoader, setPostLoader] = useState(false)
  const [photoLoader, setPhotoLoader] = useState(false)
  const [cardRefresh, setCardRefresh] = useState(false)

  const cwiRespostasApi = useCwiRespostasApi()

  const payload = {
    idPergunta: post.id,
    descricao: content,
    foto: JSON.stringify(images),
  }

  useEffect(() => {
    user && user.isMonitor ? setUserRole(variables.MONITOR) : setUserRole(variables.USER)
  }, [user])

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

  function setReplyToast() {
    if (content.length < 10 || content.length > 1000) {
      toast.error(errorMessage.CONTENT_INFO)
    } else {
      toast.error(errorMessage.REPLY_ERROR)
    }
  }

  async function handleReplyClick() {
    setPostLoader(true)
    try {
      await cwiRespostasApi.createReply(payload)
      setPostLoader(false)
      setImages([])
      setContent('')
      setRepliesRefresh(!repliesRefresh)
      setCardRefresh(!cardRefresh)
    } catch (error) {
      setReplyToast()
      setPostLoader(false)
    }
  }

  return (
    <div className="reply-form_main">
      <div className="reply-form_top-section">
        <div className="post_reply-content">
          <textarea
            name=""
            id=""
            cols="30"
            rows="6"
            value={content}
            placeholder="Deixe uma Resposta"
            onChange={handleContentChange}
            className="reply-form_content-input"
          />
        </div>
        <div className="replyform_bottom-section">
          <div className="reply-form_buttons">
            <Drag
              setPhotoLoader={setPhotoLoader}
              uploadFile={uploadFile}
              css1="reply-form_drag"
              css2="reply_drag-message"
              images={images}
            />
            {images.length > 0 && (
              <div className="reply-form_images-container">
                {images.map(image => (
                  <ImageCard key={image} image={image} images={images} setImages={setImages} />
                ))}
                {photoLoader && <Loader size="medium" />}
              </div>
            )}
          </div>
          <button onClick={handleReplyClick} className={`reply-form_button ${userRole}`}>
            {postLoader || !user ? <Loader size="small" /> : 'Enviar Resposta'}
          </button>
          <div></div>
        </div>
      </div>
    </div>
  )
}
