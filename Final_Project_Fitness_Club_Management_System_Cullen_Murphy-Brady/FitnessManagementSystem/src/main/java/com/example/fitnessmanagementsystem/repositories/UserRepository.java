/**
 * This interface is a Spring Data JPA repository interface for managing User entities.
 * It extends the JpaRepository interface, which provides the standard CRUD operations
 * for the entity class that is being managed (User) and the type of its primary key (Long).
 * 
 * The interface has one additional method, findByUsername, which is annotated to
 * query the User table to find all users whose username matches the provided string.
 * The method returns an Optional<User>, which is a container object that may contain
 * a User object if the query finds a matching user, or may be empty if the query finds
 * no matching user.
 */
package com.example.fitnessmanagementsystem.repositories;

import com.example.fitnessmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Method to find all users with a specific username.
     * 
     * @param username The username to search for.
     * @return An Optional<User> containing the matching user if found, or empty if
     *         not.
     */
    Optional<User> findByUsername(String username);
}
