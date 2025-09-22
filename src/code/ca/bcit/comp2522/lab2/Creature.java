package ca.bcit.comp2522.lab2;

import java.util.Date;

/**
 * Represents a fantasy creature with basic attributes and actions.
 *
 * @author Calvin Nguyen, Taylor Hillier
 * @version 1.0
 */
public class Creature {
    /** Maximum health of a creature. */
    private static int MAX_HEALTH = 100;

    /** Minimum health of a creature. */
    private static int MIN_HEALTH = 1;

    /** Represents the health value of a dead creature. */
    private static int NO_HEALTH = 0;

    /** The name of the creature. */
    private final String name;

    /** The date of birth of a creature. */
    private final Date dateOfBirth;

    /** The current health of a creature. */
    private int health;

    /**
     * Constructs a Creature with the specified attributes.
     *
     * @param name          the creature's name
     * @param dateOfBirth   the creature's date of birth
     * @param health        the creature's health
     * @throws IllegalArgumentException if name is invalid, date is null or future,
     *                                  or health is outside valid range
     */
    public Creature(final String name, final Date dateOfBirth, final int health) {
        //validate name,dob,health

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
    }

    /**
     * Validates if the creature is alive
     *
     * @return true if health is greater than zero, false otherwise
     */
    public boolean isAlive() {
        return health > NO_HEALTH;
    }

}
