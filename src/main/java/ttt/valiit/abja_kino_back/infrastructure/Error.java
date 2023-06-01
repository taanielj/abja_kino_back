package ttt.valiit.abja_kino_back.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Error {

    ACCESS_DENIED("Ligipääs keelatud"),
    DATABASE_CONSTRAINT("Andmebaasi tervikluse rikkumine"),
    DATABASE_NAME_CONFLICT(" nimega on juba olemas"),
    DATABASE_NAME_MUST_NOT_BE_EMPTY("Nimi ei tohi olla tühi"),

    GENRE_EXISTS("Žanr on juba olemas"),
    GENRE_HAS_MOVIES("Žanr on filmiga seotud"),
    GENRE_NOT_FOUND("Žanri ei leitud"),

    INVALID_CREDENTIALS("Vale kasutajanimi või parool"),

    MOVIE_EXISTS("Film on juba olemas"),
    MOVIE_NOT_FOUND("Filmi ei leitud"),
    MOVIE_HAS_SEANCES("Film on seansiga seotud"),

    RESOURCE_NOT_FOUND("Ressurssi ei leitud"),

    ROOM_CANNOT_BE_DELETED("Saali ei saa kustutada, sest selles on seansse"),
    ROOM_EXISTS("Saal on juba olemas"),
    ROOM_NOT_FOUND("Saali ei leitud"),
    ROOM_SEATS_CANNOT_BE_EDITED("Saali kohti ei saa muuta, sest selles on seansse"),

    SEANCE_EXISTS("Seanss on juba olemas"),
    SEANCE_HAS_ACTIVE_TICKETS("Seansil on aktiivseid pileteid"),
    SEANCE_NOT_FOUND("Seanssi ei leitud"),
    SEAT_EXISTS("Koht on juba olemas"),
    SEAT_NOT_FOUND("Kohta ei leitud"),

    TICKET_EXISTS("Pilet on juba olemas"),
    TICKET_NOT_FOUND("Piletit ei leitud"),
    TICKET_TYPE_EXISTS("Piletitüüp on juba olemas"),
    TICKET_TYPE_NOT_FOUND("Piletitüüpi ei leitud"),
    TICKETS_EXIST_WITH_THIS_TICKET_TYPE("Selle piletitüübiga on juba pileteid müüdud"),

    USER_EXISTS("Kasutaja on juba olemas"),
    USER_NOT_FOUND("Kasutajat ei leitud");

    private final String message;


}
