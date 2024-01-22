package patterns.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.example.model.Movie;
import patterns.example.repository.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public List<patterns.example.model.Movie> getMovies() {
        return movieRepository.findAll();
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
