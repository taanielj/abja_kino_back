package ttt.valiit.abja_kino_back.infrastructure.exception;

public class MovieTitleExistsException extends RuntimeException {
    public MovieTitleExistsException(String errorMessage) {
        super(errorMessage);
    }
}
