--1
SELECT e.nomeempregado as NomeEmpregado,
		g.nomeempregado as NomeGerente,
		nomedepartamento 
	from empregado e 
	inner join empregado g on e.idgerente = g.idempregado 
	inner join departamento d on e.iddepartamento = d.iddepartamento
	order by nomedepartamento
	
--2	
SELECT e.iddepartamento,
		d.nomedepartamento
		from empregado e
		inner join departamento d ON e.iddepartamento = d.iddepartamento
		order by salario ASC
		limit 1
		
		SELECT * FROM EMPREGADO order by salario
		select * from departamento
		
--3
/*Exercício 3

Conflito
Adicione uma Unique Constraint ao atributo nome departamento de departamento.
Em seguida adicione um novo departamento de vendas e faça com que o anterior mude de novo. OBS: em apenas um comando.
Dica: pesquisar por on conflict
*/

alter table departamento add unique(nomedepartamento)
;

INSERT INTO departamento(iddepartamento,nomedepartamento) 
VALUES(212321,'Vendas')
ON CONFLICT (nomedepartamento) DO 
   UPDATE SET nomedepartamento = 'Venda';
  
  
  --4
  /*Exercício 4

Cidades duplicadas
Liste todas as cidades duplicadas (Tabela: CidadeEX) (nome e UF iguais e quantas ocorrência tem na tabela.
  */
  
  SELECT nome,
  		uf,
Count(*) 
FROM cidadeex
GROUP BY nome,uf 
HAVING Count(*) > 1

--5

create view vwcidadesduplicadas as
SELECT nome,
  		uf,
Count(*) 
FROM cidadeex
GROUP BY nome,uf 
HAVING Count(*) > 1

update cidadeex set nome = CONCAT(nome,'*')
where nome in(select nome from vwcidadesduplicadas) 
and uf in(select uf from vwcidadesduplicadas);

