
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'komöödia');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'seiklus');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'draama');

INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'tava', 10.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'soodus', 8.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'klubipilet', 8.50);


INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ADMIN');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'CUSTOMER');

INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'admin', '$2a$13$c.GhEPXW3DYJW4JNb.flM.o5HNqL.Ff0XbHVVVg0GnTnZcDhOc7s2', 1, 'tuuli@abja.com');
INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'tuuli', '$2a$13$c.GhEPXW3DYJW4JNb.flM.o5HNqL.Ff0XbHVVVg0GnTnZcDhOc7s2', 2, 'tuuli2@abja.com');

