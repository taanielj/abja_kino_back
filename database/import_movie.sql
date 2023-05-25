
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'komöödia');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'seiklus');
INSERT INTO public.genre (id, name) VALUES (DEFAULT, 'draama');

INSERT INTO public.movie (id, genre_id, runtime, title, director, youtube_link, description, poster_image, status) VALUES (DEFAULT, 2, 90, 'Klassikokkutulek', 'Rene Vilbre', 'https://www.youtube.com/embed/zogCoxcs0HU', '"Klassikokkutulek" on 2016. aastal valminud Eesti mängufilm.
Film on Taanis 2011. aastal valminud komöödia "Klassefesten" korduslavastus, mis lavastati 2015. aastal ka Soomes pealkirjaga "Luokkakokous"', '', 'A');
