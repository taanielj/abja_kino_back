package ttt.valiit.abja_kino_back.business.room;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ttt.valiit.abja_kino_back.business.room.dto.RoomDto;
import ttt.valiit.abja_kino_back.business.room.dto.RoomSeanceDto;
import ttt.valiit.abja_kino_back.business.room.dto.SeatDto;
import ttt.valiit.abja_kino_back.business.seance.Seance;
import ttt.valiit.abja_kino_back.business.seance.SeanceRepository;
import ttt.valiit.abja_kino_back.business.ticket.Ticket;
import ttt.valiit.abja_kino_back.business.ticket.TicketRepository;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseConstraintException;
import ttt.valiit.abja_kino_back.infrastructure.exception.DatabaseNameConflictException;
import ttt.valiit.abja_kino_back.infrastructure.exception.ResourceNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final SeanceRepository seanceRepository;
    private final TicketRepository ticketRepository;
    private final RoomMapper roomMapper;


    @Transactional
    public void addRoom(RoomDto roomDto) {
        Room room = roomMapper.toRoom(roomDto);
        validateRoomName(room.getName());
        roomRepository.save(room);
        updateSeats(room);

        roomRepository.save(room);
    }

    private void updateSeats(Room room) {
        room.getSeats().clear();


        if (seanceRepository.existsByRoomId(room.getId())) {
            throw new DatabaseConstraintException("Saali ei saa muuta, sest selles on seansse");
        }


        for (int i = 0; i < room.getRows(); i++) {
            for (int j = 0; j < room.getCols(); j++) {
                Seat seat = new Seat();
                seat.setRow(i);
                seat.setCol(j);
                seat.setRoom(room);
                room.getSeats().add(seat);
            }
        }
    }

    void deleteRoom(int roomId) {
        //check if room has seances
        if (seanceRepository.existsByRoomId(roomId)) {
            throw new DatabaseConstraintException("Saali ei saa kustutada, sest selles on seansse");
        }
        //delete room, seats will be deleted as well because of the @OneToMany(mappedBy = "room"
        //cascade = CascadeType.ALL, orphanRemoval = true) annotation on the room entity
        roomRepository.deleteById(roomId);
    }

    public List<RoomDto> getAllRooms() {
        return roomMapper.toRoomDtos(roomRepository.findAllAlphabetic());
    }


    private void validateRoomName(String roomName) {
        if (roomName == null || roomName.isEmpty()) {
            throw new DatabaseConstraintException("Ruumi nimi ei tohi olla tühi");
        }
        if (roomRepository.existsBy(roomName)) {
            throw new DatabaseNameConflictException("Selle nimega saal on juba olemas");
        }
    }

    private void validateRoomName(String newName, String oldName) {
        if (newName == null || newName.isEmpty()) {
            throw new DatabaseConstraintException("Ruumi nimi ei tohi olla tühi");
        }
        if (!newName.equals(oldName) && roomRepository.existsBy(newName)) {
            throw new DatabaseNameConflictException("Selle nimega saal on juba olemas");
        }
    }

    public void updateRoom(Integer id, RoomDto roomDto) {
        Room existingRoom = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        validateRoomName(roomDto.getName(), existingRoom.getName());
        existingRoom.setName(roomDto.getName());
        updateSeats(existingRoom);
        roomRepository.save(existingRoom);
    }


    public RoomDto getRoom(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return roomMapper.toRoomDto(room);
    }


    public RoomSeanceDto getRoomSeance(Integer seanceId) {
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(
                () -> new ResourceNotFoundException("Seance not found")
        );
        Room room = seance.getRoom();

        List<Ticket> tickets = ticketRepository.findSeanceTickets(seanceId);
        List<SeatDto> seatDTOs = createSeatDtos(room.getSeats(), tickets);

        return createRoomSeanceDto(room, seatDTOs);
    }

    private List<SeatDto> createSeatDtos(List<Seat> seats, List<Ticket> tickets) {
        return seats.stream()
                .map(seat -> {
                    SeatDto seatDto = new SeatDto();
                    seatDto.setRow(seat.getRow());
                    seatDto.setCol(seat.getCol());
                    seatDto.setAvailable(!isSeatBooked(seat, tickets));
                    return seatDto;
                }).toList();
    }

    private boolean isSeatBooked(Seat seat, List<Ticket> tickets) {
        return tickets.stream().anyMatch(ticket -> ticket.getSeat().getId().equals(seat.getId()));
    }

    private RoomSeanceDto createRoomSeanceDto(Room room, List<SeatDto> seatDTOs) {
        RoomSeanceDto roomSeanceDto = new RoomSeanceDto();
        roomSeanceDto.setSeats(seatDTOs);
        roomSeanceDto.setRows(room.getRows());
        roomSeanceDto.setCols(room.getCols());
        roomSeanceDto.setRoomName(room.getName());
        return roomSeanceDto;
    }
}
