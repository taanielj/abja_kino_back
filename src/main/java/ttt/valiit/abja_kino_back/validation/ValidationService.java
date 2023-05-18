package ttt.valiit.abja_kino_back.validation;

import ttt.valiit.abja_kino_back.domain.user.User;
import ttt.valiit.abja_kino_back.infrastructure.exeption.BusinessException;

import java.util.Optional;

public class ValidationService {
    public static void validateCorrectUserCredentials(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
            throw new BusinessException(Error.INCORRECT_CREDENTIALS.getMessage(), Error.INCORRECT_CREDENTIALS.getErrorCode());
        }
    }

    public static void validateAvailableUsername(Optional<User> userOptional) {
        if (userOptional.isPresent()) {
            throw new BusinessException(Error.USERNAME_ALREADY_EXISTS.getMessage(), Error.EMAIL_ALREADY_EXISTS.getErrorCode());
        }
    }


}
