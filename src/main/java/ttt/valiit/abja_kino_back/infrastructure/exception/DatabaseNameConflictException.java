package ttt.valiit.abja_kino_back.infrastructure.exception;

public class DatabaseNameConflictException extends RuntimeException {
    public DatabaseNameConflictException(String errorMessage) {
        super(errorMessage);
    }
}
