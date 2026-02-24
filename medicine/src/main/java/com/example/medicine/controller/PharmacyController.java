package com.example.medicine.controller;

import com.example.medicine.entity.Pharmacy;
import com.example.medicine.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacies")
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyRepository pharmacyRepository;

    @PostMapping
    public Pharmacy addPharmacy(@RequestBody Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @GetMapping
    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }
}