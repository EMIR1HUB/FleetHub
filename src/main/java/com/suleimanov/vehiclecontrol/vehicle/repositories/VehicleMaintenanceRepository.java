package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Long> {

  @Query(value = "SELECT vm FROM VehicleMaintenance vm WHERE " +
          "concat(vm.vehicle.name, vm.supplier.name, vm.price, vm.remarks) LIKE %?1%")
  List<VehicleMaintenance> findByKeyword(String keyword);
}
