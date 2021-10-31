create table role_perm
(
    role char(25) not null,
    perm char(40) not null,
    primary key (role, perm)
);

INSERT INTO seer.role_perm (role, perm) VALUES ('admin', 'comment:delete');
INSERT INTO seer.role_perm (role, perm) VALUES ('admin', 'comment:list');
INSERT INTO seer.role_perm (role, perm) VALUES ('admin', 'user:delete');
INSERT INTO seer.role_perm (role, perm) VALUES ('admin', 'user:list');