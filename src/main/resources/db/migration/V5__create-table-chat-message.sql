CREATE TABLE chat_message (
    id BIGINT NOT NULL AUTO_INCREMENT,
    conversation_id VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL,

    PRIMARY KEY (id)
);