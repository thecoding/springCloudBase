create table tx_manager.tx_order
(
    id         bigint auto_increment
        primary key,
    order_name varchar(30) null comment '订单名'
);

create table tx_manager.tx_pay
(
    id       bigint auto_increment
        primary key,
    pay_name varchar(30) null
);

