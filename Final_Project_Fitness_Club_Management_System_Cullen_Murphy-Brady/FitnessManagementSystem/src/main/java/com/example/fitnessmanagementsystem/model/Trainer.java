/**
 * Entity class representing a trainer in the fitness management system.
 * This class maps to a database table and contains information about
 * trainers assigned to members and their schedules.
 */
package com.example.fitnessmanagementsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The @Entity annotation marks this class as a JPA entity class.
 * The @Getter and @Setter annotations are used to generate getters and
 * setters for the fields of this class.
 */
@Entity
@Getter
@Setter
public class Trainer extends User {

    /**
     * The trainers specialization. This can be weightlifting, yoga, or
     * any other type of fitness activity.
     */
    private String specialization;

    /**
     * The trainers regular shift. This can be morning, afternoon, or
     * evening.
     */
    private String shift;

    /**
     * The trainers assigned members. Each trainer is assigned to
     * multiple members, and each member is assigned to one trainer.
     */
    @OneToMany(mappedBy = "assignedTrainer")
    private List<Member> members;

}
