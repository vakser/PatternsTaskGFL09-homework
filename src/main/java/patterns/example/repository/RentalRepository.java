package patterns.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import patterns.example.model.Rental;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query(value = "select * from rentals as r where r.user_id=:user_id", nativeQuery = true)
    List<Rental> findRentalsForCustomer(@Param("user_id") Long customerId);
}
