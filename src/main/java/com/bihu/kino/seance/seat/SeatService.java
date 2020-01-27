package com.bihu.kino.seance.seat;

import com.bihu.kino.seance.SeanceRepository;
import com.bihu.kino.seance.model.Seance;
import com.bihu.kino.seance.seat.model.Seat;
import com.bihu.kino.seance.seat.model.SeatRequest;
import com.bihu.kino.seance.seat.model.SeatResponse;
import com.bihu.kino.seance.seat.model.SeatResponseWithPersonalData;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeanceRepository seanceRepository;
    private final MapperFacade mapper;

    public List<SeatResponse> findBookedSeats(long seanceId){
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(RuntimeException::new);
        return mapper.mapAsList(seance.getSeats(), SeatResponse.class);
    }

    public List<SeatResponseWithPersonalData> findBookedSeatsWithPersonalData(long seanceId){
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(RuntimeException::new);
        return mapper.mapAsList(seance.getSeats(), SeatResponseWithPersonalData.class);
    }

    public void bookSeats(long seanceId, SeatRequest seatRequest){
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(RuntimeException::new);
        Seat seat = mapper.map(seatRequest, Seat.class);
        seance.getSeats().add(seat);
    }

    public void cancelSeatsReservation(long seanceId, SeatRequest seatRequest){
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(RuntimeException::new);
        List<Seat> seats = seance.getSeats().stream()
            .filter(s -> seatRequest.getRow() != s.getRow() && seatRequest.getSeatIndex() != s.getSeatIndex())
            .collect(Collectors.toList());
        seance.setSeats(seats);
    }
}
