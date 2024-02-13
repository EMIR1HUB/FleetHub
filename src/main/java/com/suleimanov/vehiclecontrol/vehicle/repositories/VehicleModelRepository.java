package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
  List<VehicleModel> getVehicleModelByVehiclemake(VehicleMake vehicleMake);
}
