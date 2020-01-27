package com.bihu.kino.seance.seat;

import com.bihu.kino.seance.seat.model.SeatRequest;
import com.bihu.kino.seance.seat.model.SeatResponse;
import com.bihu.kino.seance.seat.model.SeatResponseWithPersonalData;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/seances/{seanceId}/seats")
    @ApiOperation(value = "Get all seats")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<SeatResponse> findBookedSeats(@PathVariable long seanceId){
        return seatService.findBookedSeats(seanceId);
    }

    @GetMapping("/api/seances/{seanceId}/seats")
    @ApiOperation(value = "Get all seats", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public List<SeatResponseWithPersonalData> findBookedSeatsForAdmin(@PathVariable long seanceId){
        return seatService.findBookedSeatsWithPersonalData(seanceId);
    }

    @PostMapping("/seances/{seanceId}/seats")
    @ApiOperation(value = "Reserve seats")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public void bookSeats(@PathVariable long seanceId, @RequestBody SeatRequest seatRequest){
        seatService.bookSeats(seanceId, seatRequest);
    }

    @DeleteMapping("/api/seances/{seanceId}/seats")
    @ApiOperation(value = "Cancel seat reservation", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 200, message = "DELETED"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void cancelSeatsReservation(@PathVariable long seanceId, @RequestBody SeatRequest seatRequest){
        seatService.cancelSeatsReservation(seanceId, seatRequest);
    }
}