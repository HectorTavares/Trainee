import './image.css'
import { Box, Button, Modal } from '@mui/material'
import { useState } from 'react'

export function Image({ image, cssType }) {
  const [open, setOpen] = useState(false)
  const handleOpen = () => setOpen(true)
  const handleClose = () => setOpen(false)

  return (
    <div>
      <Button onClick={handleOpen}>
        <img key={image} src={image} alt="Imagem listada" className={cssType} />
      </Button>
      <Modal open={open} onClose={handleClose}>
        <Box className="modal">
          <img key={image} src={image} alt="Zoom te tela na Imagem" className="modal_image" />
        </Box>
      </Modal>
    </div>
  )
}
