package com.suleimanov.vehiclecontrol.security.repositories;

import com.suleimanov.vehiclecontrol.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  @Query(value = "SELECT u FROM User u WHERE " +
          "concat(u.id, u.username, u.email) LIKE %?1%")
  List<User> findByKeyword(String keyword);

  @Modifying
  @Transactional
  @Query(value = "UPDATE User u SET u.username = :username, u.email = :email WHERE u.id = :id")
  void update(Long id, String username, String email);
}
