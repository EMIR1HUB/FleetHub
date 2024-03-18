package com.suleimanov.vehiclecontrol.hr.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.suleimanov.vehiclecontrol.security.models.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends Person{

  @OneToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @ManyToOne
  @JoinColumn(name="employeetypeid", insertable=false, updatable=false)
  private EmployeeType employeeType;
  private Long employeetypeid;

  @ManyToOne
  @JoinColumn(name="jobtitleid", insertable=false, updatable=false)
  private JobTitle jobTitle;
  private Long jobtitleid;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date hireDate;
}
