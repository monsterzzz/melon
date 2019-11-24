
# drop table user;
# drop table comment;
# drop table news;
# drop table photo;

show tables ;


create table user(
    id int(32) primary key AUTO_INCREMENT,
    username char(16) not null unique ,
    password char(16) not null ,
    nickname char(16) not null ,
    avatar varchar(255) not null ,
    description char(255) not null,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table news(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null,
    event_id char(16) not null ,
    content char(16) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table photo(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    comment_id int(32) not null ,
    path varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



create table comment(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    news_id int(32) not null ,
    reply_id int(32) default null ,
    content varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





