INSERT INTO users (id, email, password, username)
VALUES (1, 'emir230301@gmail.com', '$2a$10$BCv2MIC2fRtgg39RSYwQE.a6717PImll5EHpPliK0rEzRDW1tDNIy', 'admin');

INSERT INTO role (created_date, id, last_modified_date, created_by, description, details, last_modified_by)
VALUES ('2024-01-29 23:45:50.371000', 2, '2024-01-29 23:45:50.371000', 'admin', 'USER', 'Доступ к главной странице', 'admin'),
       ('2024-01-29 23:45:58.352000', 3, '2024-01-29 23:45:58.352000', 'admin', 'ADMIN', 'Доступ ко всем модулям приложения, кроме модуля безопасности', 'admin'),
       ('2024-01-29 23:46:21.156000', 4, '2024-01-29 23:46:21.156000', 'admin', 'SUPER_ADMIN', 'Имеет неограниченный доступ', 'admin'),
       ('2024-01-29 23:46:29.206000', 5, '2024-01-29 23:46:29.206000', 'admin', 'HR_ADMIN', 'Управляет HR отделом', 'admin');

INSERT INTO user_role (role_id, user_id)
VALUES (4, 1);
INSERT INTO user_role (role_id, user_id)
VALUES (3, 1);