import { randomNumber } from "../../utils";

export function Line({ name, idHouse, reSort }) {
  function handleClick() {
    reSort(sortStudent());
  }

  function sortStudent() {
    let random = randomNumber();

    while (random === idHouse) {
      random = randomNumber();
    }

    const studentInformation = {
      name: name,
      oldHouseId: idHouse,
      newHouseId: random,
    };
    console.log(studentInformation.oldHouseId);
    return studentInformation;
  }
  return (
    <>
      <p className="lines" onClick={handleClick}>
        {name}
      </p>
    </>
  );
}
