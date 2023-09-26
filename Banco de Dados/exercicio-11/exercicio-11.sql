/*Exercício 1
Buscar todas as licitações (tabela: LICITACAO) onde a data início da vigência seja maior ou
igual 01/01/2018 e  a data fim da vigência menor ou igual a 30/06/2018. Mostrar no Select a data de 
fechamento no formato ’01-jan-2020’.
OBS: Valor arredondado sem cases decimais.
*/

--1

select 
TO_CHAR(data_fechamento, 'DD/MON/YYYY') as data_fechamento
from lic_licitacao
where inicio_vigencia >= '2018-01-01'
and fim_vigencia <= '2018-06-30'

/*Exercício 2
Buscar das contratantes com ID 705 e 738 o valor médio mínimo e máximo. (tabela: LICITACAO)
*/


--2
select avg(valor_estimado_min) as minimo,
		avg(valor_estimado_max) as maximo
		from lic_licitacao
		where idcontratante in(705,738)
		group by idcontratante
		
/*Exercício 3
Buscar os 6 contratantes que mais tiveram registros. (tabela: LICITACAO)
*/

--3

select c.nome,c.idcidade,c.esfera, 
count(*) as Registros
from lic_contratante c 
inner join lic_licitacao l on l.idcontratante = c.idcontratante
group by c.idcontratante
having count(*) > 2
order by count(*) desc
limit 6;

/*Exercício 4
Buscar as propostas (tabela: PROPOSTA) onde a data da inscrição seja maior ou igual a 01/06/2018. 
Colocar a descrição da situação: S-> selecionada, D -> desqualificada, N -> não selecionada.
Dica: estrutura de case.
*/

--4

select idproposta,
		idlicitacao,
		idempresa,
		data_inscricao,
		valor_inicial,
		valor_final,
		case when situacao = 'S' then 'selecionada'
			when situacao = 'D' then 'desqualificada'
			when situacao = 'N' then 'não selecionada'
			end
		from lic_proposta 
where data_inscricao >= '2018-06-01'


/*Exercício 5
Buscar de todas as propostas o Id da proposta o valor inicial e final sem casas decimais 
e sem arredondamento (tabela: PROPOSTA).
*/

--5

select idproposta,
		trunc(valor_inicial) as valor_inicial,
		trunc(valor_final) as valor_final
from lic_proposta


/*Exercício 6
Buscar o maior valor final (Sem casas decimais e sem arredondar) das propostas de cada licitação que foram selecionadas.
(tabela: PROPOSTA) Ordenado o maior valor para o menor.
*/

--6

select max(trunc(valor_final)) as valor_final,
from lic_proposta
where situacao = 'S'
group by idlicitacao
order by valor_final desc

/*Exercício 7
Buscar todas as propostas do ano de 2018, agrupar por situação mês e ano e a contagem de quantas teve. (tabela: PROPOSTA)
Ordernar por mês e situação
*/

--7

select idproposta,
		idlicitacao,
		idempresa,
		data_inscricao,
		valor_inicial,
		valor_final, 
		count(*) 
from lic_proposta 
where extract(year from data_inscricao) = 2018
group by situacao,extract(month from data_inscricao),extract(year from data_inscricao)
order by extract(month from data_inscricao),situacao

/*Exercício 8
Buscar todos os campo onde a classe dos materiais é 13 e onde no nome contem “ALTURA 925 MM”. (tabela: MATERIAL)
*/

--8

select * from lic_material
where idclasse_material = 13 and nome like '%ALTURA 925 MM%'

/*Exercício 9
Buscar no item da licitação (Tabela: ITEM_LICITACAO), mostrar o valor mínimo total e máximo total 
(quantidade X valor mínimo e máximo), ordenando por Id licitação
*/

--9

select (valor_minimo * quantidade ) as valor_minimo_total,
		(valor_maximo * quantidade) as valor_maximo_total 
		from lic_item_licitacao
		order by idlicitacao;

/*Exercício 10
Buscar todos contratantes e sua cidade. Filtrar pelo Estado de Pernambuco e Cidade de Recife.
Converter esfera M – Municipal , E - Estadual
*/

--10

select c.idcontratante,
c.nome,
case when esfera = 'M' then 'Municipal'
		when esfera = 'E' then 'Estadual'
		end,
ci.* 
from lic_contratante c
inner join lic_cidade ci ON c.idcidade =  ci.idcidade
where ci.uf = 'PE' AND ci.nome = 'Recife'



/*Exercício 11
Trazer o nome da empresa e nome da cidade, onde o final do CNPJ seja igual a 86. (Usar as tabelas lic_empresa e lic_cidade)
*/

--11


select e.nome,
		c.nome
		from lic_empresa e
		inner join lic_cidade c 
		ON
		e.idcidade = c.idcidade
		where cnpj like '%86'


/*Exercício 12
Buscar no item da licitação as licitações do ID 60, mostrar no select o nome do material (apenas os 20 primeiros caracteres),
quantidade e o valor médio (entre valor mínimo e máximo) multiplicado pela quantidade.
Ordene pela quantidade.
*/

--12

select il.*,
		SUBSTRING(m.nome,1,20) as NomeMaterial,
		quantidade,
		((il.valor_maximo+il.valor_minimo/2)*quantidade) as valor_medio
from lic_item_licitacao il
inner join lic_material m
ON
m.idmaterial = il.idmaterial
where idlicitacao = 60
order by quantidade

/*Exercício 13
Buscar o título da licitação, início da vigência, quantidade, nome do material e nome da classe do material, 
onde o id contratante seja igual a 643 no mês de março de 2018 o fechamento.
*/

--13

select lic.titulo,
		lic.inicio_vigencia,
		il.quantidade,
		m.nome,
		cm.nome
		from lic_licitacao lic
		inner join lic_item_licitacao il on lic.idlicitacao = il.idlicitacao
		inner join lic_material m on m.idmaterial = il.idmaterial
		inner join lic_classe_material cm ON cm.idclasse_material = m.idclasse_material
		where idcontratante = 643
		and extract(month from data_fechamento) = 3 
		and extract(year from data_fechamento) = 2018


/*Exercício 14
Listar todas as propostas com o resultado da diferença entre o maior valor final e o menor e o número da licitação. 
Ordenar da maior diferença para a menor.
*/

select *,
		avg(valor_final) as diferenca
from lic_proposta
group by idlicitacao,idproposta
order by diferenca desc

/*Exercício 15
Listar os campos destacados abaixo de todas as propostas junto com os devidos relacionamentos.

Id Proposta
Data de inscrição da proposta
Nome da Empresa
Nome da Cidade da empresa
Estado da Empresa
CPF / CNPJ (Na mesma columa as duas informações qdo tem CNPJ apare o CNPJ e quando tem CPF aparece o CPF da empresa)
Titulo da licitação
Situação da licitação
Quantidade de cada item da licitação
Observação do item
Nome do material do item
*/

--15

select pro.idproposta,
		pro.data_inscricao AS data_inscricao_proposta,
		emp.nome as nome_empresa,
		cid.nome as nome_cidade_empresa,
		case when cnpj is null then cpf = cpf
			 when cpf is null then cpf = cnpj
			 when cnpj is not null and cpf is not null then cpf = concat(cpf,' / ',cnpj)
			end cpf_cnpj,
		lic.titulo as titulo_licitacao,
		lic.situacao AS situacao_licitacao,
		itlic.quantidade,
		itlic.observacao,
		mat.nome as nome_material_item
		from lic_proposta pro
		inner join lic_empresa emp ON pro.idempresa = emp.idempresa
		INNER JOIN lic_cidade cid ON emp.idcidade = cid.idcidade
		INNER JOIN lic_licitacao lic ON lic.idlicitacao = pro.idlicitacao
		INNER JOIN lic_item_licitacao itlic ON itlic.idlicitacao = pro.idlicitacao
		INNER JOIN lic_material mat ON itlic.idmaterial = mat.idmaterial
		order by cpf_cnpj;


/*Exercício 16
Selecionar todos os materiais que nunca foram incluídos numa licitação. Apenas a quantidade.
*/

--16
select count(*) from material
where not exists(select lic.idmaterial from lic_item_licitacao lic inner join lic_material m on m.idmaterial = lic.idmaterial)
and
exists(select idmaterial from material);

