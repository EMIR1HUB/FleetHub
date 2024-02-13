package com.suleimanov.vehiclecontrol.parameters.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.suleimanov.vehiclecontrol.security.models.Auditable;
import jakarta.persistence.*;

@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CommonObject extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "common_object_seq_generator")
  @SequenceGenerator(name = "common_object_seq_generator", sequenceName = "common_object_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;
  private String description;
  private String details;

  public CommonObject(){}

  public CommonObject(Long id, String description, String details) {
    this.id = id;
    this.description = description;
    this.details = details;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  @Override
  public String toString() {
    return "CommonObject{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", details='" + details + '\'' +
            '}';
  }
}
