package com.suleimanov.vehiclecontrol.parameters.repositories;

import com.suleimanov.vehiclecontrol.parameters.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
