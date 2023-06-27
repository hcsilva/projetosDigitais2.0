alter table LINKS drop column link;
alter table LINKS add column url varchar(100) not null;