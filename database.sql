CREATE TABLE categories(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

SELECT * FROM categories;

CREATE TABLE products(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories (id)
);