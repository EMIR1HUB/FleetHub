package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMaintenance;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleMaintenanceRepository;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMaintenanceService {

  @Autowired
  private VehicleMaintenanceRepository vehicleMaintenanceRepository;

  @Autowired
  private VehicleRepository vehicleRepository;

  private final Integer ID_STATUS_ON_REMOTE = 411;
  private final Integer ID_STATUS_ON_AVAILABLE = 412;

  public List<VehicleMaintenance> getVehicleMaintenances() {
    return vehicleMaintenanceRepository.findAll();
  }

  public List<VehicleMaintenance> getByKeyword(String keyword) {
    return vehicleMaintenanceRepository.findByKeyword(keyword);
  }

  public void save(VehicleMaintenance vehicleMaintenance) {
    Vehicle vehicle = vehicleRepository.findById(vehicleMaintenance.getVehicleid()).orElse(null);
    if (vehicle != null) {
      vehicle.setVehiclestatusid(ID_STATUS_ON_REMOTE);
      vehicleRepository.save(vehicle);
    }
    vehicleMaintenanceRepository.save(vehicleMaintenance);
  }

  public Optional<VehicleMaintenance> getById(Long id) {
    return vehicleMaintenanceRepository.findById(id);
  }

  public void delete(Long id) {
    Vehicle vehicle = vehicleMaintenanceRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("None VehicleMaintenance with ID = " + id)).getVehicle();
    if (vehicle != null){
      vehicle.setVehiclestatusid(ID_STATUS_ON_AVAILABLE);
      vehicleRepository.save(vehicle);
    }
    vehicleMaintenanceRepository.deleteById(id);
  }

  public List<VehicleMaintenance> getVehicleWithSort(String field, String direction) {
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleMaintenanceRepository.findAll(sort);
  }
}
