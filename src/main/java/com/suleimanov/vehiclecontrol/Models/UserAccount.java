package com.suleimanov.vehiclecontrol.Models;

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
public class UserAccount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String firstname;
  private String lastname;
  private String username;
  private String password;

  @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
  @JoinTable(
          name = "user_role",
          joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "role_id")}
  )
  Set<UserAccount> roles = new HashSet<>();
}
