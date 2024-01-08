package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.VehicleMake;
import com.suleimanov.vehiclecontrol.Repositories.VehicleMakeRepository;
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
}
