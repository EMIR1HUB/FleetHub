package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleType;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleTypeService {

  @Autowired
  private VehicleTypeRepository vehicleTypeRepository;

  public List<VehicleType> getVehiclesTypes(){
    return vehicleTypeRepository.findAll();
  }

  public List<VehicleType> getByKeyword(String keyword){
    return vehicleTypeRepository.findByKeyword(keyword);
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

  public List<VehicleType> getVehicleTypesWithSort(String field, String direction){
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleTypeRepository.findAll(sort);
  }

}
