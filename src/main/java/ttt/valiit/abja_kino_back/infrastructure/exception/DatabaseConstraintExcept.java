package ttt.valiit.abja_kino_back.infrastructure.exception;

public class DatabaseConstraintExcept extends RuntimeException {
    public DatabaseConstraintExcept(String errorMessage) {
        super(errorMessage);
    }
}

