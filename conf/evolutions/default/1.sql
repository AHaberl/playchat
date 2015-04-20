# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table message (
  id                        bigint auto_increment not null,
  owner_id                  bigint,
  created_at                date,
  type                      varchar(255),
  uid                       varchar(255),
  origin                    varchar(255),
  constraint pk_message primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  user_name                 varchar(255),
  status                    varchar(255),
  created_at                date,
  constraint pk_user primary key (id))
;

alter table message add constraint fk_message_owner_1 foreign key (owner_id) references user (id) on delete restrict on update restrict;
create index ix_message_owner_1 on message (owner_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table message;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

