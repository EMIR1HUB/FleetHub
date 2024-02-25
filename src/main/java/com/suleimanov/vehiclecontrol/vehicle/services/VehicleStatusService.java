package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleStatus;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleStatusService {

  @Autowired
  private VehicleStatusRepository vehicleStatusRepository;

  public List<VehicleStatus> getVehiclesStatus(){
    return vehicleStatusRepository.findAll();
  }

  public List<VehicleStatus> getByKeyword(String keyword) {
    return vehicleStatusRepository.findByKeyword(keyword);
  }

  public void save(VehicleStatus vehicleStatus){
    vehicleStatusRepository.save(vehicleStatus);
  }

  public VehicleStatus getById(Integer id){
    return vehicleStatusRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    vehicleStatusRepository.deleteById(id);
  }

  public List<VehicleStatus> getVehiclesStatusWithSort(String field, String direction) {
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleStatusRepository.findAll(sort);
  }
}
