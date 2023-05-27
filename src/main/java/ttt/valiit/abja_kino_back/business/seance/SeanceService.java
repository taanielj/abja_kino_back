package ttt.valiit.abja_kino_back.business.seance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminDto;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceScheduleDto;
import ttt.valiit.abja_kino_back.business.movie.MovieRepository;
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

    public void createSeance(SeanceAdminDto seanceAdminDto) {
        Seance seance = seanceMapper.toSeance(seanceAdminDto);

        seance.setRoom(roomRepository.findById(seanceAdminDto.getRoomId()).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga ruumi ei leitud")
        ));

        seance.setMovie(movieRepository.findById(seanceAdminDto.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga filmi ei leitud")
        ));

        seance.setStatus(ACTIVE.getLetter());

        seanceRepository.save(seance);
    }


    public List<SeanceAdminSummary> getSeanceAdminSummary() {
        List<Seance> seances = seanceRepository.findAllSeancesBy(ACTIVE.getLetter());
        return seanceMapper.toAdminSummaries(seances);
    }

    public SeanceScheduleDto getSeanceScheduleDto(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga seanssi ei leitud")
        );
        return seanceMapper.toScheduleDto(seance);
    }

    public SeanceAdminDto getSeanceAdminDto(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga seanssi ei leitud")
        );
        return seanceMapper.toAdminDto(seance);
    }

    public void updateSeance(Integer id, SeanceAdminDto seanceAdminDto) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga seanssi ei leitud")
        );
        seanceMapper.updateSeanceFromDto(seanceAdminDto, seance);

        seance.setRoom(roomRepository.findById(seanceAdminDto.getRoomId()).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga ruumi ei leitud")
        ));

        seance.setMovie(movieRepository.findById(seanceAdminDto.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException("Selle id-ga filmi ei leitud")
        ));


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
