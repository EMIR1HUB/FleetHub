INSERT INTO users (id, username, password, email)
VALUES (1, 'admin', '$2a$10$BCv2MIC2fRtgg39RSYwQE.a6717PImll5EHpPliK0rEzRDW1tDNIy', 'emir230301@gmail.com');

INSERT INTO role (id, created_by, created_date, last_modified_by, last_modified_date, description, details)
VALUES (2, 'admin', '2024-01-29 23:45:50.371000', 'admin', '2024-01-29 23:45:50.371000', 'USER',
        'Доступ к главной странице');
INSERT INTO role (id, created_by, created_date, last_modified_by, last_modified_date, description, details)
VALUES (3, 'admin', '2024-01-29 23:45:58.352000', 'admin', '2024-01-29 23:45:58.352000', 'ADMIN',
        'Доступ ко всем модулям приложения, кроме модуля безопасности');
INSERT INTO role (id, created_by, created_date, last_modified_by, last_modified_date, description, details)
VALUES (4, 'admin', '2024-01-29 23:46:21.156000', 'admin', '2024-01-29 23:46:21.156000', 'SUPER_ADMIN',
        'Имеет неограниченный доступ');
INSERT INTO role (id, created_by, created_date, last_modified_by, last_modified_date, description, details)
VALUES (5, 'admin', '2024-01-29 23:46:29.206000', 'admin', '2024-01-29 23:46:29.206000', 'HR_ADMIN',
        'Управляет HR отделом');

INSERT INTO user_role (user_id, role_id) VALUES (1, 4);
INSERT INTO user_role (user_id, role_id) VALUES (1, 3);