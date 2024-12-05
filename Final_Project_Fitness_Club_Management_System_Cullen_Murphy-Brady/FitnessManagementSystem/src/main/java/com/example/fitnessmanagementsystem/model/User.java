/**
 * Entity class representing a user in the fitness management system.
 * This class maps to a database table and contains information about
 * users in the system.
 */
package com.example.fitnessmanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The @Entity annotation marks this class as a JPA entity class.
 * The @Table annotation specifies the name of the table in the database
 * that this class maps to.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    /**
     * The @Id annotation marks this field as the primary key of this entity.
     * The @GeneratedValue annotation marks this field as automatically generated
     * by the database. The strategy is set to GenerationType.IDENTITY, which
     * means that the database will generate a unique ID for this field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The @Column annotation specifies the column name in the database table
     * that this field maps to. The unique attribute is set to true to make sure
     * that each username is unique, and the nullable attribute is set to false
     * to ensure that a username is always provided.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * The password of the user. This is not encrypted, so be careful.
     */
    private String password;

    /**
     * The role of the user in the system. This can be one of the following:
     * <ul>
     * <li>ADMIN</li>
     * <li>TRAINER</li>
     * <li>MEMBER</li>
     * </ul>
     */
    private String role;

    /**
     * Whether or not the user is active. This is used to disable a user's
     * account without deleting it.
     */
    private boolean active = true;
}
