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
    private static int DEAD_HEALTH = 0;

    /** Minimum valid damage amount. */
    private static final int MIN_DAMAGE = 0;

    /** The name of the creature. */
    private final String name;

    /** The date of birth of a creature. */
    private final Date dateOfBirth;

    /** The current health of a creature. */
    private int health;

    /**
     * Constructs a Creature with the specified attributes.
     *
     * @param name                      the creature's name
     * @param dateOfBirth               the creature's date of birth
     * @param health                    the creature's health
     * @throws IllegalArgumentException if name is invalid, date is null or future,
     *                                  or health is outside valid range
     */
    public Creature(final String name,
                    final Date dateOfBirth,
                    final int health) {

        //validate name,dob,health

        this.name           = name;
        this.dateOfBirth    = dateOfBirth;
        this.health         = health;
    }

    /**
     * Validates if the creature is alive.
     *
     * @return true if health is greater than zero, false otherwise
     */
    public boolean isAlive() {
        return health > DEAD_HEALTH;
    }

    /**
     * Reduces this creature's health by the damage amount.
     *
     * @param damage            the amount of damage to apply
     * @throws DamageException  if the damage is negative
     */
    public void takeDamage(final int damage) {

        //If damage is negative, throw unchecked DamageException
        if (damage < MIN_DAMAGE) {
            throw new DamageException("Damage amount can't be negative");
        }

        //Reduces health by damage
        health -= damage;

        //If health goes below zero, set it to zero
        if (health < DEAD_HEALTH) {
            health = DEAD_HEALTH;
        }
    }

    /**
     * Increases this creature's health by the healing amount.
     *
     * @param healAmount            the amount of healing to apply
     * @throws HealingException     if the healAmount is negative
     */
    public void heal(final int healAmount) {

        //If healing amount is negative, throw unchecked HealingException
        if (healAmount < MIN_HEALTH) {
            throw new HealingException("Heal amount can't be negative");
        }

        //Increases health by heal amount
        health += healAmount;

        //Since heal amount cannot exceed maximum health, set it to maximum health
        if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }
    }

    /**
     * Calculates the age of this creature's age in years based on its date of birth.
     *
     * @return the age of the creature in years
     */
    public int getAgeYears() {
    //not sure how jason wants us to get date w/ or w/o Date from util or we could use Date class from lab1

    }

    /**
     * Provides a formatted string containing the creature's details.
     *
     * @return a string with name, date of birth, age and health
     */
    public String getDetails() {
        final StringBuilder builder;
        builder = new StringBuilder();

        builder.append("Name: ")
                .append(name)
                .append(", Date of Birth: ")
                .append(dateOfBirth)
                .append(getAgeYears())
                .append(" years old, Health: ")
                .append(health);

        return builder.toString();
    }
}
