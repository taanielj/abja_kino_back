INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_CUSTOMER');

INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'admin', '123', 1, 'tuuli@abja.com');
INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'tuuli', '123', 2, 'tuuli2@abja.com');

