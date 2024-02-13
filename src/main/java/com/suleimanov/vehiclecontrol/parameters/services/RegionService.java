package com.suleimanov.vehiclecontrol.parameters.services;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.models.Region;
import com.suleimanov.vehiclecontrol.parameters.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

  @Autowired
  private RegionRepository regionRepository;

  public List<Region> getRegions(){
    return regionRepository.findAll();
  }

  public void save(Region region){
    regionRepository.save(region);
  }

  public Region getById(Integer id){
    return regionRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    regionRepository.deleteById(id);
  }

  public List<Region> getRegionsByCountry(Country country){
    return regionRepository.getRegionsByCountry(country);
  }
}
