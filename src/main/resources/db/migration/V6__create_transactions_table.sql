CREATE TABLE transactions (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    amount NUMERIC(19, 2) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(255),
    category_id BIGINT NOT NULL,

    CONSTRAINT fk_transactions_category
        FOREIGN KEY (category_id) REFERENCES category (id)
);
