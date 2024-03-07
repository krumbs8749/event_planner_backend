package com.app.service;

import com.app.dto.OwnerDto;
import com.app.model.Owner;
import com.app.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // Convert an OwnerDto to an Owner entity
    private Owner convertToEntity(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setName(ownerDto.getName());
        owner.setEmail(ownerDto.getEmail());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        return owner;
    }

    // Convert an Owner entity to an OwnerDto
    private OwnerDto convertToDto(Owner owner) {
        return new OwnerDto(owner.getName(), owner.getEmail(), owner.getPhoneNumber());
    }

    // Create or Update an Owner from an OwnerDto
    public Owner saveOrUpdateOwner(OwnerDto ownerDto) {
        Owner owner = convertToEntity(ownerDto);
        ownerRepository.save(owner);
        return owner;
    }

    // Retrieve an Owner by ID and convert to OwnerDto
    public OwnerDto getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null); // Handle null case appropriately
    }

    // Retrieve all Owners and convert to a list of OwnerDtos
    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deleteOwnerById(Long id) {
        ownerRepository.deleteById(id);
    }
}
