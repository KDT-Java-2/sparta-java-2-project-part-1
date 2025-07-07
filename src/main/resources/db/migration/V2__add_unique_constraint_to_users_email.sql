ALTER TABLE users
    ADD CONSTRAINT uk_users_email UNIQUE (email);