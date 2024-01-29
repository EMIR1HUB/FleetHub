package com.suleimanov.vehiclecontrol.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Employee extends Person{

  @ManyToOne
  @JoinColumn(name="employeetypeid", insertable=false, updatable=false)
  private EmployeeType employeeType;
  private Integer employeetypeid;

  private String photo;
  private String username;

  @ManyToOne
  @JoinColumn(name="jobtitleid", insertable=false, updatable=false)
  private JobTitle jobTitle;
  private Integer jobtitleid;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hireDate;
}
