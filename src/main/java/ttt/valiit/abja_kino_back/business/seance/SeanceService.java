package ttt.valiit.abja_kino_back.business.seance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceDto;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceScheduleDto;
import ttt.valiit.abja_kino_back.business.movie.Movie;
import ttt.valiit.abja_kino_back.business.movie.MovieRepository;
import ttt.valiit.abja_kino_back.business.room.Room;
import ttt.valiit.abja_kino_back.business.room.RoomRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.time.Clock;
import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Status.ACTIVE;
import static ttt.valiit.abja_kino_back.infrastructure.Status.DELETED;


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

    public int[] findMovieAllFutureSeances(Integer movieId) {
        return seanceRepository.findByStartTimeGreaterThanAndMovieId(clock.instant(), movieId)
                .stream()
                .mapToInt(Seance::getId)
                .toArray();
    }

    public void createSeance(SeanceDto seanceDto) {
        Seance seance = seanceMapper.toSeance(seanceDto);

        Room room = roomRepository.findById(seanceDto.getRoomId()).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga ruumi ei leitud")
        );
        seance.setRoom(room);

        Movie movie = movieRepository.findById(seanceDto.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga filmi ei leitud")
        );

        seance.setMovie(movie);
        seance.setStatus(ACTIVE.getLetter());

        seanceRepository.save(seance);
    }


    public List<SeanceAdminSummary> getSeanceAdminSummary() {
        List<Seance> seances = seanceRepository.findAllSeancesBy(ACTIVE.getLetter());
        return seanceMapper.toAdminSummaries(seances);
    }

    public SeanceScheduleDto getSeance(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga seanssi ei leitud")
        );
        return seanceMapper.toScheduleDto(seance);
    }

    public void updateSeance(Integer id, SeanceDto seanceDto) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga seanssi ei leitud")
        );
        seanceMapper.updateSeance(seanceDto, seance);
        seanceRepository.save(seance);
    }

    public void deleteSeance(Integer id) {

        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Seance with id " + id + " not found")
        );


        seance.setStatus(DELETED.getLetter());
        seanceRepository.save(seance);

    }


}
