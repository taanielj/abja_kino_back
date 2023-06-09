package ttt.valiit.abja_kino_back.business.seance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.movie.MovieRepository;
import ttt.valiit.abja_kino_back.business.room.RoomRepository;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminDto;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceAdminSummary;
import ttt.valiit.abja_kino_back.business.seance.dto.SeanceScheduleDto;
import ttt.valiit.abja_kino_back.business.ticket.TicketRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseConstraintException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;

import static ttt.valiit.abja_kino_back.infrastructure.Error.*;
import static ttt.valiit.abja_kino_back.infrastructure.Status.ACTIVE;
import static ttt.valiit.abja_kino_back.infrastructure.Status.DELETED;


@Service
@RequiredArgsConstructor
public class SeanceService {

    private final SeanceRepository seanceRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final TicketRepository ticketRepository;
    private final SeanceMapper seanceMapper;
    private final Clock clock;

    public List<SeanceScheduleDto> findAllFutureSeances() {
        List<Seance> seances = seanceRepository.findByStartTimeGreaterThan(clock.instant());
        return getSeanceScheduleDtos(seances);

    }

    public List<SeanceScheduleDto> findMovieAllFutureSeances(Integer movieId) {
        List<Seance> seances =  seanceRepository.findByStartTimeGreaterThanAndMovieId(clock.instant(), movieId);
        return getSeanceScheduleDtos(seances);

    }

    public void createSeance(SeanceAdminDto seanceAdminDto) {
        Seance seance = seanceMapper.toSeance(seanceAdminDto);

        seance.setRoom(roomRepository.findById(seanceAdminDto.getRoomId()).orElseThrow(
                () -> new ResourceNotFoundException(ROOM_NOT_FOUND.getMessage())));
        seance.setMovie(movieRepository.findById(seanceAdminDto.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException(MOVIE_NOT_FOUND.getMessage())));

        seance.setStatus(ACTIVE.getLetter());
        seanceRepository.save(seance);
    }


    public List<SeanceAdminSummary> getSeanceAdminSummary() {
        List<Seance> seances = seanceRepository.findAllSeancesBy(ACTIVE.getLetter());
        List<SeanceAdminSummary> seanceAdminSummaries = new ArrayList<>();

        for (Seance seance : seances) {
            Integer totalSeats = seance.getRoom().getCols() * seance.getRoom().getRows();
            Integer bookedSeats = ticketRepository.countBySeance(seance.getId());
            SeanceAdminSummary seanceAdminSummary = seanceMapper.toAdminSummary(seance);
            seanceAdminSummary.setTotalSeats(totalSeats);
            seanceAdminSummary.setAvailableSeats(totalSeats - bookedSeats);
            seanceAdminSummaries.add(seanceAdminSummary);
        }

        return seanceAdminSummaries;
    }

    public SeanceScheduleDto getSeanceScheduleDto(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SEANCE_NOT_FOUND.getMessage())
        );

        Integer totalSeats = seance.getRoom().getCols() * seance.getRoom().getRows();
        Integer bookedSeats = ticketRepository.countBySeance(seance.getId());
        SeanceScheduleDto seanceScheduleDto = seanceMapper.toScheduleDto(seance);

        seanceScheduleDto.setTotalSeats(totalSeats);
        seanceScheduleDto.setAvailableSeats(totalSeats - bookedSeats);


        return seanceScheduleDto;
    }

    public SeanceAdminDto getSeanceAdminDto(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SEANCE_NOT_FOUND.getMessage())
        );

        return seanceMapper.toAdminDto(seance);
    }

    public void updateSeance(Integer id, SeanceAdminDto seanceAdminDto) {

        Seance seance = getSeanceAndValidateChangeable(id);
        seanceMapper.updateSeanceFromDto(seanceAdminDto, seance);
        seance.setRoom(roomRepository.findById(seanceAdminDto.getRoomId()).orElseThrow(
                () -> new ResourceNotFoundException(ROOM_NOT_FOUND.getMessage())));

        seance.setMovie(movieRepository.findById(seanceAdminDto.getMovieId()).orElseThrow(
                () -> new ResourceNotFoundException(MOVIE_NOT_FOUND.getMessage())));

        seanceRepository.save(seance);
    }

    public void deleteSeance(Integer id) {
        Seance seance = getSeanceAndValidateChangeable(id);
        seance.setStatus(DELETED.getLetter());
        seanceRepository.save(seance);
    }

    private List<SeanceScheduleDto> getSeanceScheduleDtos(List<Seance> seances) {
        List<SeanceScheduleDto> seanceScheduleDtos = new ArrayList<>();

        for(Seance seance : seances) {
            SeanceScheduleDto seanceScheduleDto = seanceMapper.toScheduleDto(seance);
            int totalSeats = seance.getRoom().getRows() * seance.getRoom().getCols();
            int bookedSeats = ticketRepository.countBySeance(seance.getId());
            seanceScheduleDto.setTotalSeats(totalSeats);
            seanceScheduleDto.setAvailableSeats(totalSeats - bookedSeats);
            seanceScheduleDtos.add(seanceScheduleDto);
        }
        return seanceScheduleDtos;
    }

    private Seance getSeanceAndValidateChangeable(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(SEANCE_NOT_FOUND.getMessage())
        );

        if (ticketRepository.existsBySeanceId(id) && clock.instant().isBefore(seance.getStartTime())) {
            throw new DatabaseConstraintException(SEANCE_HAS_ACTIVE_TICKETS.getMessage());
        }
        return seance;
    }


}
