package com.suleimanov.vehiclecontrol.parameters.repositories;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> getRegionsByCountry(Country country);
}
