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
public class Supplier extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq_generator")
  @SequenceGenerator(name = "supplier_seq_generator", sequenceName = "supplier_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  private String name;
  private String address;
  private String city;
  private String phone;
  private String website;
  private String email;
  private String details;

  @ManyToOne
  @JoinColumn(name="countryid", insertable=false, updatable=false)
  private Country country;
  private Long countryid;

  @ManyToOne
  @JoinColumn(name="regionid", insertable=false, updatable=false)
  private Region region;
  private Long regionid;
}
