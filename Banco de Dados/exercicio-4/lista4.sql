--1
=
INSERT INTO alunoaula1
(nome,datanascimento,cidade,cpf,matricula,cursomatriculado)
VALUES
('teste','2002-05-23','esteio','323.323-21','2133',null),
('teste2','2002-05-23','esteio','343.323-21','2133',null),
('teste3','2002-05-23','esteio','323.333-21','2133',null),
('teste4','2002-05-23','esteio','323.313-21','2133',null),
('teste5','2002-05-23','esteio','323.343-21','2133',null),
('teste6','2002-05-23','esteio','323.353-21','2133',null),
('teste7','2002-05-23','esteio','323.253-21','2133',null),
('teste8','2002-05-23','esteio','323.327-21','2133',null),
('teste9','2002-05-23','esteio','323.223-21','2133',null),
('teste10','2002-05-23','esteio','323.532-21','2133',null);

--2
INSERT INTO cursoaula1
(nome,data,situacao,prerequisitos,local,valor)
VALUES
('teste1',CURRENT_DATE,'aberto','nenhum','sala1',200.0),
('teste2',CURRENT_DATE,'aberto','teste','sala1',250.0),
('teste3',CURRENT_DATE,'aberto','nenhum','sala1',25.0),
('teste4',CURRENT_DATE,'aberto','nenhum','sala1',200.0),
('teste5',CURRENT_DATE,'aberto','nenhum','sala1',440.0),
('teste6',CURRENT_DATE,'aberto','teste2','sala1',200.0),
('teste7',CURRENT_DATE,'aberto','nenhum','sala1',230.0),
('teste8',CURRENT_DATE,'aberto','nenhum','sala1',2030.0),
('teste9',CURRENT_DATE,'aberto','nenhum','sala1',210.0),
('teste10',CURRENT_DATE,'aberto','nenhum','sala1',2333.0);

--3
UPDATE cursoaula1
SET situacao = 'ativo';

--4
UPDATE cliente
SET situacao = 'P'
WHERE situacao = 'I';

--5
DELETE FROM associadoex
WHERE sexo = 'M';

--6
UPDATE cursoaula1
SET valor = valor+(valor*0.05);