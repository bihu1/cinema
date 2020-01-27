package com.bihu.kino.movie;

import com.bihu.kino.movie.model.Movie;
import com.bihu.kino.movie.model.MovieRequest;
import com.bihu.kino.movie.model.MovieResponse;
import com.bihu.kino.movie.model.MovieResponseWithId;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MapperFacade mapper;

    public List<MovieResponseWithId> findAllMovies(){
        return mapper.mapAsList(movieRepository.findAll(), MovieResponseWithId.class);
    }

    public MovieResponse findMovieById(long movieId){
        Movie movie = movieRepository.findById(movieId).orElseThrow(RuntimeException::new);
        return mapper.map(movie, MovieResponse.class);
    }

    public void saveMovie(MovieRequest movieRequest){
        Movie movie = mapper.map(movieRequest, Movie.class);
        movieRepository.save(movie);
    }

    public void updateMovie(long movieId, MovieRequest movieRequest){
        Movie movie = mapper.map(movieRequest, Movie.class);
        movie.setId(movieId);
        movieRepository.save(movie);
    }

    public void deleteMovie(long movieId){
        movieRepository.deleteById(movieId);
    }
}
