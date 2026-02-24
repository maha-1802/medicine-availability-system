package com.example.medicine.repository;

import com.example.medicine.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByMedicine_NameAndQuantityGreaterThan(String name, Integer quantity);

}