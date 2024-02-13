package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleStatus;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleStatusService {

  @Autowired
  private VehicleStatusRepository vehicleStatusRepository;

  public List<VehicleStatus> getVehiclesStatus(){
    return vehicleStatusRepository.findAll();
  }

  public void save(VehicleStatus vehicleStatus){
    vehicleStatusRepository.save(vehicleStatus);
  }

  public Optional<VehicleStatus> findById(Integer id){
    return vehicleStatusRepository.findById(id);
  }

  public void delete(Integer id) {
    vehicleStatusRepository.deleteById(id);
  }
}
