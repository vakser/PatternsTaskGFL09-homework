package patterns.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patterns.example.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
