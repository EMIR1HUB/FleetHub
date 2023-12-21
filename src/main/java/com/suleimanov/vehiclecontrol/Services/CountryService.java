package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.Country;
import com.suleimanov.vehiclecontrol.Repositories.CountryRepository;
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
}
