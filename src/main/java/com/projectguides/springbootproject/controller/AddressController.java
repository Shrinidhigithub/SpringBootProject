package com.projectguides.springbootproject.controller;


import com.projectguides.springbootproject.exception.ResourceNotFoundException;
import com.projectguides.springbootproject.model.Address;
import com.projectguides.springbootproject.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    private List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // create REST api //
    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    // to get the student address //

    @GetMapping("/studentaddress/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not exist with id:" + id));
        return ResponseEntity.ok(address);
    }

    //Build UPDATE Student Rest API//

    @PutMapping("{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable int id, @RequestBody Address addressDetails) {
        Address updateAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not Exist with id: " + id));

        updateAddress.setStudent_Id(addressDetails.getStudent_Id());
        updateAddress.setStreetline1(addressDetails.getStreetline1());
        updateAddress.setStreetline2(addressDetails.getStreetline2());
        updateAddress.setCity(addressDetails.getCity());
        updateAddress.setState(addressDetails.getState());
        updateAddress.setCountry(addressDetails.getCountry());
        updateAddress.setPincode(addressDetails.getPincode());

        addressRepository.save(updateAddress);

        return ResponseEntity.ok(updateAddress);
    }

    //DELETE Student Rest API//

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable int id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not Exist with id: " + id));

        addressRepository.delete(address);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}