create table perm
(
    name char(40) not null
        primary key
);

INSERT INTO seer.perm (name) VALUES ('comment:delete');
INSERT INTO seer.perm (name) VALUES ('comment:list');
INSERT INTO seer.perm (name) VALUES ('user:delete');
INSERT INTO seer.perm (name) VALUES ('user:list');