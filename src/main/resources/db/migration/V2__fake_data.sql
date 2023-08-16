-- Insert fake test records into users table
INSERT INTO users (first_name, last_name, email, password, created_at, updated_at)
VALUES
    ('John', 'Doe', 'john@example.com', 'Abc12345', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Jane', 'Smith', 'jane@example.com', 'Hello123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Alice', 'Johnson', 'alice@example.com', 'Password9', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Bob', 'Williams', 'bob@example.com', '1234abcd', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Emily', 'Brown', 'emily@example.com', 'SecurePass1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Foo', 'Bar', 'Foo@example.com', 'SecurePass1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert fake test records into authors table
INSERT INTO authors (username, user_id, created_at, updated_at)
VALUES
    ('author1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('author2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('author3', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('author4', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('author5', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert fake test records into articles table
INSERT INTO articles (title, content, author_id, created_at, updated_at)
VALUES
    ('Article Title 1', 'Article Content 1 Article Content 1 Article Content 1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Article Title 2', 'Article Content 2 Article Content 2 Article Content 2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Article Title 3', 'Article Content 3 Article Content 3 Article Content 3', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Article Title 4', 'Article Content 4 Article Content 4 Article Content 4', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Article Title 5', 'Article Content 5 Article Content 5 Article Content 5', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insert fake test records into article_tags table
INSERT INTO article_tags (article_id, tags)
VALUES
    (1, 'EXHIBITIONS'),
    (1, 'EVENTS'),
    (2, 'ARCHAEOLOGY'),
    (3, 'INTERVIEWS'),
    (4, 'BEHIND_THE_SCENES');

-- Insert fake test records into events table
INSERT INTO events (title, content, timing, capacity, status, author_id, created_at, updated_at)
VALUES
    ('Event Title 1', 'Event Content 1 Event Content 1 Event Content 1', CURRENT_TIMESTAMP, 100, 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Event Title 2', 'Event Content 2 Event Content 2 Event Content 2', CURRENT_TIMESTAMP, 50, 'SCHEDULED', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Event Title 3', 'Event Content 3 Event Content 3 Event Content 3', CURRENT_TIMESTAMP, 200, 'ACTIVE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Event Title 4', 'Event Content 4 Event Content 4 Event Content 4', CURRENT_TIMESTAMP, 75, 'CANCELED', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Event Title 5', 'Event Content 5 Event Content 5 Event Content 5', CURRENT_TIMESTAMP, 120, 'ACTIVE', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
