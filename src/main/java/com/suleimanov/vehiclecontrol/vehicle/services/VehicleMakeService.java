package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.parameters.models.CommonObject;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleMakeService {

  @Autowired
  private VehicleMakeRepository vehicleMakeRepository;

  public List<VehicleMake> getVehiclesMakes(){
    return vehicleMakeRepository.findAll();
  }

  public void save(VehicleMake vehicleMake){
    vehicleMakeRepository.save(vehicleMake);
  }

  public Optional<VehicleMake> findById(Integer id){
    return vehicleMakeRepository.findById(id);
  }

  public void delete(Integer id) {
    vehicleMakeRepository.deleteById(id);
  }
  
  public String getVehicleMakeDescriptionById(Integer id){
    return findById(id).map(CommonObject::getDescription).orElse(null);
  }
}
