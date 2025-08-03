
-- src/main/resources/sql/chat-schema.sql

CREATE TABLE IF NOT EXISTS chat_rooms (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS chat_messages (
     id SERIAL PRIMARY KEY,
     room_id INT NOT NULL,
     sender VARCHAR(255) NOT NULL,
     content TEXT NOT NULL,
     timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
