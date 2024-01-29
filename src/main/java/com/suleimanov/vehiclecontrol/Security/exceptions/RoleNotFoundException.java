package com.suleimanov.vehiclecontrol.Security.exceptions;

public class RoleNotFoundException extends RuntimeException {

  public RoleNotFoundException(String message){
    super(message);
  }
}
