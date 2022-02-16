create table if not exists actions_detail
(
    id int auto_increment
        primary key,
    name varchar(255) not null,
    type varchar(255) not null,
    hold_size int null
);

create table if not exists categories
(
    id int auto_increment
        primary key,
    name varchar(255) not null,
    language varchar(255) not null,
    parent_id int null
);

create table if not exists program
(
    id int auto_increment
        primary key,
    name varchar(255) not null,
    image_uri varchar(255) null,
    image blob null,
    products_xml_link varchar(255) null,
    gotoLink varchar(255) null
);

create table if not exists rates
(
    id int auto_increment
        primary key,
    name varchar(255) not null
);

create table if not exists tariff
(
    id int auto_increment
        primary key,
    name varchar(255) not null
);