# :fa-taxi: Me Leva ai  :fa-taxi:

- Bem vindo ao Me leva ai.
- Um projeto feito por Héctor Tavares da Silva.
	___
- Me leva ai é uma aplicação de carona, no qual você pode chamar motoristas para te levar para diversos lugares.


## Passo a Passo 

1) Primeiro você deve importar o Arquivo me-leva-ai-import no seu Api Client de preferencia(indico [Insomnia](http://https://insomnia.rest/download "Insomnia"))
* Arquivo na raiz do projeto

	___
2) Crie um motorista, lembre de passar dados que façam sentido
* exemplo : cpf : xxx.xxx.xxx-xx, email : email@email
	___
3) Crie um Passageiro, lembrando do que foi dito antes.
	___
4) Crie um veiculo(lembre de colocar o cpf de um motorista que já existe)
	___
5)Pode pedir uma corrida
	___
6) Lembre-se de depositar na conta do passageiro, para conseguir pagar o motorista na hora de sair da corrida.
	___
7) Inicie a corrida
	___
8) se você tem como pagar pela corrida, pode terminar ela no momento que quiser.
	___
9)Pode dar as notas da corrida.
	___
10)Pode sacar o dinheiro da conta do motorista.
	___

Depois disso, voce pode livremente pegar as listas de corrida, veiculo, passageiro ou motorista.
Também pode excluir motoristas ou passageiros já cadastrados.
	___

### Script Sql (Pode copiar aqui, ou baixar o arquivo na raiz do projeto)


    CREATE TABLE cnh (
    	id int NOT NULL,
    	categoria int NOT NULL,
    	data_vencimento date NOT NULL,
    	numero varchar(255) NOT NULL,
    	CONSTRAINT cnh_pkey PRIMARY KEY (id)
    );
    
    
    CREATE TABLE pessoa (
    	id int NOT NULL,
    	conta float NOT NULL,
    	cpf varchar(255) NOT NULL,
    	data_nascimento date NOT NULL,
    	email varchar(255) NOT NULL,
    	nome varchar(255) NOT NULL,
    	CONSTRAINT pessoa_pkey PRIMARY KEY (id)
    );
    
    CREATE TABLE motorista (
    	em_corrida bool NOT NULL,
    	id int NOT NULL,
    	id_cnh int NOT NULL,
    	CONSTRAINT motorista_pkey PRIMARY KEY (id),
    	CONSTRAINT fk_id_pessoa FOREIGN KEY (id) REFERENCES pessoa(id),
    	CONSTRAINT fk_id_cnh FOREIGN KEY (id_cnh) REFERENCES cnh(id)
    );
    
    
    CREATE TABLE passageiro (
    	id int NOT NULL,
    	CONSTRAINT passageiro_pkey PRIMARY KEY (id),
    	CONSTRAINT fk_id_pessoa FOREIGN KEY (id) REFERENCES pessoa(id)
    );
    
    
    
    CREATE TABLE veiculo (
    	id int NOT NULL,
    	ano int NOT NULL,
    	categoria int NOT NULL,
    	cor int NOT NULL,
    	foto varchar(255) ,
    	marca int NOT NULL,
    	modelo varchar(255) NOT NULL,
    	placa varchar(255) NOT NULL,
    	quantidade_lugares int ,
    	proprietario_id int NOT NULL,
    	CONSTRAINT veiculo_pkey PRIMARY KEY (id),
    	CONSTRAINT fk_id_motorista FOREIGN KEY (proprietario_id) REFERENCES motorista(id)
    );
    
    
    
    CREATE TABLE corrida (
    	id int NOT NULL,
    	x_final float NOT NULL,
    	y_final float NOT NULL,
    	x_inicial float NOT NULL,
    	y_inicial float NOT NULL,
    	nota_motorista int ,
    	nota_passageiro int ,
    	tempo_inicio_corrida timestamp ,
    	id_passageiro int ,
    	id_veiculo int ,
    	CONSTRAINT corrida_pkey PRIMARY KEY (id),
    	CONSTRAINT fk_id_passageiro FOREIGN KEY (id_passageiro) REFERENCES passageiro(id),
    	CONSTRAINT fk_id_veiculo FOREIGN KEY (id_veiculo) REFERENCES veiculo(id)
    );
