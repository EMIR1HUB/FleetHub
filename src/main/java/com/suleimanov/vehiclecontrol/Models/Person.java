package com.suleimanov.vehiclecontrol.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String firstname;
  private String lastname;
  private String othername;

  @Formula(value = " concat(firstName, ' ', lastName) ")
  private String fullname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateOfBirth;

  private String initials;
  private String passport;
  private String gender;
  private String maritalStatus;


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
  private String phone;
  private String email;
  private String photo;
}
