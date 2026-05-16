ALTER TABLE chat_message
DROP COLUMN conversation_id;

ALTER TABLE chat_message
ADD COLUMN id_conversation CHAR(36) NOT NULL;

ALTER TABLE chat_message
ADD CONSTRAINT fk_chat_message_conversation
FOREIGN KEY (id_conversation)
REFERENCES chat_conversations(id);