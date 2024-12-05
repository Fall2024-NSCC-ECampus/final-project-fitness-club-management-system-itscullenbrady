package com.example.fitnessmanagementsystem.repositories;

// Import statements for necessary classes and interfaces
import com.example.fitnessmanagementsystem.model.Member;
import com.example.fitnessmanagementsystem.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for managing Member entities.
 * Extends JpaRepository which provides JPA functionalities for the entity class
 * that is being managed (Member) and the type of its primary key (Long).
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Method to find all members assigned to a specific trainer.
     * 
     * @param trainer The trainer whose assigned members are to be retrieved.
     * @return A list of members assigned to the provided trainer.
     */
    List<Member> findByAssignedTrainer(Trainer trainer);
}
