package com.bihu.kino.seance;

import com.bihu.kino.seance.model.SeanceRequest;
import com.bihu.kino.seance.model.SeanceResponse;
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
public class SeanceController {

    private final SeanceService seanceService;

    @GetMapping("/movies/{movieId}/seances")
    @ApiOperation(value = "Get all seances by movieId")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<SeanceResponse> findAllSeancesByMovieId(@PathVariable long movieId){
        return seanceService.findAllSeancesByMovieId(movieId);
    }

    @PostMapping("/api//movies/{movieId}/seances")
    @ApiOperation(value = "Create seance", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 201, message = "CREATED"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public void saveSeance(@PathVariable long movieId, @RequestBody SeanceRequest seanceRequest){
        seanceService.createSeance(movieId, seanceRequest);
    }

    @PutMapping("/api//seances/{seanceId}")
    @ApiOperation(value = "Update seance", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void updateSeance(@PathVariable long seanceId, @RequestBody SeanceRequest seanceRequest){
        seanceService.updateSeance(seanceId, seanceRequest);
    }

    @DeleteMapping("/api/seances/{seanceId}")
    @ApiOperation(value = "Delete seance", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 204, message = "DELETED"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteSeance(@PathVariable long seanceId){
        seanceService.deleteSeance(seanceId);
    }
}
