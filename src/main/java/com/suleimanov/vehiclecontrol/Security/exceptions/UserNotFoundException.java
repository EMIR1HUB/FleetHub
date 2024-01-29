package com.suleimanov.vehiclecontrol.Security.exceptions;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String message){
    super(message);
  }
}
