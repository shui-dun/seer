use seer;

create table role
(
    name char(25) primary key
);

create table perm
(
    name char(40) primary key
);

create table user
(
    name   char(20) primary key,
    passwd char(100),
    salt   char(30)
);

create table role_perm
(
    role char(25),
    perm char(40),
    primary key (role, perm),
    foreign key (role) references role (name),
    foreign key (perm) references perm (name)
);

create table user_role
(
    user char(20),
    role char(25),
    primary key (user, role),
    foreign key (user) references user (name),
    foreign key (role) references role (name)
);

create table comment
(
    id      int primary key auto_increment,
    user    char(20),
    pet     smallint,
    time    timestamp,
    content varchar(350),
    foreign key (user) references user (name),
    foreign key (pet) references pet (id)
);

insert into role (name)
values ("normal");
insert into role (name)
values ("admin");

insert into perm
values ("comment:delete");
insert into perm
values ("user:delete");
insert into perm
values ("comment:list");
insert into perm
values ("user:list");


insert into role_perm
values ("admin", "comment:delete");
insert into role_perm
values ("admin", "comment:list");
insert into role_perm
values ("admin", "user:delete");
insert into role_perm
values ("admin", "user:list");