package com.reservation.authservice.repository;

import com.reservation.authservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByUserName(String userName);
}