package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleType;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeService {

  @Autowired
  private VehicleTypeRepository vehicleTypeRepository;

  public List<VehicleType> getVehiclesTypes(){
    return vehicleTypeRepository.findAll();
  }

  public void save(VehicleType vehicleType){
    vehicleTypeRepository.save(vehicleType);
  }

  public VehicleType getById(Integer id){
    return vehicleTypeRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    vehicleTypeRepository.deleteById(id);
  }

  public Long getCount(){
    return vehicleTypeRepository.count();
  }
}
