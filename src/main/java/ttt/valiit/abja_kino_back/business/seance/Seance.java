package ttt.valiit.abja_kino_back.business.seance;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ttt.valiit.abja_kino_back.business.movie.Movie;
import ttt.valiit.abja_kino_back.business.room.Room;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "seance")
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Size(max = 255)
    @NotNull
    @Column(name = "language", nullable = false)
    private String language;

    @Size(max = 255)
    @NotNull
    @Column(name = "subtitles", nullable = false)
    private String subtitles;

    @Size(max = 1)
    @NotNull
    @Column(name = "status", nullable = false, length = 1)
    private String status;

}
