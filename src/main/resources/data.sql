USE ecommerce;

CREATE TABLE IF NOT EXISTS category (
    id int AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS products (
    id int AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    cost DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL,
    image_url VARCHAR(255),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO category (id, name, description) VALUES (1, 'Electronics', 'Electronic gadgets and devices');
INSERT INTO category (id, name, description) VALUES (2, 'Books', 'All kinds of books');

INSERT INTO products (id, title, description, cost, quantity, image_url, category_id) VALUES
             (1, 'Smartphone', 'Latest model smartphone', 699.99, 50, 'http://example.com/smartphone.jpg', 1),
             (2, 'Laptop', 'High performance laptop', 1299.99, 30, 'http://example.com/laptop.jpg', 1),
             (3, 'E-Book Reader', 'Portable e-book reader', 199.99, 100, 'http://example.com/ebook_reader.jpg', 1),
             (4, 'Science Fiction Book', 'A science fiction novel', 19.99, 200, 'http://example.com/scifi_book.jpg', 2),
             (5, 'Cookbook', 'A cookbook with recipes', 29.99, 150, 'http://example.com/cookbook.jpg', 2);
