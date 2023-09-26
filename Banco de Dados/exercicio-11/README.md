### Exercício 1
Buscar todas as licitações (tabela: LICITACAO) onde a data início da vigência seja maior ou igual 01/01/2018 e  a data fim da vigência menor ou igual a 30/06/2018. Mostrar no Select a data de fechamento no formato ’01-jan-2020’.
OBS: Valor arredondado sem cases decimais. 


### Exercício 2
Buscar das contratantes com ID 705 e 738 o valor médio mínimo e máximo. (tabela: LICITACAO)

### Exercício 3
Buscar os 6 contratantes que mais tiveram registros. (tabela: LICITACAO)

### Exercício 4
Buscar as propostas (tabela: PROPOSTA) onde a data da inscrição seja maior ou igual a 01/06/2018. Colocar a descrição da situação: S-> selecionada, D -> desqualificada, N -> não selecionada. 
Dica: estrutura de case.


### Exercício 5 
Buscar de todas as propostas o Id da proposta o valor inicial e final sem casas decimais e sem arredondamento (tabela: PROPOSTA). 


### Exercício 6
Buscar o maior valor final (Sem casas decimais e sem arredondar) das propostas de cada licitação que foram selecionadas. 
(tabela: PROPOSTA) Ordenado o maior valor para o menor. 


### Exercício 7
Buscar todas as propostas do ano de 2018, agrupar por situação mês e ano e a contagem de quantas teve. (tabela: PROPOSTA)
Ordernar por mês e situação


### Exercício 8
Buscar todos os campo onde a classe dos materiais é 13 e onde no nome contem “ALTURA 925 MM”. (tabela: MATERIAL)


### Exercício 9
Buscar no item da licitação (Tabela: ITEM_LICITACAO), mostrar o valor mínimo total e máximo total (quantidade X valor mínimo e máximo), ordenando por Id licitação


### Exercício 10
Buscar todos contratantes e sua cidade. Filtrar pelo Estado de Pernambuco e Cidade de Recife. Converter esfera M – Municipal , E - Estadual

 
### Exercício 11
Trazer o nome da empresa e nome da cidade, onde o final do CNPJ seja igual a 86. (Usar as tabelas lic_empresa e lic_cidade)


### Exercício 12
Buscar no item da licitação as licitações do ID 60, mostrar no select o nome do material (apenas os 20 primeiros caracteres), quantidade e o valor médio (entre valor mínimo e máximo) multiplicado pela quantidade. Ordene pela quantidade.


### Exercício 13
Buscar o título da licitação, início da vigência, quantidade, nome do material e nome da classe do material, onde o id contratante seja igual a 643 no mês de março de 2018 o fechamento.


### Exercício 14
Listar todas as propostas com o resultado da diferença entre o maior valor final e o menor e o número da licitação. Ordenar da maior diferença para a menor.


### Exercício 15
Listar os campos destacados abaixo de todas as propostas junto com os devidos relacionamentos.
* Id Proposta
* Data de inscrição da proposta
* Nome da Empresa
* Nome da Cidade da empresa
* Estado da Empresa
* CPF / CNPJ (Na mesma columa as duas informações qdo tem CNPJ apare o CNPJ e quando tem CPF aparece o CPF da empresa)
* Titulo da licitação
* Situação da licitação
* Quantidade de cada item da licitação
* Observação do item
* Nome do material do item


### Exercício 16
Selecionar todos os materiais que nunca foram incluídos numa licitação. Apenas a quantidade.