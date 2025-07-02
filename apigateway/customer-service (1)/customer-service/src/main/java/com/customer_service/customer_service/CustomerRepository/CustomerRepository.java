package com.customer_service.customer_service.CustomerRepository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.customer_service.customer_service.CustomerEntity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
