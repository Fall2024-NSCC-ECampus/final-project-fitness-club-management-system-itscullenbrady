/**
 * This class is an entity, meaning it maps to a database table.
 * It extends the User class, meaning it has all the fields and methods
 * of the User class, and also adds its own fields and methods.
 * Specifically, it adds a field for the membership type of the member,
 * and a field for the trainer assigned to the member.
 * 
 * The membership type can be one of several types, such as "GOLD", "SILVER", or "BRONZE",
 * and it determines what kind of services the member is entitled to.
 * 
 * The assigned trainer is the trainer that the member is assigned to,
 * and it is used to schedule sessions with the member.
 * 
 * This class is used to represent a member in the fitness management system.
 */
package com.example.fitnessmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * This annotation marks this class as an entity, meaning it maps to a database
 * table.
 */
@Entity

/**
 * This annotation is a shortcut for writing public getters and setters for the
 * fields of this class.
 */
@Getter
@Setter
public class Member extends User {

    /**
     * This field is used to store the membership type of the member.
     * The membership type can be one of several types, such as "GOLD", "SILVER", or
     * "BRONZE",
     * and it determines what kind of services the member is entitled to.
     */
    private String membershipType;

    /**
     * This field is used to store the trainer assigned to the member.
     * The assigned trainer is the trainer that the member is assigned to,
     * and it is used to schedule sessions with the member.
     */
    @ManyToOne
    private Trainer assignedTrainer;
}
