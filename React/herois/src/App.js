import "./App.css";
import { herois } from "./herois";
import { Card } from "./components";

function App() {
  const listCards = herois.map((heroi) => (
    <Card
      background={heroi.background}
      color={heroi.color}
      description={heroi.description}
      image={heroi.image}
      name={heroi.name}
    ></Card>
  ));
  return (
    <div className="background">
      <header className="header">
        <h1>Seleção de Heróis</h1>
      </header>
      <div className="container">{listCards}</div>
    </div>
  );
}

export default App;
