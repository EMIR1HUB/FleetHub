package com.suleimanov.vehiclecontrol.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  private String description;
  private String details;

  @ManyToOne
  @JoinColumn(name="countryid", insertable=false, updatable=false)
  private Country country;
  private Integer countryid;

  @ManyToOne
  @JoinColumn(name="regionid", insertable=false, updatable=false)
  private Region region;
  private Integer regionid;

  private String city;
  private String address;
}
