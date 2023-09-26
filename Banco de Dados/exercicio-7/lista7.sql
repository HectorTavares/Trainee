/*Exercício 1

Datas
Exiba a data corrente no formato (01-oct-2018 15:16:01) colocando o nome da coluna como  “Data”
*/

--1

SELECT to_date(replace('01-oct-2018 15:16:01','oct','09'),'dd/mm/yyyy')
AS Data;

/*Exercício 2

Iniciais Nome
Exiba os 4 primeiros caracteres do nome do empregado da tabela (Empregado) em minúsculo.
*/

--2

SELECT lower(substring(nomeempregado,1,4))
from empregado;

/*Exercício 3

Concatenar nome e cargo
Ainda sobre a tabela de empregado concatenar nome e cargo,
fazer no  mesmo select de duas formas diferente, renomeando (alias) NomeCargo1 e NomeCargo2
*/

--3

SELECT CONCAT(nomeempregado,cargo) AS NomeCargo1,
		nomeempregado || cargo AS NomeCargo2
		FROM empregado;
		
/*Exercício 4

Data Admissão
Foi identificado que a data de admissão ao ser inserida no banco está sem a hora.
Exiba a data de admissão com data e hora, considerando que a admissão foram as 08
horas para todos os colaboradores.
Exibindo no formato (22/02/1981 08:00:00)
*/

--4

SELECT to_char(e.dataadmissao + '8 hours'::interval, 'DD/MM/YYYY HH24:MI:SS')
from empregado e;


