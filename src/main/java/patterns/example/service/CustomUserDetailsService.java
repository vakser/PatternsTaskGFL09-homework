package patterns.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import patterns.example.model.Customer;
import patterns.example.repository.CustomerRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Customer with email " + email + " not found."));
        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
}
