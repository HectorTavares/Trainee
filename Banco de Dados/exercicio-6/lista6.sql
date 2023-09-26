/*Exercício 1
Listar id do pedido, id do cliente, data pedido, valor pedido e situação conforme condições abaixo:

Situação igual a “A” e cliente Id igual 11518
ou valor do pedido maior que 9 mil
Ordene pelo valor do pedido
*/
--1
SELECT idpedido,
		idcliente,
		datapedido,
		valorpedido,
		situacao
FROM pedido
WHERE situacao = 'A'AND idcliente = 11518
 OR valorpedido > 9000
ORDER BY valorpedido;


/*Exercício 2
Listar os materiais onde o peso seja maior que 4 e o preço menor que R$ 0,30 centavos.
*/

--2
SELECT *
FROM material
WHERE pesoliquido>4 
AND
precocusto < 0.30;


/*Exercício 3
Listar nome e uf das cidades (tabela CIDADE) do estado do Rio Grande do Sul,
ordenando por nome, 
sem mostrar nomes repetidos.
*/

--3
SELECT DISTINCT nome ,uf
FROM cidade
WHERE UF = 'RS'
ORDER BY nome;

/*Exercício 4
Listar os itens de pedido do pedido com valor de R$28299.76 e a situação seja “Q”.
Sem usar join (ainda não vimos). Ordenar por preço unitário
Dica: Usar dois selects
*/

--4
SELECT * 
FROM pedidoitem
WHERE idpedido = (
SELECT idpedido FROM pedido
WHERE valorpedido = 28299.76 AND situacao = 'Q');


/*Exercício 5
Listar clientes ativos sem CEP.
*/

--5
SELECT *
FROM cliente
WHERE situacao = 'A'
AND 
cep IS NULL;


/*Exercício 6
Calcular o valor total de todos produtos vendidos no pedido 168,
projetar o id do pedido, cálculo do valor e o id do produto.
Ordene pelo cálculo do valor de forma decrescente.
Dica: considere os campos quantidade e preço unitário, para o cálculo.
*/

--6
SELECT (quantidade*precounitario) as valor_total,
		idpedido,
		idproduto
FROM pedidoitem
WHERE idpedido = 168
ORDER BY valor_total;
		
		


/*Exercício 7
Listar os materiais com peso líquido entre 0.5 e 0.6.
*/

--7
SELECT *
FROM material
WHERE pesoliquido BETWEEN 0.5 AND 0.6
ORDER BY pesoliquido;



/*Exercício 8
Listar todos os pedidos que existem item cadastrado, situação do pedido seja igual a “A” 
e o valor do pedido maior que R$9500.
Dica: Usar exists
*/

--8
SELECT * 
FROM pedido p
WHERE EXISTS (SELECT idpedido FROM pedidoitem i WHERE i.idpedido = p.idpedido) 
AND
situacao = 'A'
AND
valorpedido > 9500
ORDER BY valorpedido;


/*Exercício 9
Vamos precisar provisionar o estoque para armazenar cinco vezes mais 
os materiais do produto com id igual 2492,
precisamos calcular as quantidades, considere o valor de 1, caso tenha valores nulos.
*/

--9
SELECT (COALESCE(quantidade,1)*5) AS quantidade_provisionada 
from produtomaterial
WHERE idproduto = 2492
ORDER BY idprodutomaterial;

/*Exercício 10
Listar os pedidos com situação igual a “A” ou “C” ou “I”, e o valor do pedido seja 
maior ou igual a R$9800,00
*/

--10
SELECT * 
FROM pedido
WHERE situacao IN ('A','C','I')
AND
valorpedido >= 9800.00;



