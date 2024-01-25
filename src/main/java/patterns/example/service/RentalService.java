package patterns.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.example.model.Customer;
import patterns.example.model.Movie;
import patterns.example.model.Rental;
import patterns.example.repository.MovieRepository;
import patterns.example.repository.RentalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;
    private final CustomerService customerService;

    public void addRental(Rental rental) {
        Movie movie = movieRepository.findById(rental.getMovie().getId()).get();
        rental.setMovie(movie);
        Customer customer = customerService.getLoggedInCustomer();
        List<Rental> rentals = customer.getRentals();
        rentals.add(rental);
        customer.setRentals(rentals);
        rentalRepository.save(rental);
    }

    public List<Rental> getRentalsByCustomerId(Long customerId) {
        return rentalRepository.findRentalsForCustomer(customerId);
    }
}
