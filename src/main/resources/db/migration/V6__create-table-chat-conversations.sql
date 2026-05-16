CREATE TABLE chat_conversations (
    id CHAR(36),
    id_usuario BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_chat_conversations_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id)
);