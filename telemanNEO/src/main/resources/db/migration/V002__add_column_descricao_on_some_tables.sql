alter table secretaria
add column descricao varchar(255) default null;

alter table servico_operadora
add column descricao varchar(255) default null;

alter table roteador
add column descricao varchar(255) default null;

alter table plano_operadora
add column descricao varchar(255) default null;

alter table local
add column descricao varchar(255) default null after nome;