
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
    avatar int(32) not null default 1,
    description char(255) not null,
    status int(3) not null default 0,
    email char(32) not null ;
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# update user set avatar = '';
# alter table user add column status int(3) not null default 0;

create table news(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null,
    event_id char(16) not null ,
    admin_opt int(2) not null default 0,
    happen_time int(5) not null default 0,
    content varchar(255) not null ,
    like_num int(15) not null default 0,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# alter table news drop column like_num;

# drop table event;
create table event(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null,
    name varchar(30) not null ,
    description varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# alter table event add column view_num int(32) not null default 0;

# drop table tag;
create table tag(
    id int(32) primary key AUTO_INCREMENT,
    description char(10) not null unique ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# alter table tag modify column description char(10) not null unique;

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
    path varchar(255) not null ,
    md5 char(32) not null unique ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
# alter table avatar add column md5 char(32) not null ;

create table comment(
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    news_id int(32) not null ,
    reply_id int(32) default null ,
    content varchar(255) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table comment_like (
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    comment_id int(32) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table news_like (
    id int(32) primary key AUTO_INCREMENT,
    user_id int(32) not null ,
    news_id int(32) not null ,
    create_time datetime DEFAULT CURRENT_TIMESTAMP,
    update_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


