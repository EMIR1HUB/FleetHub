package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, Integer> {

  @Query(value = "SELECT vs FROM VehicleStatus vs WHERE " +
          "concat(vs.description, vs.details) LIKE %?1%")
  List<VehicleStatus> findByKeyword(String keyword);
}
