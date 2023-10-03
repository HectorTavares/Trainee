-- Exercicio 1
Create table AlunoAula1(
	Nome varchar(255) not null,
	DataNascimento date not null,
	Cidade varchar(255) not null,
	CPF varchar(11)not null unique,
	Matricula varchar(255) not null,
	CursoMatriculado BIGINT
);

-- Exercicio 2
Create table CursoAula1(
	Nome VARCHAR(255) not null,
	Data DATE,
	Situacao VARCHAR(255),
	PreRequisitos VARCHAR(255),
	Local VARCHAR(255)
);

-- Exercicio 3

ALTER TABLE CursoAula1 ADD COLUMN Valor FLOAT not null;

-- Exercicio 4
ALTER TABLE AlunoAula1 ADD COLUMN Id_Aluno BIGINT not null unique GENERATED ALWAYS AS IDENTITY PRIMARY KEY;
ALTER TABLE CursoAula1 ADD COLUMN Id_Curso BIGINT not null unique GENERATED ALWAYS AS IDENTITY PRIMARY KEY;
ALTER TABLE AlunoAula1 ADD CONSTRAINT CursoMatriculado FOREIGN KEY (CursoMatriculado) REFERENCES CursoAula1(Id_Curso);

-- Exercicio 5
-- A relação é que o aluno pode estar matriculado em um curso.
