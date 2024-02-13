package com.suleimanov.vehiclecontrol.security.repositories;

import com.suleimanov.vehiclecontrol.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByFirstnameAndLastname(String firstname, String lastname);

  Optional<User> findByEmail(String email);
}
