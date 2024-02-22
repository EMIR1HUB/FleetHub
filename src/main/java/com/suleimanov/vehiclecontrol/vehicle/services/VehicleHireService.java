package com.suleimanov.vehiclecontrol.vehicle.services;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleHire;
import com.suleimanov.vehiclecontrol.vehicle.repositories.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
