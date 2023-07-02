package com.astro.ai.astroai.standards.repository;

import com.astro.ai.astroai.standards.entity.Customer;
import com.astro.ai.astroai.standards.entity.PlanetDetailsCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetDetailsCredentialRepository extends CrudRepository<PlanetDetailsCredentials, Long> {

    List<PlanetDetailsCredentials> findPlanetDetailsCredentialsByActive(boolean isActive);
}
