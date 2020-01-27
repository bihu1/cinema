package com.bihu.kino.movie;

import com.bihu.kino.movie.model.MovieRequest;
import com.bihu.kino.movie.model.MovieResponse;
import com.bihu.kino.movie.model.MovieResponseWithId;
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
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    @ApiOperation(value = "Get all movies")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<MovieResponseWithId> findAllMovies(){
        return movieService.findAllMovies();
    }

    @GetMapping("/movies/{movieId}")
    @ApiOperation(value = "Get movie by id")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    public MovieResponse findMovieById(@PathVariable long movieId){
        return movieService.findMovieById(movieId);
    }

    @PostMapping("/api/movies")
    @ApiOperation(value = "Create movie", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 201, message = "Created"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("ROLE_ADMIN")
    public void saveMovie(@RequestBody MovieRequest movieRequest){
        movieService.saveMovie(movieRequest);
    }

    @PutMapping("/api/movies/{movieId}")
    @ApiOperation(value = "Update movie", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
    })
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void updateMovie(@PathVariable long movieId, @RequestBody MovieRequest movieRequest){
        movieService.updateMovie(movieId, movieRequest);
    }

    @DeleteMapping("/api/movies/{movieId}")
    @ApiOperation(value = "Delete movie", authorizations = {@Authorization("Bearer <oAuth2>")})
    @ApiResponses({
        @ApiResponse(code = 204, message = "DELETED"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured("ROLE_ADMIN")
    public void deleteMovie(@PathVariable long movieId){
        movieService.deleteMovie(movieId);
    }
}