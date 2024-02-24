package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.parameters.models.CommonObject;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleMakeService {

  @Autowired
  private VehicleMakeRepository vehicleMakeRepository;

  public List<VehicleMake> getVehiclesMakes(){
    return vehicleMakeRepository.findAll();
  }

  public List<VehicleMake> getByKeyword(String keyword){
    return vehicleMakeRepository.findByKeyword(keyword);
  }

  public void save(VehicleMake vehicleMake){
    vehicleMakeRepository.save(vehicleMake);
  }

  public VehicleMake getById(Integer id){
    return vehicleMakeRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    vehicleMakeRepository.deleteById(id);
  }
  
  public String getVehicleMakeDescriptionById(Integer id){
    return vehicleMakeRepository.findById(id).map(CommonObject::getDescription).orElse(null);
  }

  public List<VehicleMake> getVehicleMakesWithSort(String field, String direction){
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleMakeRepository.findAll(sort);
  }
}
