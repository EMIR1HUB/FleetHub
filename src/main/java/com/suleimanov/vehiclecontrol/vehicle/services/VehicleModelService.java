package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.parameters.models.CommonObject;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import com.suleimanov.vehiclecontrol.vehicle.models.VehicleModel;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleModelService {

  @Autowired
  private VehicleModelRepository vehicleModelRepository;

  public List<VehicleModel> getVehiclesModels(){
    return vehicleModelRepository.findAll();
  }

  public List<VehicleModel> getByKeyword(String keyword){
    return vehicleModelRepository.findByKeyword(keyword);
  }

  public void save(VehicleModel vehicleModel){
    vehicleModelRepository.save(vehicleModel);
  }

  public VehicleModel getById(Integer id){
    return vehicleModelRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    vehicleModelRepository.deleteById(id);
  }

  public List<VehicleModel> getVehicleModelByMake(VehicleMake vehicleMake){
    return vehicleModelRepository.getVehicleModelByVehiclemake(vehicleMake);
  }

  public String getVehicleModelDescriptionById(Integer id){
    return vehicleModelRepository.findById(id).map(CommonObject::getDescription).orElse(null);
  }

  public List<VehicleModel> getAllWithSort(String field,  String direction){
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleModelRepository.findAll(sort);
  }
}
