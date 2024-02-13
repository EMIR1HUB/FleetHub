package com.suleimanov.vehiclecontrol.parameters.services;

import com.suleimanov.vehiclecontrol.parameters.models.Location;
import com.suleimanov.vehiclecontrol.parameters.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

  @Autowired
  private LocationRepository locationRepository;

  public List<Location> getLocations(){
    return locationRepository.findAll();
  }

  public void save(Location location){
    locationRepository.save(location);
  }

  public Location getById(Integer id){
    return locationRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    locationRepository.deleteById(id);
  }
}
