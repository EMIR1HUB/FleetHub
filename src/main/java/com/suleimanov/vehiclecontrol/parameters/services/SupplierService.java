package com.suleimanov.vehiclecontrol.parameters.services;

import com.suleimanov.vehiclecontrol.parameters.models.Supplier;
import com.suleimanov.vehiclecontrol.parameters.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

  @Autowired
  private SupplierRepository supplierRepository;

  public List<Supplier> getSuppliers(){
    return supplierRepository.findAll();
  }

  public void save(Supplier supplier){
    supplierRepository.save(supplier);
  }

  public Supplier getById(Integer id){
    return supplierRepository.findById(id).orElse(null);
  }

  public void delete(Integer id) {
    supplierRepository.deleteById(id);
  }
}
