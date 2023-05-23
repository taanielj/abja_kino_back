package ttt.valiit.abja_kino_back.business.seance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceDto;
import ttt.valiit.abja_kino_back.domain.movie.Movie;
import ttt.valiit.abja_kino_back.domain.movie.MovieRepository;
import ttt.valiit.abja_kino_back.domain.room.Room;
import ttt.valiit.abja_kino_back.domain.room.RoomRepository;
import ttt.valiit.abja_kino_back.domain.seance.Seance;
import ttt.valiit.abja_kino_back.domain.seance.SeanceRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.time.Clock;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final SeanceMapper seanceMapper;
    private final Clock clock;




    public int[] findAllFutureSeances() {
        return seanceRepository.findByStartTimeGreaterThan(clock.instant())
                .stream()
                .mapToInt(Seance::getId)
                .toArray();
    }

    public void createSeance(SeanceDto seanceDto) {
        Seance seance = seanceMapper.toSeance(seanceDto);

        Room room = roomRepository.findById(seanceDto.getRoomId()).orElseThrow(
                () -> new ResourceNotFoundException("Room with id " + seanceDto.getRoomId() + " not found")
        );
        seance.setRoom(room);

        Movie movie = movieRepository.findById(seanceDto.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException("Movie with id " + seanceDto.getMovieId() + " not found")
        );

        seance.setMovie(movie);


        seanceRepository.save(seance);
    }


    public List<SeanceAdminSummary> getSeanceAdminSummary() {
        List<Seance> seances = seanceRepository.findAll();
        return seanceMapper.toAdminSummaries(seances);
    }
}
