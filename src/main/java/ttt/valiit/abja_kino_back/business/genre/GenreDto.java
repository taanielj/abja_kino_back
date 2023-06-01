package ttt.valiit.abja_kino_back.business.genre;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Genre}
 */
@Value
public class GenreDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 255)
    String name;
}
