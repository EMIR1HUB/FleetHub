package com.suleimanov.vehiclecontrol.Repositories;

import com.suleimanov.vehiclecontrol.Models.VehicleMake;
import com.suleimanov.vehiclecontrol.Models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
  List<VehicleModel> getVehicleModelByVehiclemake(VehicleMake vehicleMake);
}
