package patterns.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import patterns.example.model.Movie;
import patterns.example.model.Rental;
import patterns.example.service.MovieService;
import patterns.example.service.RentalService;

@Controller
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final MovieService movieService;

    @GetMapping("/rentMovie")
    public String addRentalForm(@RequestParam Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        model.addAttribute("movie", movie);
        Rental rental = new Rental();
        rental.setMovie(movie);
        model.addAttribute("rental", rental);
        return "rental-form";
    }

    @PostMapping("/addRental")
    public String addRental(Rental rental) {
        rentalService.addRental(rental);
        return "redirect:/movies";
    }

}
