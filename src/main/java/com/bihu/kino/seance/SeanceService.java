package com.bihu.kino.seance;

import com.bihu.kino.movie.MovieRepository;
import com.bihu.kino.movie.model.Movie;
import com.bihu.kino.seance.model.Seance;
import com.bihu.kino.seance.model.SeanceRequest;
import com.bihu.kino.seance.model.SeanceResponse;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SeanceService {

    private final MovieRepository movieRepository;
    private final SeanceRepository seanceRepository;
    private final MapperFacade mapper;

    public List<SeanceResponse> findAllSeancesByMovieId(long movieId){
        return movieRepository.findById(movieId)
            .map(m -> mapper.mapAsList(m.getSeances(), SeanceResponse.class))
            .orElseThrow(RuntimeException::new);
    }

    public void createSeance(long movieId, SeanceRequest seanceRequest){
        Movie movie = movieRepository.findById(movieId).orElseThrow(RuntimeException::new);
        Seance seance = mapper.map(seanceRequest, Seance.class);
        movie.getSeances().add(seance);
    }

    public void updateSeance(long seanceId, SeanceRequest seanceRequest){
        Seance seance = seanceRepository.findById(seanceId).orElseThrow(RuntimeException::new);
        seance.setLocalDate(seanceRequest.getLocalDate());
    }

    public void deleteSeance(long seanceId){
        seanceRepository.deleteById(seanceId);
    }
}
