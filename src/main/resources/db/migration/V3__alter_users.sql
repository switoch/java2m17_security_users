drop table authorities;
drop table users;
create table users(
                      id BIGINT GENERATED ALWAYS AS IDENTITY,
                      username varchar(50) not null primary key,
                      password varchar(500) not null,
                      enabled boolean not null
);

create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

insert into users(username, password, enabled) VALUES ('admin','$2a$12$ZAWnjUvufIAQB9ZRTJxkOuRizfpILm6xa6wY7ZXeGNZ8Kk47Kcp9a', '1');
insert into users(username, password, enabled) VALUES ('simple','$2a$12$Y/KnOFiEVHFOtSWYBy/QGO3W1cOC6kozckQ9PRU/ugRJNLE0JyTTq', '1');

insert into authorities(username, authority) VALUES ('admin', 'ADMIN');
insert into authorities(username, authority) VALUES ('simple', 'USER');