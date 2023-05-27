
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'komöödia');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'seiklus');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'draama');

INSERT INTO public.movie (id, genre_id, runtime, title, director, youtube_link, description, poster_image, status) VALUES (DEFAULT, 2, 90, 'Klassikokkutulek', 'Rene Vilbre', 'https://www.youtube.com/embed/zogCoxcs0HU', '"Klassikokkutulek" on 2016. aastal valminud Eesti mängufilm.
Film on Taanis 2011. aastal valminud komöödia "Klassefesten" korduslavastus, mis lavastati 2015. aastal ka Soomes pealkirjaga "Luokkakokous"', 'placeholder', 'A');

INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Maa', 10, 9);
INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Jupiter', 5, 9);
INSERT INTO public.room (id, name, rows, cols) VALUES (DEFAULT, 'Pluto', 8, 6)


INSERT INTO public.seance (id, room_id, movie_id, start_time, language, subtitles, status) VALUES (DEFAULT, 1, 1, '2023-06-26 13:36:36.000000', 'eesti', 'eesti', 'A');


INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'tava', 10.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'soodus', 8.00);
INSERT INTO public.ticket_type (id, name, price) VALUES (DEFAULT, 'klubipilet', 8.50);


INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (DEFAULT, 'ROLE_CUSTOMER');

INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'admin', '123', 1, 'tuuli@abja.com');
INSERT INTO public."user" (id, username, password, role_id, email) VALUES (DEFAULT, 'tuuli', '123', 2, 'tuuli2@abja.com');

