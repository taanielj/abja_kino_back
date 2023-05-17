INSERT INTO public."user" (id, username, password, role_id) VALUES (1, 'admin', '123', 1);
INSERT INTO public."user" (id, username, password, role_id) VALUES (2, 'tuuli', '123', 2);

INSERT INTO public.role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (2, 'ROLE_USER')