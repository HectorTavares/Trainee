import './tag-button.css'

export function TagButton({
  tag,
  tags,
  setTags,
  selectedTags,
  setSelectedTags,
  filteredTags,
  setFilteredTags,
}) {
  const disabled = selectedTags.length >= 5

  function handleTagClick() {
    if (selectedTags.length < 5) {
      setSelectedTags([...selectedTags, tag])
      const newTags = tags.filter(t => {
        return t.id !== tag.id
      })
      setTags(newTags)
      const newFilteredTags = filteredTags.filter(t => {
        return t.id !== tag.id
      })
      setFilteredTags(newFilteredTags)
    }
  }

  return (
    <button onClick={handleTagClick} className={`tag-button_main`} disabled={disabled}>
      {tag.nome}
    </button>
  )
}
