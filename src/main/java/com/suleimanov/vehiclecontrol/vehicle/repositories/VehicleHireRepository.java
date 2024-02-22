package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleHire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Integer> {

  @Query(value = "SELECT vh FROM VehicleHire vh " +
          "LEFT JOIN Client c ON vh.clientid = c.id " +
          "LEFT JOIN Vehicle v ON vh.vehicleid = v.id " +
          "WHERE c.name LIKE %?1% OR v.description LIKE %?1%")
  List<VehicleHire> findByKeyword(String keyword);
}
