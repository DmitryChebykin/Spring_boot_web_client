create table if not exists actions_detail
(
    id int 
        primary key,
    name varchar(255),
    type varchar(255),
    hold_size int  
);

create table if not exists categories
(
    id int 
        primary key,
    name varchar(255),
    language varchar(255) ,
    parent_id int 
);

create table if not exists program
(
    id int
        primary key,
    name varchar(255) ,
    image_uri varchar(255) ,
    image blob ,
    products_xml_link varchar(255) ,
    goto_link varchar(255)
);

create table if not exists rates
(
    id int 
        primary key,
    name varchar(255) 
);

create table if not exists tariff
(
    id int 
        primary key,
    name varchar(255) 
);