/*Exercício 1

Primeiro nome
Lista qual o primeiro nome mais popular entre os clientes, considere apenas o primeiro nome. 
Liste o primeiro nome e a quantidade de ocorrência deste nome.
*/

--1
SELECT nome,
count(*) 
FROM cliente 
GROUP BY nome HAVING count(*) > 2
order by count(*)
LIMIT 1



/*Exercício 2

Total do Mês
Liste o total de pedidos (quantidade e valor) realizados no mês de março/2018.
*/

--2
select SUM(valorpedido) as valor_total,
		count(*) as quantidade
		from pedido
		where Extract(year from datapedido) = 2018
		AND
		Extract(month from datapedido) = 3



/*Exercício 3

Estados x Clientes
Identifique qual o estado (coluna UF da tabela Cidade) possui o maior número de clientes (tabela Cliente),
liste também qual o Estado possui o menor número de clientes.
Dica: Utilizar UNION
*/

--3
SELECT UF,
		COUNT(1)
from cidade ci
INNER JOIN cliente cli on cli.idcidade = ci.idcidade
group by uf
order by count(1) desc;




/*exercício 4

Novo Produto
Crie (insira) um novo registro na tabela de Produto, com as seguintes informações:

Nome: Coturno Pica-Pau
Preço de custo: 29.25
Preço de venda: 77.95
Situação: A
*/

--4

select * from produto
order by idproduto desc;
INSERT INTO produto 
			(idproduto,nome,datacadastro,precocusto,precovenda,situacao)
			VALUES
			(8002,'Coturno Pica-Pau',CURRENT_DATE,29.25,77.95,'A')

/*Exercício 5

Produtos não comprados
Identifique e liste os produtos que não tiveram nenhum pedido.
=> Obs.: o produto criado anteriormente deverá ser listado.
*/

--5

SELECT * FROM produto p
where not exists(select idproduto from pedidoitem pi where pi.idproduto = p.idproduto)



/*Exercício 6

Principais Produtos
Liste os 30 produtos que mais geraram lucro em 2018.
*/

--6

select *,
(precovenda - precocusto) as lucro	
from produto
order by lucro desc
limit 30;

select * from pedidoitem

select pro.nome, 
sum(pro.precovenda-pro.precocusto) as lucro
from produto pro 
inner join pedidoitem i on i.idproduto = pro.idproduto 
inner join pedido e on i.idpedido = e.idpedido 
where Extract(year from datapedido) = 2018
group by pro.idproduto
order by lucro desc
limit 30;


