create table comment
(
    id      int auto_increment
        primary key,
    user    char(20)     null,
    pet     smallint     null,
    time    timestamp    null,
    content varchar(350) null
);

