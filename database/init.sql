CREATE TABLE users
(
    user_id       UUID PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE categories
(
    category_id UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);


CREATE TABLE posts
(
    post_id     UUID PRIMARY KEY,
    user_id     UUID,
    title       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    category_id UUID,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (category_id) REFERENCES categories (category_id)
);


CREATE TABLE comments
(
    comment_id UUID PRIMARY KEY,
    post_id    UUID,
    user_id    UUID,
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (post_id) REFERENCES posts (post_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);


CREATE TABLE tags
(
    tag_id UUID PRIMARY KEY,
    name   VARCHAR(50) NOT NULL UNIQUE
);


CREATE TABLE post_tags
(
    post_id UUID,
    tag_id  UUID,
    PRIMARY KEY (post_id, tag_id),
    FOREIGN KEY (post_id) REFERENCES posts (post_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
);