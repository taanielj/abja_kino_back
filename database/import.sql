
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'komöödia');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'seiklus');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'draama');

INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'tava', 10.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'soodus', 8.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'klubipilet', 8.50);

INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'MArss', 10, 10);
INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Jupiter', 5, 5);

INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_CUSTOMER');

INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'admin', '123', 1, 'tuuli@abja.com');
INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'tuuli', '123', 2, 'tuuli2@abja.com');

INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Maa', 10, 9)
INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Jupiter', 5, 9)
INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Pluto', 8, 6)
