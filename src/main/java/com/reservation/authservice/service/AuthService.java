package com.reservation.authservice.service;
import com.reservation.authservice.model.Customer;
import com.reservation.authservice.repository.CustomerRepository;
import com.reservation.authservice.util.BusResponse;
import com.reservation.authservice.util.RequestStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public AuthService(CustomerRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public BusResponse registerCustomer(Customer customer) {
        Optional<Customer> oldCustomer = repository.findByUserName(customer.getUserName());
        if (oldCustomer.isPresent())
            throw new com.reservation.bookingservice.exception.FieldException("username already taken");

        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        repository.save(customer);
        return new BusResponse(RequestStatus.SUCCESS, "Registration successful");
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
