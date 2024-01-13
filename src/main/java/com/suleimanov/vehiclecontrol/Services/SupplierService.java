package com.suleimanov.vehiclecontrol.Services;

import com.suleimanov.vehiclecontrol.Models.Supplier;
import com.suleimanov.vehiclecontrol.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

  public Optional<Supplier> findById(Integer id){
    return supplierRepository.findById(id);
  }

  public void delete(Integer id) {
    supplierRepository.deleteById(id);
  }
}
