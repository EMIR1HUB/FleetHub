package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMovement;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMovementService {

  @Autowired
  private VehicleMovementRepository vehicleMovementRepository;

  public List<VehicleMovement> getVehicleMovements(){
    return vehicleMovementRepository.findAll();
  }

  public void save(VehicleMovement vehicleMovement){
    vehicleMovementRepository.save(vehicleMovement);
  }

  public Optional<VehicleMovement> findById(Integer id){
    return vehicleMovementRepository.findById(id);
  }

  public void delete(Integer id) {
    vehicleMovementRepository.deleteById(id);
  }
}
