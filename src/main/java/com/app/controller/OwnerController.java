package com.app.controller;

import com.app.dto.OwnerDto;
import com.app.model.Owner;
import com.app.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // Endpoint to create or update an owner
    @PostMapping("/create")
    public Owner createeOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.saveOrUpdateOwner(ownerDto);
    }
    @PostMapping("/update")
    public Owner updateOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.saveOrUpdateOwner(ownerDto);
    }
    // Endpoint to retrieve an owner by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        OwnerDto ownerDto = ownerService.getOwnerById(id);
        if (ownerDto != null) {
            return ResponseEntity.ok(ownerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<OwnerDto>> getAllOwners() {
        List<OwnerDto> ownerDtos = ownerService.getAllOwners();
        return ResponseEntity.ok(ownerDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        ownerService.deleteOwnerById(id);
        return ResponseEntity.ok().build();
    }
    // Additional endpoints as needed...
}
