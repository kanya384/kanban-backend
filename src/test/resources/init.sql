insert into users (id, email, password_digest, created_at, updated_at)
values (1, 'test01@mail.ru', '$2a$10$1p9qE.a2ID4uYrbsQWCkGOmVL7lgyhN/aV2jjk1vRR5bArhCinrYG', '2024-10-24', '2024-10-24'),
(2, 'test02@mail.ru', '$2a$10$1p9qE.a2ID4uYrbsQWCkGOmVL7lgyhN/aV2jjk1vRR5bArhCinrYG', '2024-10-24', '2024-10-24');

insert into kanban (id, title, owner_id, created_at, updated_at)
values (1, 'kanban', 1, '2024-10-24', '2024-10-24');

insert into status (id, title, kanban_id, created_at, updated_at)
values (1, 'status', 1, '2024-10-24', '2024-10-24');

insert into task (id, title, content, assignee_id, author_id, status_id, created_at, updated_at)
values (1, 'task', 'content', 2, 1, 1, '2024-10-24', '2024-10-24');