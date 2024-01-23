package patterns.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.example.model.Movie;
import patterns.example.model.Rental;
import patterns.example.repository.MovieRepository;
import patterns.example.repository.RentalRepository;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;

    public void addRental(Rental rental) {
        Movie movie = movieRepository.findById(rental.getMovie().getId()).get();
        rental.setMovie(movie);
        rentalRepository.save(rental);
    }
}
