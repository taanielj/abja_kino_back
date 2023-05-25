package ttt.valiit.abja_kino_back.infrastructure;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ApiError {
    private HttpStatus status;
    private Integer errorCode;
    private String message;
    private String path;
}
