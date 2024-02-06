package com.suleimanov.vehiclecontrol.Security.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
  private String token;
}
