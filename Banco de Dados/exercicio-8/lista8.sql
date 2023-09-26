/*Exercício 1

Datas
Faça uma consulta que liste todos os empregados admitidos no ano de 1981. Deve ser projetado nesta consulta:
ID, Nome e a quantidade de anos da admissão até o dia 01/06/2021.
OBS.: pesquisar por função de data (EXTRACT e AGE)
*/

--1

SELECT  idempregado,
		nomeempregado,
		AGE(dataadmissao,'2021-06-01') AS anos_De_Admissao
from empregado WHERE Extract (Year from dataadmissao) = 1981;


/*Exercício 2

Ranking
Qual o cargo (tabela empregado) possui mais empregados ? Deve ser projetado apenas um registro. 
** DICA: Pesquise recursos específicos para esta funcionalidade.
*/

--2
SELECT MAX (cargo), 
COUNT(1) as total
from empregado
group by cargo
order by total desc
limit 1;

/*Exercício 3

Contagem
Liste os estados (atributo UF) e o total de cidades existente em cada estado na tabela cidade. 
Ordene do maior para o menor.
*/

--3

SELECT MAX (UF), 
COUNT(1) as total
from cidade
group by UF
order by total desc



/*Exercício 4

Alterando dados
Crie um novo departamento, denominado 'Inovação' com localização em 'SAO LEOPOLDO'. 
Todos os empregados que foram admitidos no mês de dezembro (qualquer ano) que não ocupam o cargo de
'Atendente' devem ser ter o departamento (IDDepartamento) atualizado para este novo registro (inovação).
*/

--4

INSERT INTO departamento 
(iddepartamento,nomedepartamento,localizacao) 
VALUES
(13,'Inovação','SAO LEOPOLDO');

UPDATE empregado 
SET iddepartamento = 13
WHERE 
Extract (month from dataadmissao) = 12 AND cargo != 'Atendente';

select * from empregado;
