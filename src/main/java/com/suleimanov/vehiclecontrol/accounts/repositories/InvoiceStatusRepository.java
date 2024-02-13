package com.suleimanov.vehiclecontrol.accounts.repositories;

import com.suleimanov.vehiclecontrol.accounts.models.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceStatusRepository extends JpaRepository<InvoiceStatus, Integer> {

}
