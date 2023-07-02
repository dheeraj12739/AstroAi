package com.astro.ai.astroai.standards.repository;

import com.astro.ai.astroai.standards.entity.Customer;
import com.astro.ai.astroai.standards.entity.Dasha;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashaRepository extends CrudRepository<Dasha, Long> {

    List<Dasha> findDashaByCustomer(Customer customer);

    Dasha save(Dasha dasha);
}
