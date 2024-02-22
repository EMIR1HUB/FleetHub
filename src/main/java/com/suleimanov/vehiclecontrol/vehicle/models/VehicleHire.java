package com.suleimanov.vehiclecontrol.vehicle.models;


import com.suleimanov.vehiclecontrol.parameters.models.Client;
import com.suleimanov.vehiclecontrol.parameters.models.Location;
import com.suleimanov.vehiclecontrol.security.models.Auditable;
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
public class VehicleHire extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_hire_seq_generator")
  @SequenceGenerator(name = "vehicle_hire_seq_generator", sequenceName = "vehicle_hire_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name="vehicleid", insertable=false, updatable=false)
  private Vehicle vehicle;
  private Long vehicleid;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateOut;

  private String timeOut;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateIn;

  private String timeIn;

  @ManyToOne
  @JoinColumn(name="clientid", insertable=false, updatable=false)
  private Client client;
  private Long clientid;

  @ManyToOne
  @JoinColumn(name="locationid", insertable=false, updatable=false)
  private Location location;
  private Long locationid;

  private String price;
  private String remarks;
}
