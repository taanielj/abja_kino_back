package ttt.valiit.abja_kino_back.validation;

import ttt.valiit.abja_kino_back.domain.user.User;

import java.util.Optional;

public class ValidationService {
    public static void validateCorrectUserCredentials(Optional<User> userOptional) {
        if (userOptional.isEmpty()) {
//            throw new BusinessException(INCORRECT_CREDENTIALS.getMessage(), INCORRECT_CREDENTIALS.getErrorCode());
        }
    }
}
