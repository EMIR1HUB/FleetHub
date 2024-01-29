package com.suleimanov.vehiclecontrol.Repositories;

import com.suleimanov.vehiclecontrol.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByFirstnameAndLastname(String firstname, String lastname);
}
