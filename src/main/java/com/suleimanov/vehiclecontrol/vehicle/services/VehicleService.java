package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.Vehicle;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

  @Autowired
  private VehicleRepository vehicleRepository;

  public List<Vehicle> getVehicles(){
    return vehicleRepository.findAll();
  }

  public List<Vehicle> getByKeyword(String keyword) {
    return vehicleRepository.findByKeyword(keyword);
  }

  public void save(Vehicle vehicle){
    vehicleRepository.save(vehicle);
  }

  public Optional<Vehicle> getById(Long id){
    return vehicleRepository.findById(id);
  }

  public void delete(Long id) {
    vehicleRepository.deleteById(id);
  }

  public List<Vehicle> getVehicleWithSort(String field, String direction) {
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleRepository.findAll(sort);
  }
}
