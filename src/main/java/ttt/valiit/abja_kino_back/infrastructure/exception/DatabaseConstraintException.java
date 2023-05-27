package ttt.valiit.abja_kino_back.infrastructure.exception;

public class DatabaseConstraintException extends RuntimeException {
    public DatabaseConstraintException(String errorMessage) {
        super(errorMessage);
    }
}

