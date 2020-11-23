drop schema if exists demo;
create schema demo;

create table message
(
	id varchar(50) not null
		constraint message_pk
			primary key,
	text varchar(500) not null,
	created timestamp default now() not null
);
