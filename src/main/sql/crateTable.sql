
# drop table user;
# drop table comment;
# drop table news;
# drop table media;

# show tables ;


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
    admin_opt int(2) not null default 0,
    happen_time int(5) not null default 0,
    content char(16) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# drop table event;
create table event(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null,
    name varchar(30) not null ,
    description varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# drop table tag;
create table tag(
    id int(32) primary key AUTO_INCREMENT,
    description varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table event_tag(
    id int(32) primary key AUTO_INCREMENT,
    event_id int(32) not null ,
    tag_id int(32) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table media(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    news_id int(32) not null ,
    type char(10) not null ,
    path varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# drop table avatar;
create table avatar(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    path varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table comment(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    news_id int(32) not null ,
    reply_id int(32) default null ,
    content varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





