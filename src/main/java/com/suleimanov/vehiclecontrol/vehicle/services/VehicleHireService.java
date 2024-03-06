package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleHire;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleHireService {

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

  public List<VehicleHire> getVehicleHires(){
    return vehicleHireRepository.findAll();
  }

  public List<VehicleHire> getByKeyword(String keyword){
    return vehicleHireRepository.findByKeyword(keyword);
  }

  public void save(VehicleHire vehicleHire){
    vehicleHireRepository.save(vehicleHire);
  }

  public VehicleHire getById(Integer id){
    return vehicleHireRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    vehicleHireRepository.deleteById(id);
  }

  public List<VehicleHire> getVehicleHireWithSort(String field, String direction) {
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    return vehicleHireRepository.findAll(sort);
  }
}
