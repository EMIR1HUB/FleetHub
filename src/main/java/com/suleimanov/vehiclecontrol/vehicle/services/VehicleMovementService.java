package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMovement;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleMovementRepository;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMovementService {

  @Autowired
  private VehicleMovementRepository vehicleMovementRepository;

  @Autowired private VehicleRepository vehicleRepository;

  private final Integer ID_STATUS_ON_BUSY = 415;
  private final Integer ID_STATUS_ON_AVAILABLE = 414;

  public List<VehicleMovement> getVehicleMovements(){
    return vehicleMovementRepository.findAll();
  }

  public List<VehicleMovement> getByKeyword(String keyword) {
    return vehicleMovementRepository.findByKeyword(keyword);
  }

  public void save(VehicleMovement vehicleMovement){
    Vehicle vehicle = vehicleRepository.findById(vehicleMovement.getVehicleid()).orElse(null);
    if (vehicle != null){
      vehicle.setVehiclestatusid(ID_STATUS_ON_BUSY);
      vehicleRepository.save(vehicle);
    }
    vehicleMovementRepository.save(vehicleMovement);
  }

  public Optional<VehicleMovement> getById(Long id){
    return vehicleMovementRepository.findById(id);
  }

  public void delete(Long id) {
    Vehicle vehicle = vehicleMovementRepository.findById(id).orElseThrow(
            () -> new IllegalArgumentException("None VehicleMovement with ID = " + id)).getVehicle();
    if (vehicle != null){
      vehicle.setVehiclestatusid(ID_STATUS_ON_AVAILABLE);
      vehicleRepository.save(vehicle);
    }
    vehicleMovementRepository.deleteById(id);
  }

  public List<VehicleMovement> getVehicleWithSort(String field, String sortDir) {
    // asc or desc
    Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleMovementRepository.findAll(sort);
  }
}
