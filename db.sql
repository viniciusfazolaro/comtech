create database assistencia;

use assistencia;

create table Client
(
    id                  bigint(20) primary key auto_increment,
    name                varchar(50)        not null,
    email               varchar(50)        not null,
    phone               varchar(50)        not null,
    cpf                 varchar(50) unique not null,
    enabled             boolean            not null,
    addressStreet       varchar(50)        not null,
    addressNumber       varchar(10)        not null,
    addressComplement   varchar(10),
    addressNeighborhood varchar(50)        not null,
    addressCep          varchar(20)        not null,
    addressCity         varchar(50)        not null,
    addressState        varchar(50)        not null
) engine = InnoDB
  default charset = utf8;

create table PaymentMethod
(
    id   bigint(20) primary key auto_increment,
    name varchar(100) not null
) engine = InnoDB
  default charset = utf8;

create table ServiceOrder
(
    id              bigint(20) primary key auto_increment,
    description     varchar(50)    not null,
    emissionDate    date           not null,
    finishDate      date           not null,
    value           decimal(10, 2) not null,
    observation     varchar(50)    not null,
    status          varchar(50)    not null,
    paymentMethodId bigint(20)     not null,
    clientId        bigint(20)     not null,

    foreign key (paymentMethodId) references PaymentMethod (id),
    foreign key (clientId) references Client (id)
) engine = InnoDB
  default charset = utf8;

create table User
(
    id bigint(20) primary key auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    password varchar(50) not null,
    dateOfBirth date not null,
    gender varchar(20) not null,
    active boolean not null
) engine = InnoDB
  default charset = utf8;