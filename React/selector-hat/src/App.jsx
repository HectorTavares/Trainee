import "./App.css";
import { Card } from "../src/components/index";
import STUDENTS from "./students.json";
import { useState } from "react";
import { randomNumber } from "./utils";

function App() {
  const [houses, setHouses] = useState({
    gryffindor: [],
    slytherin: [],
    hufflepuff: [],
    ravenclaw: [],
  });
  function handleClick() {
    sortStudents(STUDENTS);
  }

  function sortStudents(students) {
    const gryffindor = [];
    const slytherin = [];
    const hufflepuff = [];
    const ravenclaw = [];

    students.forEach((student) => {
      const random = randomNumber();

      if (random === 0) {
        gryffindor.push(student);
      }
      if (random === 1) {
        slytherin.push(student);
      }
      if (random === 2) {
        hufflepuff.push(student);
      }
      if (random === 3) {
        ravenclaw.push(student);
      }
    });

    const sortedStudents = {
      gryffindor: gryffindor,
      slytherin: slytherin,
      hufflepuff: hufflepuff,
      ravenclaw: ravenclaw,
    };

    setHouses(sortedStudents);
  }

  function reSortOneStudent(name, oldHouseId, newHouseId) {
    const gryffindor = [...houses.gryffindor];
    const slytherin = [...houses.slytherin];
    const hufflepuff = [...houses.hufflepuff];
    const ravenclaw = [...houses.ravenclaw];

    //ta dando undefined nas casas

    let index;
    switch (oldHouseId) {
      case 0:
        index = gryffindor.indexOf(name);
        gryffindor.splice(index, 1);
        break;
      case 1:
        index = slytherin.indexOf(name);
        slytherin.splice(index, 1);
        break;

      case 2:
        index = hufflepuff.indexOf(name);
        hufflepuff.splice(index, 1);
        break;

      case 3:
        index = ravenclaw.indexOf(name);
        ravenclaw.splice(index, 1);
        break;

      default:
        break;
    }

    switch (newHouseId) {
      case 0:
        gryffindor.unshift(name);
        break;
      case 1:
        slytherin.unshift(name);
        break;
      case 2:
        hufflepuff.unshift(name);
        break;
      case 3:
        ravenclaw.unshift(name);
        break;

      default:
        break;
    }

    const actualHouses = {
      gryffindor: gryffindor,
      slytherin: slytherin,
      hufflepuff: hufflepuff,
      ravenclaw: ravenclaw,
    };

    setHouses(actualHouses);
  }

  return (
    <div className="app">
      <header className="header">
        <h1>Sorting Hat</h1>
      </header>
      <div className="content">
        <Card
          key={1}
          id={1}
          houseName="GRYFFINDOR"
          list={houses.gryffindor}
          bgColor="red"
          reSort={reSortOneStudent}
        ></Card>
        <Card
          key={2}
          id={2}
          houseName="SLYTHERIN"
          list={houses.slytherin}
          bgColor="green"
          reSort={reSortOneStudent}
        ></Card>
        <Card
          key={3}
          id={3}
          houseName="HUFFLEPUFF"
          list={houses.hufflepuff}
          bgColor="yellow"
          reSort={reSortOneStudent}
        ></Card>
        <Card
          key={4}
          id={4}
          houseName="RAVENCLAW"
          list={houses.ravenclaw}
          bgColor="blue"
          reSort={reSortOneStudent}
        ></Card>
      </div>
      <div className="div-button">
        <button className="button" onClick={handleClick} />
      </div>
    </div>
  );
}

export default App;
