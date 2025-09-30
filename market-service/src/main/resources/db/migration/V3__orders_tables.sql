CREATE TYPE delivery_step AS ENUM ('CREATED', 'SENT_TO_DELIVERY', 'ON_THE_WAY', 'COMPLETED', 'CANCELED', 'ISSUE');

CREATE TABLE orders (
                        id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
                        user_id bigint NOT NULL,
                        user_order_id varchar(255) NOT NULL,
                        items jsonb NOT NULL,
                        price double precision,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        finished_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status delivery_step NOT NULL DEFAULT 'CREATED'
);

