package patterns.example.service;

import jakarta.persistence.EntityNotFoundException;
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

    public Movie getMovieById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie with id " + movieId + " not found"));
    }

    public List<Movie> getFilteredMoviesByTitle(String keyword) {
        return movieRepository.findByTitleContaining(keyword);
    }

    public List<Movie> getFilteredMoviesByType(String keyword) {
        return movieRepository.findByTypeContaining(keyword);
    }

    public List<Movie> getFilteredMoviesByCountry(String keyword) {
        return movieRepository.findByCountryContaining(keyword);
    }

}
