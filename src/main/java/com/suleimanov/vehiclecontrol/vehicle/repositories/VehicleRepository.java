package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

  @Query(value = "SELECT v FROM Vehicle v WHERE " +
          "concat(v.name, v.description, v.vehicleStatus.description, " +
          "v.currentLocation.description) LIKE %?1%")
  List<Vehicle> findByKeyword(String keyword);
}
