package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Integer> {

}
