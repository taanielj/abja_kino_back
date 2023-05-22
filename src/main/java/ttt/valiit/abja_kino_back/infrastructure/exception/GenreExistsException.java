package ttt.valiit.abja_kino_back.infrastructure.exception;

public class GenreExistsException extends RuntimeException{
    public GenreExistsException(String message) {
        super(message);
    }
}
