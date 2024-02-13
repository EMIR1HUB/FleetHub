package com.suleimanov.vehiclecontrol.parameters.repositories;

import com.suleimanov.vehiclecontrol.parameters.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
