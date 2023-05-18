package ttt.valiit.abja_kino_back.infrastructure.exeption;

public class BusinessException extends RuntimeException {
    private final String message;
    private final Integer errorCode;
    public BusinessException(String message, Integer errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}
