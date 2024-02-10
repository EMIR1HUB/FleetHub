package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.Country;
import com.suleimanov.vehiclecontrol.Models.Region;
import com.suleimanov.vehiclecontrol.Repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  public Optional<Region> findById(Integer id){
    return regionRepository.findById(id);
  }

  public void delete(Integer id) {
    regionRepository.deleteById(id);
  }

  public List<Region> getRegionsByCountry(Country country){
    return regionRepository.getRegionsByCountry(country);
  }
}
