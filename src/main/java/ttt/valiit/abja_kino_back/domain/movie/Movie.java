package ttt.valiit.abja_kino_back.domain.movie;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "/api/v1/movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "runtime", nullable = false)
    private Integer runtime;

    @Size(max = 255)
    @NotNull
    @Column(name = "director", nullable = false)
    private String director;

    @Size(max = 255)
    @NotNull
    @Column(name = "genre", nullable = false)
    private String genre;

    @NotNull
    @Column(name = "poster", nullable = false)
    private byte[] poster;

}