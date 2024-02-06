package com.suleimanov.vehiclecontrol.Security.models;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegistrationUserDto {
  @Column(unique = true)
  private String username;
  private String password;
  private String confirmPassword;
}
