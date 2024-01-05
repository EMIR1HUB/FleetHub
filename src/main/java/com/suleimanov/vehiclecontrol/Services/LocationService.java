package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.Location;
import com.suleimanov.vehiclecontrol.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  public Optional<Location> findById(Integer id){
    return locationRepository.findById(id);
  }

  public void delete(Integer id) {
    locationRepository.deleteById(id);
  }
}
