package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMovementRepository extends JpaRepository<VehicleMovement, Long> {

  @Query(value = "SELECT vm FROM VehicleMovement vm WHERE " +
          "concat(vm.vehicle.name, vm.date1, vm.location1.description, " +
          "vm.date2, vm.location2.description, vm.remarks) LIKE %?1%")
  List<VehicleMovement> findByKeyword(String keyword);
}
