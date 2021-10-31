create table role
(
    name char(25) not null
        primary key
);

INSERT INTO seer.role (name) VALUES ('admin');
INSERT INTO seer.role (name) VALUES ('normal');