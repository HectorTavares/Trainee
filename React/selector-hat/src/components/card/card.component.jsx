import "./card.css";
import { Line } from "../line/line.component";

export function Card({ houseName, list = [" "], bgColor, idHouse, reSort }) {
  function getStudentInformation({ name, oldHouseId, newHouseId }) {
    reSort(name, oldHouseId, newHouseId);
  }
  return (
    <section className="table">
      <div className={`table-header header-${bgColor}`}>
        <h2 className="title">{houseName}</h2>
      </div>
      <div className="table-lines">
        {list.map((name) => (
          <Line
            className="lines"
            name={name}
            key={name}
            id={idHouse}
            reSort={getStudentInformation}
          ></Line>
        ))}
      </div>
    </section>
  );
}
