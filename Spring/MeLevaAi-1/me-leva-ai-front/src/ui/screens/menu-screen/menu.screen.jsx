import { DriverScreen, HomeScreen, PassengerScreen } from '..'
import { useGlobalUser } from '../../../context'

export function MenuScreen() {
  const USER_TYPES = {
    driver: 'driver',
    passenger: 'passenger',
  }

  const [user] = useGlobalUser()

  switch (user.type) {
    case USER_TYPES.driver:
      return <DriverScreen />

    case USER_TYPES.passenger:
      return <PassengerScreen />

    default:
      return <HomeScreen />
  }
}
