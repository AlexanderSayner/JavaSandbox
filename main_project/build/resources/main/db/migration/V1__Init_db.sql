drop table if exists article cascade
;
create table article (
    id  serial not null,
    manufacturer varchar(255),
    name varchar(255) not null,
    primary key (id)
)
;
