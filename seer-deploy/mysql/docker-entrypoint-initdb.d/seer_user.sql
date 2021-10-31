create table user
(
    name   char(20)  not null
        primary key,
    passwd char(100) null,
    salt   char(30)  null
);

