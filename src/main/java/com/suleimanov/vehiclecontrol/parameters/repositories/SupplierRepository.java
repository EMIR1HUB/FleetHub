package com.suleimanov.vehiclecontrol.parameters.repositories;

import com.suleimanov.vehiclecontrol.parameters.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
