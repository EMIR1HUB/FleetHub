package com.suleimanov.vehiclecontrol.Security.controllers;

import com.suleimanov.vehiclecontrol.Models.UserPrincipal;
import com.suleimanov.vehiclecontrol.Security.exceptions.AppError;
import com.suleimanov.vehiclecontrol.Security.models.JwtRequest;
import com.suleimanov.vehiclecontrol.Security.models.JwtResponse;
import com.suleimanov.vehiclecontrol.Security.services.MyUserDetailsService;
import com.suleimanov.vehiclecontrol.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
  private final MyUserDetailsService myUserDetailsService;
  private final JwtTokenUtils jwtTokenUtils;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticateToken(@RequestBody JwtRequest jwtRequest) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              jwtRequest.getUsername(),
              jwtRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
    }
    //    UserDetails
    final UserPrincipal userPrincipal = (UserPrincipal) myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
    final String token = jwtTokenUtils.generateToken(userPrincipal);
    return ResponseEntity.ok(new JwtResponse(token));
  }


}
