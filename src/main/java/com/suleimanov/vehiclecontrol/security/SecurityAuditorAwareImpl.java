package com.suleimanov.vehiclecontrol.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


// Реализовываем интерфейс AuditorAware<T>, для настройки значений в аннотированных полях, как @CreatedBy, @LastModifiedBy ...
public class SecurityAuditorAwareImpl implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return Optional.ofNullable(authentication.getName()).filter(s -> !s.isEmpty());
  }
}
