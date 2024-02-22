package com.suleimanov.vehiclecontrol.parameters.services;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

  @Autowired
  private CountryRepository countryRepository;

  public List<Country> getCountries(){
    return countryRepository.findAll();
  }

  public List<Country> getByKeyword(String keyword){
    return countryRepository.findByKeyword(keyword);
  }

  public Page<Country> getPage(Integer pageNumber){
    Pageable pageable = PageRequest.of(pageNumber - 1, 7);
    return countryRepository.findAll(pageable);
  }

  public Page<Country> getPageByKeyword(Integer pageNumber, String keyword){
    Pageable pageable = PageRequest.of(pageNumber - 1, 7);
    return countryRepository.findByKeyword(keyword, pageable);
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

  public Page<Country> getCountriesWithSort(String field, String direction, int pageNumber){
    // asc or desc
    Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(field).ascending() : Sort.by(field).descending();
    Pageable pageable = PageRequest.of(pageNumber - 1, 7, sort);
    return countryRepository.findAll(pageable);
  }
}
