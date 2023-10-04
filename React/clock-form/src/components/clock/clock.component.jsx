import './clock.style.css'
import { buildTimeText, getDegreesFromHours, getDegreesFromMinutes, getDegreesFromSeconds } from '../../utils'

const Clock = ({ description, hours, minutes, seconds }) => {
  return (
    <div className="clock">
      <div className="clock-body">
        <div className="clock-body__circle clock-body__circle--outer">
          <div className="clock-body__circle clock-body__circle--inner">
            <div
              style={{ transform: `rotate(${getDegreesFromHours(hours)}deg)` }}
              className="clock-body__hand clock-body__hand--hours"
            />
            <div
              style={{ transform: `rotate(${getDegreesFromMinutes(minutes)}deg)` }}
              className="clock-body__hand clock-body__hand--minutes"
            />
            <div
              style={{ transform: `rotate(${getDegreesFromSeconds(seconds)}deg)` }}
              className="clock-body__hand clock-body__hand--seconds"
            />
            <div className="clock-body__center clock-body__center--big" />
            <div className="clock-body__center clock-body__center--small" />
          </div>
        </div>
      </div>
      <div className="clock-info">
        <h2 className="clock-info__name">{description}</h2>
        <h3 className="clock-info__time">{buildTimeText({ hours, minutes, seconds })}</h3>
      </div>
    </div>
  )
}

export { Clock }
