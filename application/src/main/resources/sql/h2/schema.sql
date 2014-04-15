--drop table if exists s_user;

-- .日历表
create table  t_app_calendar(
	id varchar(64) generated by default as identity, --主键
	userid varchar(64), --用户编号
	title varchar(255) not null,
	details varchar(255),
	startdate varchar(64),
	length int,
	starttime varchar(64),
	endtime varchar(64),
	backgroundcolor varchar(64),
	textcolor varchar(64),
	primary key (id)
);


create table s_user(
    id             varchar(64) generated by default as identity, --主键
    login_name      varchar(64) not null,
    username       varchar(64) not null,
    password       varchar(255) not null,
    salt           varchar(64) not null,
    email          varchar(255),
    register_date   timestamp,
    status         varchar(64),
    primary key (id)
);