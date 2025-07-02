package com.customer_service.customer_service.CustomerController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.customer_service.customer_service.CustomerEntity.Customer;
import com.customer_service.customer_service.CustomerRepository.CustomerRepository;
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Customer> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
        Customer saved = repo.save(c);

        // Post to sales-order service
        try {
            restTemplate.postForObject(
                "http://sales-order/customer", // Use Eureka service name
                saved,
                Void.class
            );
            System.out.println("Customer synced to Sales Order Service");
        } catch (Exception e) {
            System.out.println("Failed to sync customer: " + e.getMessage());
        }

        return ResponseEntity.ok(saved);
    }
}
