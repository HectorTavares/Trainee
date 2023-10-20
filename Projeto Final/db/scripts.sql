CREATE TABLE public.tag (
	id int8 NOT NULL,
	nome varchar(30) NULL,
	CONSTRAINT tag_pkey PRIMARY KEY (id)
);




CREATE TABLE public.usuario (
	id_usuario int8 NOT NULL,
	avatar varchar(255) NULL,
	email varchar(255) NULL,
	is_monitor bool NULL,
	reputacao float8 NULL,
	username varchar(255) NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario)
);


CREATE TABLE public.pergunta (
	id int8 NOT NULL,
	descricao varchar(1000) NULL,
	foto varchar(1000) NULL,
	titulo varchar(100) NULL,
	data_hora timestamp NULL,
	autor_id_usuario int8 NULL,
	CONSTRAINT pergunta_pkey PRIMARY KEY (id),
	CONSTRAINT fk6e361f442b2yj8ryd1mqy8a1x FOREIGN KEY (autor_id_usuario) REFERENCES public.usuario(id_usuario)
);




CREATE TABLE public.pergunta_tags (
	pergunta_id int8 NOT NULL,
	tags_id int8 NOT NULL,
	CONSTRAINT fkh2mp8r2ehqnmxgb7fm6yj83mo FOREIGN KEY (tags_id) REFERENCES public.tag(id),
	CONSTRAINT fktd4t1fgsl8nf8gbbd5sr22019 FOREIGN KEY (pergunta_id) REFERENCES public.pergunta(id)
);



CREATE TABLE public.resposta (
	id int8 NOT NULL,
	descricao varchar(1000) NULL,
	foto varchar(1000) NULL,
	is_aprovado bool NULL,
	data_hora timestamp null,
	autor_id_usuario int8 NULL,
	pergunta_id int8 NULL,
	CONSTRAINT resposta_pkey PRIMARY KEY (id),
	CONSTRAINT fkhl4axbv8d1yoixs9vtq7yui20 FOREIGN KEY (pergunta_id) REFERENCES public.pergunta(id),
	CONSTRAINT fkl51j3tr1qh1txm4kus2q55aya FOREIGN KEY (autor_id_usuario) REFERENCES public.usuario(id_usuario)
);




CREATE TABLE public.interacao_pergunta (
	id int8 NOT NULL,
	tipo_interacao int4 NULL,
	autor_id_usuario int8 NULL,
	pergunta_id int8 NULL,
	CONSTRAINT interacao_pergunta_pkey PRIMARY KEY (id),
	CONSTRAINT fk2ay6bcw3abtvo0syk4wae5eam FOREIGN KEY (pergunta_id) REFERENCES public.pergunta(id),
	CONSTRAINT fk9d1dh10kkkkd54tu1b2ehrl9k FOREIGN KEY (autor_id_usuario) REFERENCES public.usuario(id_usuario)
);




CREATE TABLE public.interacao_resposta (
	id int8 NOT NULL,
	tipo_interacao int4 NULL,
	autor_id_usuario int8 NULL,
	resposta_id int8 NULL,
	CONSTRAINT interacao_resposta_pkey PRIMARY KEY (id),
	CONSTRAINT fkakfc0qo8um7ye92mbxqudtlvm FOREIGN KEY (resposta_id) REFERENCES public.resposta(id),
	CONSTRAINT fkmssjiq3jv3claipxwul06dgo2 FOREIGN KEY (autor_id_usuario) REFERENCES public.usuario(id_usuario)
);

CREATE TABLE notificacao(
	id int8 NOT null,
	tipo_notificacao varchar(255) NULL,
	mensagem varchar(255) not null,
	destinatario_id_usuario int8 not null,
	pergunta_id int8,
	resposta_id int8,
	
	CONSTRAINT notificacao_pkey PRIMARY KEY (id),
	CONSTRAINT fk_pergunta FOREIGN KEY (pergunta_id) REFERENCES public.pergunta(id),
	CONSTRAINT fk_resposta FOREIGN KEY (resposta_id) REFERENCES public.resposta(id),
	CONSTRAINT fk_id_usuario FOREIGN KEY (destinatario_id_usuario) REFERENCES public.usuario(id_usuario)
);


CREATE SEQUENCE public.seq_interacao
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;




CREATE SEQUENCE public.seq_interacao_pergunta
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;




CREATE SEQUENCE public.seq_interacao_resposta
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;



CREATE SEQUENCE public.seq_notificacao
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;




CREATE SEQUENCE public.seq_pergunta
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


CREATE SEQUENCE public.seq_resposta
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;




CREATE SEQUENCE public.seq_tag
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;



CREATE SEQUENCE public.seq_usuario
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;




CREATE SEQUENCE public.seq_voto
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
