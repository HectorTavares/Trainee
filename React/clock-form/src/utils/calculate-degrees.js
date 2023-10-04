const getDegreesFromCircleFraction = fraction => {
  const TOTAL_DEGREES_IN_THE_CLOCK = 360
  return TOTAL_DEGREES_IN_THE_CLOCK * fraction
}

const getDegreesFromHours = hours => {
  const TOTAL_HOURS_IN_THE_CLOCK = 12
  return getDegreesFromCircleFraction(hours / TOTAL_HOURS_IN_THE_CLOCK)
}

const getDegreesFromMinutes = minutes => {
  const TOTAL_MINUTES_IN_THE_CLOCK = 60
  return getDegreesFromCircleFraction(minutes / TOTAL_MINUTES_IN_THE_CLOCK)
}

const getDegreesFromSeconds = seconds => {
  const TOTAL_SECONDS_IN_THE_CLOCK = 60
  return getDegreesFromCircleFraction(seconds / TOTAL_SECONDS_IN_THE_CLOCK)
}

export { getDegreesFromHours, getDegreesFromMinutes, getDegreesFromSeconds }
