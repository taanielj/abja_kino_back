package ttt.valiit.abja_kino_back.infrastructure.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String errorMessage) {
        super(errorMessage);
    }
}
