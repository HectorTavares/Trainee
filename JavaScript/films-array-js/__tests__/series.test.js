import axios from 'axios'

import {
  verificarSeAtorEstaEmSeriado,
  filtarPorAnoERetornarNome,
  calcularMediaTotalDeEpisodios,
  agruparTituloDasSeriesPorPropriedade,
} from '../src/metodos'


let respostaApiData;

beforeAll(async () => {
  const respostaApi = await axios.get('https://hectortavares.github.io/Trainee/Conteudo/movies/movies.json')
  respostaApiData = respostaApi.data

})

describe('Filtrar', () => {
  it('Deve filtrar as series com ano de estreia maior ou igual a 2010 e retornar uma listagem com os nomes', () => {
    const resultadoObtido = filtarPorAnoERetornarNome(respostaApiData, 2010);
    const resultadoEsperado = [
      'Stranger Things',
      'Game Of Thrones',
      'The Walking Dead',
      'Band of Brothers',
      'Gus and Will The Masters of the Wizards',
      '10 Days Why',
      'Mr. Robot',
      'Narcos',
      'Westworld'
    ]

    expect(resultadoObtido).toEqual(resultadoEsperado)
  })
})

describe('Verificar', ()=>{
  it('Deve retornar true ao procurar ator que está em elenco',()=>{
    const resultadoObtido = verificarSeAtorEstaEmSeriado(respostaApiData,'RJ Mitte')

    expect(resultadoObtido).toBeTruthy()
  })

  it('Deve retornar false ao procurar ator que não participa de elenco',()=>{
    const resultadoObtido = verificarSeAtorEstaEmSeriado(respostaApiData,'Héctor')

    expect(!resultadoObtido).toBeTruthy()
  })
})

describe('Calcular',()=>{
  it('Deve calcular corretamente a media total de episódios de todas as series',()=>{
    const resultadoObtido  = calcularMediaTotalDeEpisodios(respostaApiData)
    const resultadoEsperado = 35.8

    expect(resultadoObtido).toBe(resultadoEsperado)
  })
})

describe('Agrupar',()=>{
  it('Deve agrupar corretamente em um objeto os titulos das series baseado na Distribuidora',()=>{

    const resultadoObtido = agruparTituloDasSeriesPorPropriedade(respostaApiData,'distribuidora')
    const resultadoEsperado =  {
      Netflix: [
        {
          titulo: 'Stranger Things',
          anoEstreia: 2016,
          diretor: [
            "Matt Duffer",
            "Ross Duffer"
          ],
          genero: [
            "Suspense",
            "Ficcao Cientifica",
            "Drama"
          ],
          elenco: [
            "Winona Ryder",
            "David Harbour",
            "Finn Wolfhard",
            "Millie Bobby Brown",
            "Gaten Matarazzo",
            "Caleb McLaughlin",
            "Natalia Dyer",
            "Charlie Heaton",
            "Cara Buono",
            "Matthew Modine",
            "Noah Schnapp"
          ],
          temporadas: 3,
          numeroEpisodios: 25,
          distribuidora: 'Netflix'
        },
        {
          titulo: 'Narcos',
          anoEstreia: 2015,
          diretor: [
            "Paul Eckstein",
            "Mariano Carranco",
            "Tim King",
            "Lorenzo O Brien"
          ],
          genero: [
            "Documentario",
            "Crime",
            "Drama"
          ],
          elenco: [
            "Wagner Moura",
            "Boyd   Holbrook",
            "Pedro Pascal",
            "Joann Christie",
            "Mauricie Compte",
            "André Mattos",
            "Roberto Urbina",
            "Diego Cataño",
            "Jorge A. Jiménez",
            "Paulina Gaitán",
            "Paulina Garcia"
          ],
          temporadas: 2,
          numeroEpisodios: 20,
          distribuidora: 'Netflix'
        }
      ],
      HBO: [
        {
          titulo: 'Game Of Thrones',
          anoEstreia: 2011,
          diretor: [
            "David Benioff",
            "D. B. Weiss",
            "Carolyn Strauss",
            "Frank Doelger",
            "Bernadette Caulfield",
            "George R. R. Martin"
          ],
          genero: [
            "Fantasia",
            "Drama"
          ],
          elenco: [
            "Peter Dinklage",
            "Nikolaj Coster-Waldau",
            "Lena Headey",
            "Emilia Clarke",
            "Kit Harington",
            "Aidan Gillen",
            "Iain Glen  ",
            "Sophie Turner",
            "Maisie Williams",
            "Alfie Allen",
            "Isaac Hempstead Wright"
          ],
          temporadas: 8,
          numeroEpisodios: 60,
          distribuidora: 'HBO'
        },
        {
          titulo: 'Band of Brothers',
          anoEstreia: 20001,
          diretor: [
            "Steven Spielberg",
            "Tom Hanks",
            "Preston Smith",
            "Erik Jendresen",
            "Stephen E. Ambrose"
          ],
          genero: [
            "Guerra"
          ],
          elenco: [
            "Damian Lewis",
            "Donnie Wahlberg",
            "Ron Livingston",
            "Matthew Settle",
            "Neal McDonough"
          ],
          temporadas: 1,
          numeroEpisodios: 10,
          distribuidora: 'HBO'
        },
        {
          titulo: 'Westworld',
          anoEstreia: 2016,
          diretor: [
            "Athena Wickham"
          ],
          genero: [
            "Ficcao Cientifica",
            "Drama",
            "Thriller",
            "Acao",
            "Aventura",
            "Faroeste"
          ],
          elenco: [
            "Evan N. Rachel Wood",
            "Thandie U. Newton",
            "Jeffrey N. Wright",
            "James E. Marsden",
            "Ben S. Barnes",
            "Ingrid I. Bolso Berdal",
            "Clifton L. Collins Jr.",
            "Luke L. Hemsworth",
            "Sidse U. Babett Knudsen",
            "Simon M. Quarterman",
            "Rodrigo I. Santoro",
            "Tessa N. Thompson",
            "Shannon A. Woodward",
            "Ed T. Harris",
            "Anthony I. Hopkins"
          ],
          temporadas: 1,
          numeroEpisodios: 10,
          distribuidora: 'HBO'
        }
      ],
      AMC: [
        {
          titulo: 'The Walking Dead',
          anoEstreia: 2010,
          diretor: [
            "Jolly Dale",
            "Caleb Womble",
            "Paul Gadd",
            "Heather Bellson"
          ],
          genero: [
            "Terror",
            "Suspense",
            "Apocalipse Zumbi"
          ],
          elenco: [
            "Andrew Lincoln",
            "Jon Bernthal",
            "Sarah Wayne Callies",
            "Laurie Holden",
            "Jeffrey DeMunn",
            "Steven Yeun",
            "Chandler Riggs ",
            "Norman Reedus",
            "Lauren Cohan",
            "Danai Gurira",
            "Michael Rooker ",
            "David Morrissey"
          ],
          temporadas: 7,
          numeroEpisodios: 99,
          distribuidora: 'AMC'
        },
        {
          titulo: 'Breaking Bad',
          anoEstreia: 2008,
          diretor: [
            "Vince Gilligan",
            "Michelle MacLaren",
            "Adam Bernstein",
            "Colin Bucksey",
            "Michael Slovis",
            "Peter Gould"
          ],
          genero: [
            "Acao",
            "Suspense",
            "Drama",
            "Crime",
            "Humor Negro"
          ],
          elenco: [
            "Bryan Cranston",
            "Anna Gunn",
            "Aaron Paul",
            "Dean Norris",
            "Betsy Brandt",
            "RJ Mitte"
          ],
          temporadas: 5,
          numeroEpisodios: 62,
          distribuidora: 'AMC'
        }
      ],
      CWI: [
        {
          titulo: 'Gus and Will The Masters of the Wizards',
          anoEstreia: 2021,
          diretor: [
            "James Bajczuk",
            "Marcio Tesser",
            "Andre Nunes"
          ],
          genero: [
            "Terror",
            "Caos",
            "JavaScript"
          ],
          elenco: [
            "Alunos E-CRESCER"
          ],
          temporadas: 1,
          numeroEpisodios: 40,
          distribuidora: 'CWI'
        }
      ],
      JS: [
        {
          titulo: '10 Days Why',
          anoEstreia: 2010,
          diretor: [
            "Brendan Eich"
          ],
          genero: [
            "Caos",
            "JavaScript"
          ],
          elenco: [
            "Brendan Eich",
            "Gustavo Rodrigues",
            "William Cardozo"
          ],
          temporadas: 10,
          numeroEpisodios: 10,
          distribuidora: 'JS'
        }
      ],
      'USA Network': [
        {
          titulo: 'Mr. Robot',
          anoEstreia: 2018,
          diretor: [
            "Sam Esmail"
          ],
          genero: [
            "Drama",
            "Techno Thriller",
            "Psychological Thriller"
          ],
          elenco: [
            "Rami Malek",
            "Carly Chaikin",
            "Portia Doubleday",
            "Martin Wallström",
            "Christian Slater"
          ],
          temporadas: 2,
          numeroEpisodios: 22,
          distribuidora: 'USA Network'
        }
      ]
    }

    expect(resultadoObtido).toEqual(resultadoEsperado)

  })
})
