package com.suleimanov.vehiclecontrol.security.repositories;

import com.suleimanov.vehiclecontrol.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  @Query(value = "SELECT * FROM role WHERE id NOT IN (" +
          "SELECT role_id FROM user_role WHERE user_id = ?1)",
          nativeQuery = true)
  Set<Role> findRolesNotUser(Long userId);

  @Query(value = "SELECT * FROM role WHERE id IN (" +
          "SELECT role_id FROM user_role WHERE user_id = ?1)",
          nativeQuery = true)
  Set<Role> findAllByUserId(Long userId);

  @Query(value = "SELECT r FROM Role r WHERE " +
          "concat(r.id, r.description, r.details) LIKE %?1%")
  List<Role> findByKeyword(String keyword);
}
