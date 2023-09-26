CREATE TABLE Usuario(
id_usuario INTEGER NOT NULL,
nome VARCHAR (40),
senha VARCHAR (10) NOT NULL,
PRIMARY KEY(id_usuario)
);

CREATE TABLE Usuario_operador(
id_usuario_operador INTEGER NOT NULL,
id_usuario INTEGER NOT NULL,
PRIMARY KEY(id_usuario_operador),
FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Usuario_gerente(
id_usuario_gerente INTEGER NOT NULL,
id_usuario INTEGER NOT NULL,
PRIMARY KEY(id_usuario_gerente),
FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Usuario_admin(
id_usuario_admin INTEGER NOT NULL,
id_usuario INTEGER NOT NULL,
PRIMARY KEY(id_usuario_admin),
FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Usuario_final(
id_usuario_final INTEGER NOT NULL,
id_usuario INTEGER NOT NULL,
PRIMARY KEY(id_usuario_final),
FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);


CREATE TABLE status_chamado(
id_status_chamado VARCHAR (50) NOT NULL,
tempo_inicio TIMESTAMP NOT NULL,
tempo_termino TIMESTAMP NOT NULL,	
PRIMARY KEY(id_status_chamado)
);

CREATE TABLE chamado(
id_status_chamado VARCHAR (50) NOT NULL,
id_usuario_operador INTEGER,
FOREIGN KEY (id_usuario_operador) REFERENCES Usuario_operador(id_usuario_operador),
FOREIGN KEY (id_status_chamado) REFERENCES status_chamado(id_status_chamado)
);

CREATE TABLE status_atual(
id_status_atual VARCHAR (50) NOT NULL ,
nome VARCHAR (50) NOT NULL,
id_status_chamado VARCHAR (50) NOT NULL,
PRIMARY KEY(id_status_atual),
FOREIGN KEY (id_status_chamado) REFERENCES status_chamado(id_status_chamado)	
);

CREATE TABLE servico(
id_servico INTEGER NOT NULL,
nome VARCHAR (50) NOT NULL,
PRIMARY KEY(id_servico)
);

CREATE TABLE area(
id_area INTEGER NOT NULL ,
nome VARCHAR (50) NOT NULL,
resposta VARCHAR (300) NOT NULL,
id_servico INTEGER NOT NULL,
PRIMARY KEY(id_area),
FOREIGN KEY (id_servico) REFERENCES servico(id_servico)	
);

