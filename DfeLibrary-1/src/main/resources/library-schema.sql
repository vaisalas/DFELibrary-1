drop table if exists library CASCADE; 
create table library
 (
 	id integer primary key auto_increment, 
 	author varchar(255), 
 	genre varchar(255), 
 	name varchar(255)
 );