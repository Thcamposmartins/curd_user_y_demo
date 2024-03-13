CREATE TABLE users (
    id RAW(16) PRIMARY KEY,
    nick_name VARCHAR2(32),
    name VARCHAR2(255) NOT NULL UNIQUE,
    birth_date TIMESTAMP NOT NULL
);

CREATE TABLE user_stack (
    user_id RAW(16) REFERENCES users(id),
    stack VARCHAR2(32),
    PRIMARY KEY (user_id, stack)
);

INSERT INTO users (id, nick_name, name, birth_date) VALUES (SYS_GUID(), 'nick1', 'User 1', TIMESTAMP '2000-01-01 00:00:00');
INSERT INTO users (id, nick_name, name, birth_date) VALUES (SYS_GUID(), 'nick2', 'User 2', TIMESTAMP '2001-02-02 00:00:00');

INSERT INTO user_stack (user_id, stack) VALUES ((SELECT id FROM users WHERE name = 'User 1'), 'Java');
INSERT INTO user_stack (user_id, stack) VALUES ((SELECT id FROM users WHERE name = 'User 1'), 'Spring');
INSERT INTO user_stack (user_id, stack) VALUES ((SELECT id FROM users WHERE name = 'User 2'), 'Kotlin');
INSERT INTO user_stack (user_id, stack) VALUES ((SELECT id FROM users WHERE name = 'User 2'), 'Android');