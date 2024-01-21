package com.suleimanov.vehiclecontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class VehicleControlApplication {

  // для поиска текущего субъекта, объявляем компонент типа AuditorAware, инициализируемый экземпляром SecurityAuditorAwareImpl,
  // и указываем имя компонента в качестве значения параметра auditorAwareRef в @EnableJpaAuditing
  @Bean
  public AuditorAware<String> auditorAware(){
    return new SecurityAuditorAwareImpl();
  }

  public static void main(String[] args) {
    SpringApplication.run(VehicleControlApplication.class, args);
  }

}
