package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.VehicleMaintenance;
import com.suleimanov.vehiclecontrol.Repositories.VehicleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMaintenanceService {

  @Autowired
  private VehicleMaintenanceRepository vehicleMaintenanceRepository;

  public List<VehicleMaintenance> getVehicleMaintenances(){
    return vehicleMaintenanceRepository.findAll();
  }

  public void save(VehicleMaintenance vehicleMaintenance){
    vehicleMaintenanceRepository.save(vehicleMaintenance);
  }

  public Optional<VehicleMaintenance> findById(Integer id){
    return vehicleMaintenanceRepository.findById(id);
  }

  public void delete(Integer id) {
    vehicleMaintenanceRepository.deleteById(id);
  }
}
