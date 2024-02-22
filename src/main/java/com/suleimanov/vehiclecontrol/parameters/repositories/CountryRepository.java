package com.suleimanov.vehiclecontrol.parameters.repositories;

import com.suleimanov.vehiclecontrol.parameters.models.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
  @Query(value = "SELECT c FROM Country c WHERE " +
          "concat(c.description, c.capital, c.code, c.continent) LIKE %?1%") // "c.description LIKE %?1% OR c.capital LIKE %?1% OR c.continent LIKE %?1%")
  List<Country> findByKeyword(String keyword);

  @Query("SELECT c FROM Country c WHERE " +
          "concat(c.description, c.capital, c.code, c.continent) LIKE %?1%")
  Page<Country> findByKeyword(String keyword, Pageable pageable);
}
