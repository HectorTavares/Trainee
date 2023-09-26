# Lista de Exercícios 06

## Exercício 1
Listar id do pedido, id do cliente, data pedido, valor pedido e situação conforme condições abaixo:
- Situação igual a “A” e cliente Id igual 11518
- ou valor do pedido maior que 9 mil
Ordene pelo valor do pedido


## Exercício 2
Listar os materiais onde o peso seja maior que 4 e o preço menor que R$ 0,30 centavos.


## Exercício 3
Listar nome e uf das cidades (tabela CIDADE) do estado do Rio Grande do Sul, ordenando por nome, sem mostrar nomes repetidos.


## Exercício 4
Listar os itens de pedido do pedido com valor de R$28299.76 e a situação seja “Q”.
Sem usar join (ainda não vimos). Ordenar por preço unitário 
Dica: Usar dois selects


## Exercício 5
Listar clientes ativos sem CEP.


## Exercício 6
Calcular o valor total de todos produtos vendidos no pedido 168, projetar o id do pedido, cálculo do valor e o id do produto. Ordene pelo cálculo do valor de forma decrescente.
Dica: considere os campos quantidade e preço unitário, para o cálculo. 


## Exercício 7
Listar os materiais com peso líquido entre 0.5 e 0.6.


## Exercício 8
Listar todos os pedidos que existem item cadastrado, situação do pedido seja igual a “A” e o valor do pedido maior que R$9500.
Dica: Usar exists


## Exercício 9
Vamos precisar provisionar o estoque para armazenar cinco vezes mais os materiais do produto com id igual 2492, precisamos calcular as quantidades, considere o valor de 1, caso tenha valores nulos.


## Exercício 10
Listar os pedidos com situação igual a “A” ou “C” ou “I”, e o valor do pedido seja maior ou igual a R$9800,00
