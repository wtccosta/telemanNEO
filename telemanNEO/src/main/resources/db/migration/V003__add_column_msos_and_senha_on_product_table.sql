alter table produto
add column msos varchar(100) default null after agrupador_id;

alter table produto
add column senha varchar(100) default null after msos;