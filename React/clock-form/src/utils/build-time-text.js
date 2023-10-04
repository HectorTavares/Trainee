const leadZeroToTens = number => {
  const DESIRED_LENGTH = 2
  return number.toString().length < DESIRED_LENGTH ? `0${number}` : number
}

const buildTimeText = ({ hours, minutes, seconds }) => {
  return `${leadZeroToTens(hours)}:${leadZeroToTens(minutes)}:${leadZeroToTens(seconds)}`
}

export { buildTimeText }
