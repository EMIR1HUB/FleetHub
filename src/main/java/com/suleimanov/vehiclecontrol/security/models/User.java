package com.suleimanov.vehiclecontrol.security.models;

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
//  private String firstname;
//  private String lastname;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String username;

  //  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
//          message = "Пароль должен содержать хотя бы одну цифру, одну строчную букву, одну заглавную букву и быть не менее 8 символов")
//  @Size(min = 4, message = "Пароль должен быть от 4 символов")
  private String password;

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)   // FetchType.EAGER обеспечивает жадную загрузку связанных сущностей
  @JoinTable(name = "user_role",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")})
  Set<Role> roles = new HashSet<>();
}
