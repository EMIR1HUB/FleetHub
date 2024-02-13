package com.suleimanov.vehiclecontrol.parameters.repositories;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
