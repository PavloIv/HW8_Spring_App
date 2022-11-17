CREATE TABLE producers(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL,
    producer_id INTEGER,
    FOREIGN KEY(producer_id)
    REFERENCES producers(id)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(100),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    role VARCHAR(10), CHECK(role = 'admin' OR role = 'user')
);

