create table topics(
    id bigint not null auto_increment,
    title varchar(100) not null,
    message varchar(300) not null,
    creation_date datetime not null,
    status tinyint not null,
    author varchar(100) not null,
    course varchar(200) not null,

    primary key(id)
);