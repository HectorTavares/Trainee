import './image-card.css'
import { useCwiRespostasApi } from '../../../api/Projetofinal-api/Projetofinal-api'
import { toast } from 'react-toastify'
import { errorMessage } from '../../../constants/index'

export function ImageCard({ image, images, setImages }) {
  const cwiRespostasApi = useCwiRespostasApi()

  async function handleDeleteClick() {
    const fileName = image.slice(52)
    try {
      await cwiRespostasApi.deletePicture(fileName)
      const newImages = images.filter(i => {
        return i !== image
      })
      setImages(newImages)
    } catch (error) {
      toast.error(errorMessage.DELETE_IMAGE_ERROR)
    }
  }

  return (
    <div className="image-card_main">
      <button onClick={handleDeleteClick} className="image-card_button">
        <img src={image} alt="Foto Anexada" className="image-card_image" />
      </button>
    </div>
  )
}
