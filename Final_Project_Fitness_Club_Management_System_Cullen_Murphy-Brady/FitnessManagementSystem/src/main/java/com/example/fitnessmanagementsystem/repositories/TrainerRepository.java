/**
 * This is a Spring Data JPA repository interface for managing Trainer entities.
 * It extends the JpaRepository interface, which provides standard CRUD operations
 * for the entity class that is being managed (Trainer) and the type of its primary
 * key (Long).
 * 
 * The interface has one additional method, findByShift, which is annotated to
 * query the Trainer table to find all trainers whose shift matches the provided
 * string.
 * 
 * @author <a href="mailto:your_email@example.com">Your Name</a>
 */
package com.example.fitnessmanagementsystem.repositories;

import com.example.fitnessmanagementsystem.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    /**
     * Finds all trainers whose shift matches the provided string
     * 
     * @param shift The shift to search for.
     * @return A list of trainers whose shift matches the provided string.
     */
    List<Trainer> findByShift(String shift);
}
