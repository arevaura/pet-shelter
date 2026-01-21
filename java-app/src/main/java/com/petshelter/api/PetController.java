package com.petshelter.api;

import com.petshelter.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final List<Pet> petsDb = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Pet>> petsGet() {
        return ResponseEntity.ok(petsDb);
    }

    @PostMapping
    public ResponseEntity<Pet> petsPost(@RequestBody Pet pet) {
        petsDb.add(pet);
        return new ResponseEntity<>(pet, HttpStatus.CREATED);
    }
}
