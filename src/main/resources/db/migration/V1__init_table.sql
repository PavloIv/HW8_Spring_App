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
    email VARCHAR(255) UNIQUE,
    password VARCHAR(100),
    active BOOLEAN,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(10),
	FOREIGN KEY(id)
    REFERENCES users(id)
);

