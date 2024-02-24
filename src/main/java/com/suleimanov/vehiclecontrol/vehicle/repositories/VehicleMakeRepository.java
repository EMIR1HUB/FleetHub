package com.suleimanov.vehiclecontrol.vehicle.repositories;

import com.suleimanov.vehiclecontrol.vehicle.models.VehicleMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMakeRepository extends JpaRepository<VehicleMake, Integer> {

  @Query(value = "SELECT vm FROM VehicleMake vm WHERE concat(vm.description, vm.details) LIKE %?1%")
  List<VehicleMake> findByKeyword(String keyword);
}
