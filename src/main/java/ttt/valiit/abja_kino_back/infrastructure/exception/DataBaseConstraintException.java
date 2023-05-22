package ttt.valiit.abja_kino_back.infrastructure.exception;

public class DataBaseConstraintException extends RuntimeException{
    public DataBaseConstraintException (String message) {
        super(message);
    }
}
