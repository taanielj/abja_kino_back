
INSERT INTO public.genre (id, name) VALUES (1, 'komöödia');
INSERT INTO public.genre (id, name) VALUES (2, 'seiklus');
INSERT INTO public.genre (id, name) VALUES (3, 'draama');

INSERT INTO public.ticket_type (id, name, price) VALUES (1, 'tava', 10.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (2, 'soodus', 8.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (3, 'klubipilet', 8.50);

INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_CUSTOMER');

INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'admin', '123', 1, 'tuuli@abja.com');
INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'tuuli', '123', 2, 'tuuli2@abja.com');


