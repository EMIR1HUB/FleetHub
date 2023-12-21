package com.suleimanov.vehiclecontrol.Repositories;

import com.suleimanov.vehiclecontrol.Models.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Integer> {

}
