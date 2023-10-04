const buildCurrentTimeObject = () => {
  const now = new Date()

  return {
    hours: now.getHours(),
    minutes: now.getMinutes(),
    seconds: now.getSeconds(),
    description: 'Hora de Brasília',
  }
}

export { buildCurrentTimeObject }
