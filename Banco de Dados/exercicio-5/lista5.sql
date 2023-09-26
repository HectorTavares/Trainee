--Exercício 1
--Ordenação
--Faça uma consulta que retorne os empregados em ordem de admissão, projetar apenas ID e Nome.

--1
SELECT idempregado, nomeempregado FROM empregado
ORDER BY dataadmissao;

--Exercício 2
--Filtros
--Faça uma consulta que liste todos os empregados que não recebam comissão,
--considerando apenas os de comissão nula, deve ser ordenado pelo salário. 
--Projetar nome e salário.

--2
SELECT nomeempregado,salario FROM empregado
WHERE comissao is null
ORDER BY salario;

/*Exercício 3
Cálculo
Faça uma consulta que retorno o nome dos empregados, o salário anual 
(considere 13 salários), projete também a comissão (considere 12x). 
As colunas que deverão ser exibidas são:
Ordenar por Nome

ID do Empregado
Nome
Salario Anual
Comissão Anual
Renda Anual
*/

--3

SELECT idempregado AS ID_do_Empregado,
nomeempregado AS Nome,
(COALESCE(salario,0)*13) AS Salario_Anual,
(COALESCE(comissao,0)*12) AS Comissão_Anual,
((COALESCE(salario,0)*13) + (COALESCE(comissao,0)*12)) AS Renda_Anual
FROM empregado
ORDER BY Nome;

/*Exercício 4

Cálculo e Filtros
Faça uma consulta que liste todos os empregados que tenham o salário anual seja inferior a R$ 18500 (considere 13 salários) ou que tenham o cargo de Atendente. Projetar as seguintes colunas:
Ordenar por Nome

ID do Empregado
Nome
Cargo
Salário Anual
*/

--4
SELECT idempregado AS ID_do_Empregado,
nomeempregado AS Nome,
cargo,
(COALESCE(salario,0)*13) AS Salario_Anual
FROM empregado
WHERE (salario*13) < 18500
ORDER BY Nome;

/*Exercício 5

Busca Empregado
Faça uma consulta que liste os empregados com cargo de “Atendente” 
ou o Id do gerente seja 7698. Projetar as seguintes colunas:

Nome
Cargo
Ordenar por Nome

*/

--5
SELECT nomeempregado AS Nome,
		cargo 
FROM empregado
WHERE cargo = 'Atendente'
OR
idgerente= 7698
ORDER BY Nome;

/*Exercício 6

Busca Departamento
Faça uma consulta que liste os departamentos que o nome da localização comece com “SAO”.
Projetar as seguintes colunas:

ID do Departamento
Nome Departamento
Ordenar por Nome departamento

*/

--6
SELECT iddepartamento AS ID_do_Departamento,
nomedepartamento AS Nome_Departamento
FROM departamento
WHERE localizacao LIKE 'SAO%'
ORDER BY nomedepartamento;

/*Exercício 7

Busca Cidade
Faça uma consulta que liste todas as Cidades com ID entre 4 e 9. Projetar todas as colunas:
Ordenar por Id. (Considerar tabela CidadeEx)
*/

--7
SELECT * FROM cidadeEx
WHERE idcidade BETWEEN 4 AND 9;


/*Exercício 8

Departamentos sem empregados
Faça uma consulta que liste todos os departamentos que não tem nenhum empregado vinculado naquele departamento.
Projetar todas as colunas:
Ordenar por Id
*/

--8
SELECT * FROM departamento d
WHERE NOT EXISTS (SELECT 1 FROM empregado e WHERE d.iddepartamento = e.iddepartamento)
ORDER BY iddepartamento;

/*Exercício 9

Departamentos nulo
Faça uma consulta que liste todos Empregados que estão com o Id do departamento nulo. Projetar as colunas:

Nome Empregado
Cargo
Ordenar por Nome Empregado
*/

--9

SELECT nomeempregado AS Nome_Empregado,
		cargo
FROM empregado
WHERE iddepartamento IS NULL
ORDER BY nomeempregado;

/*Exercício 10

Empregados dos gerentes
Faça uma consulta que liste todos Empregados que tem como Id Gerente (7566, 7698, 7782). Projetar as colunas:

Nome Empregado
Ordenar por Nome Empregado

*/

--10

SELECT nomeempregado AS Nome_Empregado 
FROM empregado
WHERE idgerente in(7566,7698,7782)
ORDER BY nomeempregado;






