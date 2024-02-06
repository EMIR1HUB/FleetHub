package com.suleimanov.vehiclecontrol.Security.models;

import com.suleimanov.vehiclecontrol.Security.models.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
  @SequenceGenerator(name = "user_seq_generator", sequenceName = "user_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
//  @SequenceGenerator(name = "user_seq_generator", sequenceName = "user_seq", initialValue = 10)
  // @Column(name = "id", updatable = false, nullable = false)
  private String firstname;
  private String lastname;

  @Column(unique = true)
  private String username;
  private String password;

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)   // FetchType.EAGER обеспечивает жадную загрузку связанных сущностей
  @JoinTable(name = "user_role",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")})
  Set<Role> roles = new HashSet<>();
}
