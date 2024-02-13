package com.suleimanov.vehiclecontrol.parameters.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.suleimanov.vehiclecontrol.security.models.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Region extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_seq_generator")
  @SequenceGenerator(name = "region_seq_generator", sequenceName = "region_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  private String name;
  private String capital;
  private String code;
  private String details;

  @ManyToOne
  @JoinColumn(name = "countryid", insertable = false, updatable = false)
  @JsonIgnore
  private Country country;
  private Long countryid;
}
