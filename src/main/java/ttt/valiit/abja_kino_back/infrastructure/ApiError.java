package ttt.valiit.abja_kino_back.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiError {
    private HttpStatus status;
    private Integer errorCode;
    private String message;
    private String path;
}
