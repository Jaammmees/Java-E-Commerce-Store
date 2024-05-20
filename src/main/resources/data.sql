USE ecommerce;

-- Dropping existing tables in reverse order to avoid foreign key constraint issues
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS category;

-- Creating tables
CREATE TABLE category (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT
);

CREATE TABLE products (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          description TEXT,
                          cost DECIMAL(10,2) NOT NULL,
                          quantity BIGINT NOT NULL,
                          image_url VARCHAR(255),
                          category_id BIGINT,
                          FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       CHECK (email LIKE '%@%')
);

CREATE TABLE orders (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        total_amount DECIMAL(10,2) NOT NULL,
                        status VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE order_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             product_id BIGINT NOT NULL,
                             quantity BIGINT NOT NULL,
                             unit_price DECIMAL(10, 2) NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE carts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            cart_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            quantity BIGINT NOT NULL,
                            FOREIGN KEY (cart_id) REFERENCES carts(id),
                            FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Inserting initial data
INSERT INTO category (name, description) VALUES
                                             ('Electronics', 'Electronic gadgets and devices'),
                                             ('Books', 'All kinds of books');

INSERT INTO products (title, description, cost, quantity, image_url, category_id) VALUES
                                                                                      ('Smartphone', 'Latest model smartphone', 699.99, 50, 'http://example.com/smartphone.jpg', 1),
                                                                                      ('Laptop', 'High performance laptop', 1299.99, 30, 'http://example.com/laptop.jpg', 1),
                                                                                      ('E-Book Reader', 'Portable e-book reader', 199.99, 100, 'http://example.com/ebook_reader.jpg', 1),
                                                                                      ('Science Fiction Book', 'A science fiction novel', 19.99, 200, 'http://example.com/scifi_book.jpg', 2),
                                                                                      ('Cookbook', 'A cookbook with recipes', 29.99, 150, 'http://example.com/cookbook.jpg', 2);

INSERT INTO users (first_name, last_name, email) VALUES
                                                     ('James', 'Smith', 'james.smith@example.com'),
                                                     ('Emily', 'Johnson', 'emily.johnson@example.com'),
                                                     ('Michael', 'Williams', 'michael.williams@example.com'),
                                                     ('Linda', 'Brown', 'linda.brown@example.com'),
                                                     ('David', 'Davis', 'david.davis@example.com');

INSERT INTO orders (user_id, total_amount, status, created_at, updated_at) VALUES
                                                                               (1, 1000.00, 'Processing', NOW(), NOW()),
                                                                               (2, 550.50, 'Shipped', NOW(), NOW()),
                                                                               (3, 320.75, 'Delivered', NOW(), NOW());

INSERT INTO order_items (order_id, product_id, quantity, unit_price) VALUES
                                                                         (1, 1, 1, 699.99),
                                                                         (1, 4, 2, 19.99),
                                                                         (2, 3, 1, 199.99),
                                                                         (3, 2, 1, 1299.99);

INSERT INTO carts (user_id, created_at, updated_at) VALUES
                                                        (1, NOW(), NOW()),
                                                        (2, NOW(), NOW()),
                                                        (3, NOW(), NOW());

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
                                                           (1, 5, 3),
                                                           (2, 4, 2),
                                                           (3, 1, 1);
