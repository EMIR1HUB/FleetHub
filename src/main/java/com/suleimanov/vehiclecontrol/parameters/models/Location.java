package com.suleimanov.vehiclecontrol.parameters.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
public class Location extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq_generator")
  @SequenceGenerator(name = "location_seq_generator", sequenceName = "location_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  private String description;
  private String details;
  private String city;
  private String address;

  @ManyToOne
  @JoinColumn(name="countryid", insertable=false, updatable=false)
  private Country country;
  private Long countryid;

  @ManyToOne
  @JoinColumn(name="regionid", insertable=false, updatable=false)
  private Region region;
  private Long regionid;
}
