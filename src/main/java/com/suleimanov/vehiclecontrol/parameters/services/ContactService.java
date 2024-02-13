package com.suleimanov.vehiclecontrol.parameters.services;

import com.suleimanov.vehiclecontrol.parameters.models.Contact;
import com.suleimanov.vehiclecontrol.parameters.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

  @Autowired
  private ContactRepository contactRepository;

  public List<Contact> getContacts(){
    return contactRepository.findAll();
  }

  public void save(Contact contact){
    contactRepository.save(contact);
  }

  public Optional<Contact> findById(Integer id){
    return contactRepository.findById(id);
  }

  public void delete(Integer id) {
    contactRepository.deleteById(id);
  }
}
