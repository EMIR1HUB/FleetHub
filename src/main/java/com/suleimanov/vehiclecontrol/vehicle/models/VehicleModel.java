package com.suleimanov.vehiclecontrol.vehicle.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suleimanov.vehiclecontrol.parameters.models.CommonObject;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class VehicleModel extends CommonObject {

  @ManyToOne
  @JoinColumn(name = "vehiclemakeid", insertable = false, updatable = false)
  @JsonIgnore
  private VehicleMake vehiclemake;

  private Integer vehiclemakeid;
}
