--changeset dao:1
create table if not exists student (
    id bigint PRIMARY KEY not null
);
--rollback SQL STATEMENT