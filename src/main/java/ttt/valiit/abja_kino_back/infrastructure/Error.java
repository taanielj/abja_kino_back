package ttt.valiit.abja_kino_back.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum Error {
    GENRE_NOT_FOUND("Žanri ei leitud"),
    MOVIE_NOT_FOUND("Filmi ei leitud"),
    ROOM_NOT_FOUND("Saali ei leitud"),
    SEANCE_NOT_FOUND("Seanssi ei leitud"),
    SEAT_NOT_FOUND("Kohta ei leitud"),
    TICKET_TYPE_NOT_FOUND("Piletitüüpi ei leitud"),
    TICKET_NOT_FOUND("Piletit ei leitud"),
    USER_NOT_FOUND("Kasutajat ei leitud"),
    GENRE_EXISTS("Žanr on juba olemas"),
    MOVIE_EXISTS("Film on juba olemas"),
    ROOM_EXISTS("Saal on juba olemas"),
    SEANCE_EXISTS("Seanss on juba olemas"),
    SEAT_EXISTS("Koht on juba olemas"),
    TICKET_TYPE_EXISTS("Piletitüüp on juba olemas"),
    TICKET_EXISTS("Pilet on juba olemas"),
    USER_EXISTS("Kasutaja on juba olemas"),
    DATABASE_NAME_CONFLICT(" nimega on juba olemas"),
    DATABASE_NAME_MUST_NOT_BE_EMPTY("Nimi ei tohi olla tühi"),
    DATABASE_CONSTRAINT("Andmebaasi tervikluse rikkumine"),
    RESOURCE_NOT_FOUND("Ressurssi ei leitud"),
    ACCESS_DENIED("Ligipääs keelatud"),
    ROOM_CANNOT_BE_DELETED("Saali ei saa kustutada, sest selles on seansse"),
    ROOM_SEATS_CANNOT_BE_EDITED("Saali kohti ei saa muuta, sest selles on seansse"),
    INVALID_CREDENTIALS("Vale kasutajanimi või parool");
    private final String message;


}
