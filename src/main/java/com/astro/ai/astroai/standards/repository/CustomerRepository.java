package com.astro.ai.astroai.standards.repository;

import com.astro.ai.astroai.standards.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByCustomerId(Long customerId);
    Customer findByAstroAppCustomerId(String astroAppCustomerId);

    Customer save(Customer customer);

}
