# Lista de Exercícios 10

## Exercício 1
### Múltiplos acessos
Liste o nome do empregado, o nome do gerente e o departamento de cada um.


## Exercício 2
### Maior Salário
Liste o departamento (id e nome) com o empregado de maior salário.


## Exercício 3
### Conflito
Adicione uma Unique Constraint ao atributo nome departamento de departamento.
Em seguida adicione um novo departamento de vendas e faça com que o anterior mude de novo. OBS: em apenas um comando.
Dica: pesquisar por on conflict

## Exercício 4
### Cidades duplicadas
Liste todas as cidades duplicadas (Tabela: CidadeEX) (nome e UF iguais e quantas ocorrência tem na tabela.


## Exercício 5
### Definindo Cidades
Faça uma alteração nas cidades (Tabela: CidadeEX) que tenham nome+UF duplicados, adicione no final do nome um asterisco. Mas atenção! Apenas a cidade com maior ID deve ser alterada.

### Explicação adicional - VIEW
Para reaproveitar uma consulta SQL um dos recursos oferecidos é a criação de VIEWS. Neste recurso o comando SQL é salvo no dicionário de dados do SGBD e pode ser reutilizado novamente.
Um exemplo disso seria uma consulta que retorna apenas as cidades do RS.

~~~sql
Create view vwCidades_Gauchas as
   Select IDCidade,
          Nome
   From   Cidade
   Where  UF = 'RS';
~~~

Para utilizar esta consulta ela deve ser referenciada no FROM como se fosse uma fonte de dados qualquer:

~~~sql
Select IDCidade, 
       Nome 
  From vwCidades_Gauchas;
~~~