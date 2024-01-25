package patterns.example.repository;

import patterns.example.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContaining(String keyword);

    List<Movie> findByTypeContaining(String keyword);

    List<Movie> findByCountryContaining(String keyword);
}
