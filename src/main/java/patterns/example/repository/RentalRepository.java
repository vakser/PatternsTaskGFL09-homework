package patterns.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patterns.example.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
