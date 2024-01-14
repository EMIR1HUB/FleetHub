package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.VehicleHire;
import com.suleimanov.vehiclecontrol.Repositories.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleHireService {

  @Autowired
  private VehicleHireRepository vehicleHireRepository;

  public List<VehicleHire> getVehicleHires(){
    return vehicleHireRepository.findAll();
  }

  public void save(VehicleHire vehicleHire){
    vehicleHireRepository.save(vehicleHire);
  }

  public Optional<VehicleHire> findById(Integer id){
    return vehicleHireRepository.findById(id);
  }

  public void delete(Integer id) {
    vehicleHireRepository.deleteById(id);
  }
}
