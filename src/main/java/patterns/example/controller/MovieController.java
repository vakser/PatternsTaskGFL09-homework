package patterns.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import patterns.example.dto.MovieFilterDto;
import patterns.example.model.Movie;
import patterns.example.service.MovieService;
import patterns.example.util.MoviePDFExporter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @Value("${types}")
    private List<String> types;
    @Value("${countries}")
    private List<String> countries;

    @GetMapping("/movies")
    public String showMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        model.addAttribute("filter", new MovieFilterDto());
        return "movies";
    }

    @GetMapping("/movies/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        List<Movie> movies = movieService.getMovies();
        MoviePDFExporter exporter = new MoviePDFExporter();
        exporter.export(movies, response);
    }

    @GetMapping("/createMovie")
    public String addMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("types", types);
        model.addAttribute("countries", countries);
        return "movie-form";
    }

    @PostMapping("/addMovie")
    public String addMovie(Movie movie) {
        movieService.addMovie(movie);
        return "redirect:/movies";
    }

    @GetMapping("/filterMovies")
    public String filterMovies(@ModelAttribute("filter") MovieFilterDto movieFilterDto, Model model) {
        String searchBy = movieFilterDto.getSearchBy();
        List<Movie> movies = new ArrayList<>();
        if ("type".equals(searchBy)) {
            movies = movieService.getFilteredMoviesByType(movieFilterDto.getKeyword());
        } else if ("country".equals(searchBy)) {
            movies = movieService.getFilteredMoviesByCountry(movieFilterDto.getKeyword());
        } else if ("title".equals(searchBy)) {
            movies = movieService.getFilteredMoviesByTitle(movieFilterDto.getKeyword());
        }
        model.addAttribute("movies", movies);
        return "movies";
    }
}
