package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

  @Query(value = "SELECT v FROM Vehicle v WHERE " +
          "concat(v.name, v.description, v.vehicleStatus.description, " +
          "v.currentLocation.description, v.vehicleNumber) LIKE %?1%")
  List<Vehicle> findByKeyword(String keyword);

  @Query(value = "SELECT v FROM Vehicle v WHERE " +
          "v.vehicleStatus.description NOT LIKE 'На обслуживании' OR v.vehicleStatus.id != 411")
  List<Vehicle> findVehiclesByStatusNoRemote();
}
