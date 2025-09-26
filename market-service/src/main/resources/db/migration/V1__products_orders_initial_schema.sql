CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE products (
    id bigserial primary key,
    title varchar(255),
    price float
);

CREATE TABLE carts (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_id bigint not null,
    products jsonb,
    total_price float
);

-- CREATE TABLE orders (
--                         id uuid primary key,
--                         user_order_id bigint auto_increment unique not null,
--                         user_id bigint not null,
--                         title varchar(255),
--                         price double
-- );
