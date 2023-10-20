export const Input = ({ onChange, ...props }) => {
  const handleChange = event => {
    onChange(event.target.value)
  }

  return <input onChange={handleChange} {...props} />
}
