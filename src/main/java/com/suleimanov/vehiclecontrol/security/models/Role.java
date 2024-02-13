package com.suleimanov.vehiclecontrol.security.models;

import com.suleimanov.vehiclecontrol.security.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq_generator")
  @SequenceGenerator(name = "role_seq_generator", sequenceName = "role_seq", allocationSize = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="role_seq_generator")
//  @SequenceGenerator(name="role_seq_generator", sequenceName="role_seq", allocationSize=10)
  // @Column(name="id", updatable=false, nullable=false)
  private String description;
  private String details;
}
