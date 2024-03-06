package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleHire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Integer> {

  @Query(value = "SELECT vh FROM VehicleHire vh WHERE " +
          "concat(vh.vehicle.name, vh.client.name) LIKE %?1%")
  List<VehicleHire> findByKeyword(String keyword);
}
