package com.suleimanov.vehiclecontrol.parameters.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.suleimanov.vehiclecontrol.security.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Country extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_seq_generator")
  @SequenceGenerator(name = "country_seq_generator", sequenceName = "country_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  private String code;
  private String capital;
  private String description;
//  private String nationality;
  private String continent;

  @OneToMany(mappedBy = "country")
  private List<Region> regions;
}
