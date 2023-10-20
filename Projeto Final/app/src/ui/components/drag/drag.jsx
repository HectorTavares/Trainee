import './drag.css'
import { toast } from 'react-toastify'
import { useDropzone } from 'react-dropzone'
import { useCallback } from 'react'
import { errorMessage, variables } from '../../../constants'

export function Drag({ setPhotoLoader, uploadFile, css1, css2, images }) {
  const onDrop = useCallback(
    acceptedFiles => {
      acceptedFiles.forEach(file => {
        const reader = new FileReader()
        reader.onabort = () => toast.error(errorMessage.IMAGE_ERROR)
        reader.onerror = () => toast.error(errorMessage.IMAGE_ERROR)
        reader.onload = () => {
          setPhotoLoader(true)
          if (variables.ACCEPTED_FILES.includes(acceptedFiles[0].name.split('.')[1])) {
            uploadFile(acceptedFiles[0])
          } else {
            toast.error(errorMessage.IMAGE_TYPE_INFO)
            setPhotoLoader(false)
          }
        }
        reader.readAsArrayBuffer(file)
      })
    },
    [images]
  )

  const { getRootProps, getInputProps, isDragActive } = useDropzone({
    onDrop,
  })

  return (
    <div {...getRootProps()}>
      <div className={css1}>
        <input {...getInputProps()} />
        {isDragActive ? (
          <p>Largue aqui a Imagem ...</p>
        ) : (
          <div className={css2}>
            <p>Clique ou Arraste uma Imagem aqui.</p>
            <p>Somente JPEG, JPG e PNG</p>
          </div>
        )}
      </div>
    </div>
  )
}
