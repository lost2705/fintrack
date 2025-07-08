CREATE TABLE expense (
    id BIGSERIAL PRIMARY KEY,
    amount NUMERIC(15, 2) NOT NULL,
    description TEXT,
    date_time TIMESTAMP NOT NULL,
    category_id BIGINT REFERENCES category(id)
);