package com.astro.ai.astroai.standards.repository;

import com.astro.ai.astroai.standards.entity.Planet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, Long> {

    Planet findByPlanetId(Long planetId);

    List<Planet> findByCustomersCustomerId(Long customerId);

    Planet save(Planet planet);
}
