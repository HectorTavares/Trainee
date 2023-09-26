--1
CREATE TABLE cidadeaux 
as
SELECT * FROM cidadeex;

--2
TRUNCATE TABLE cidadeaux;
INSERT INTO cidadeaux 
(idcidade,nome,uf)
VALUES
(1,'Sao Leopoldo','RS'),
(2,'Porto Alegre','RS'),
(3,'Parobe','RS'),
(4,'Taquara','RS'),
(5,'Sao Gabriel','RS'),
(6,'Sao Borja','RS'),
(7,'Bagé','RS'),
(8,'Bento Gonçalves','RS'),
(9,'Santana do Livramento','RS'),
(10,'Santa Maria','RS'),
(11,'Santa Rosa','RS'),
(12,'Sao Francisco de Paula','RS');

--3
INSERT INTO EstoqueEx
(id,nomeestoque,datacriacao,capacidade)
VALUES
(1,'Frutas','2021-06-23',450);

--4
INSERT INTO ProdutoEx
(id,nome,descProduto,dtCriacao,idEstoque,quantidade,preco)
VALUES
(1,'Bergamotas','fruta doce com casca laranja','2021-02-22',1,200);
(2,'Maca','fruta doce com casca vermelha','2021-02-22',1,250);

--5
 UPDATE associadoex
 SET cpf = '123.321.22',
 endereco = 'av pascoal',
 bairro = 'olimpica',
 complemento  = '22abb',
 idcidade = 4
 WHERE idassociado = 1;

--6

DELETE from cidade
where nome = 'Campinas';

DELETE from cidade
where nome = 'Taquara';