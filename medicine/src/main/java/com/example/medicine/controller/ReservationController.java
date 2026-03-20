package com.example.medicine.controller;

import com.example.medicine.entity.*;
import com.example.medicine.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final InventoryRepository inventoryRepository;

    // Create Reservation
    @PostMapping
    public String reserveMedicine(@RequestParam Long userId,
                                  @RequestParam Long inventoryId,
                                  @RequestParam Integer quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getQuantity() < quantity) {
            return "Not enough stock available";
        }

        // Reduce stock
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);

        // Save reservation
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setInventory(inventory);
        reservation.setQuantity(quantity);
        reservation.setStatus("CONFIRMED");

        reservationRepository.save(reservation);

        return "Reservation Successful";
    }

    // View reservations by user
    @GetMapping("/user")
    public List<Reservation> getUserReservations(@RequestParam Long userId) {
        return reservationRepository.findByUser_Id(userId);
    }
}