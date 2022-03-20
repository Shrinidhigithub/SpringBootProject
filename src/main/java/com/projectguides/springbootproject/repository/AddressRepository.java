package com.projectguides.springbootproject.repository;

import com.projectguides.springbootproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
