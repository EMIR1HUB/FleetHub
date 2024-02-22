package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

  @Query(value = "SELECT v FROM VehicleType v WHERE concat(v.description, v.details) LIKE %?1%")
  List<VehicleType> findByKeyword(String keyword);
}
