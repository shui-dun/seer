create table user_role
(
    user char(20) not null,
    role char(25) not null,
    primary key (user, role)
);

