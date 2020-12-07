
create database t_order;

create table t_order.order_info(
    order_id bigint(32) not null primary key auto_increment,
    emp_name varchar(10) null comment '姓名',
    shop_name varchar(50) null comment '商品名称',
    create_time timestamp default current_timestamp not null comment '创建时间',
    update_time timestamp null on update current_timestamp
)engine=innodb character set utf8mb4;