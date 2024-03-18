package com.suleimanov.vehiclecontrol.vehicle.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.suleimanov.vehiclecontrol.security.models.Auditable;
import com.suleimanov.vehiclecontrol.parameters.models.Location;
import com.suleimanov.vehiclecontrol.hr.models.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Vehicle extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_seq_generator")
  @SequenceGenerator(name = "vehicle_seq_generator", sequenceName = "vehicle_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  private String name;
  private String photo;

  @ManyToOne
  @JoinColumn(name = "vehicletypeid", insertable = false, updatable = false)
  private VehicleType vehicleType;
  private Integer vehicletypeid;

  @Column(unique = true)
  private String vehicleNumber;
  private String description;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date registrationDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date acquisitionDate;

  @ManyToOne
  @JoinColumn(name="vehiclemakeid", insertable=false, updatable=false)
  private VehicleMake vehicleMake;
  private Integer vehiclemakeid;

  private String power;
  private String fuelCapacity;

  @ManyToOne
  @JoinColumn(name="vehiclestatusid", insertable=false, updatable=false)
  private VehicleStatus vehicleStatus;
  private Integer vehiclestatusid;

  private String netWeight;

  @ManyToOne
  @JoinColumn(name="employeeid", insertable=false, updatable=false)
  private Employee inCharge;
  private Integer employeeid;

  @ManyToOne
  @JoinColumn(name="vehiclemodelid", insertable=false, updatable=false)
  private VehicleModel vehicleModel;
  private Integer vehiclemodelid;

  @ManyToOne
  @JoinColumn(name="locationid", insertable=false, updatable=false)
  private Location currentLocation;
  private Integer locationid;

  private String remarks;
}
