package com.suleimanov.vehiclecontrol.hr.models;

import com.suleimanov.vehiclecontrol.security.models.Auditable;
import com.suleimanov.vehiclecontrol.parameters.models.Country;
import com.suleimanov.vehiclecontrol.parameters.models.Region;
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
public class Person extends Auditable<String> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_seq_generator")
  @SequenceGenerator(name = "person_seq_generator", sequenceName = "person_seq", initialValue = 10)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  private String firstname;
  private String lastname;
  private String othername;

  @Formula(value = " concat(firstName, ' ', lastName) ")
  private String fullname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date dateOfBirth;

  @ManyToOne
  @JoinColumn(name="countryid", insertable=false, updatable=false)
  private Country country;
  private Long countryid;

  @ManyToOne
  @JoinColumn(name="regionid", insertable=false, updatable=false)
  private Region region;
  private Long regionid;

  private String city;
  private String address;
  private String phone;
  private String email;

  @Column(unique = true)
  private String passport;
  private String gender;
  private String maritalStatus;
  private String photo;
}
