package com.suleimanov.vehiclecontrol;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryTest {

  @Autowired private CountryRepository countryRepository;

  //FindById
  @Test
  public void testFindById(){
    Country country = countryRepository.findById(11).orElse(null);
    assertNotNull(country);
  }

  @Test
  public void testFindByIdEmpty(){
    Country country = countryRepository.findById(2).orElse(null);
    assertNull(country);
  }

}
