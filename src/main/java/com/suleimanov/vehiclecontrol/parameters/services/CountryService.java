package com.suleimanov.vehiclecontrol.parameters.services;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

  @Autowired
  private CountryRepository countryRepository;

  public List<Country> getCountries(){
    return countryRepository.findAll();
  }

  public void save(Country country){
    countryRepository.save(country);
  }

  public Country getById(Integer id){
    return countryRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    countryRepository.deleteById(id);
  }
}
