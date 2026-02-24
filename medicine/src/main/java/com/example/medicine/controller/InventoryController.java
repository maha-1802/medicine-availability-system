package com.example.medicine.controller;

import com.example.medicine.entity.*;
import com.example.medicine.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    @PostMapping
    public Inventory addInventory(@RequestParam Long pharmacyId,
                                  @RequestParam Long medicineId,
                                  @RequestParam Integer quantity) {

        Pharmacy pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        Inventory inventory = new Inventory();
        inventory.setPharmacy(pharmacy);
        inventory.setMedicine(medicine);
        inventory.setQuantity(quantity);

        return inventoryRepository.save(inventory);
    }

    @GetMapping("/search")
    public List<Inventory> searchByMedicine(@RequestParam String name) {
        return inventoryRepository.findByMedicine_NameAndQuantityGreaterThan(name, 0);
    }
}