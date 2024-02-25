package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
  List<VehicleModel> getVehicleModelByVehiclemake(VehicleMake vehicleMake);

  @Query("SELECT vm FROM VehicleModel vm WHERE " +
          "concat(vm.description, vm.vehiclemake.description, vm.details) LIKE %?1%")
  List<VehicleModel> findByKeyword(String keyword);
}
