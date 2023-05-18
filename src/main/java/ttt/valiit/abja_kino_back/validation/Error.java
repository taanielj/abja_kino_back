package ttt.valiit.abja_kino_back.validation;

import lombok.Getter;

@Getter
public enum Error {
    INCORRECT_CREDENTIALS("Vale kasutajanimi või parool", 111),
    USERNAME_ALREADY_EXISTS("Sellise kasutajanimega kasutaja on juba süsteemis olemas", 222),
    EMAIL_ALREADY_EXISTS("Sellise e-mailiga kasutaja on juba süsteemis olemas", 333);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}

