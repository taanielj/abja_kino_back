package ttt.valiit.abja_kino_back.infrastructure.exception;

public class UsernameExistsException extends RuntimeException{
    public UsernameExistsException(String errorMessage) {
        super(errorMessage);
    }

}
