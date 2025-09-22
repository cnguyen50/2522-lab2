package ca.bcit.comp2522.lab2;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents a fantasy creature with basic attributes and actions.
 *
 * @author Calvin Nguyen
 * @author Taylor Hillier
 * @version 1.0
 */
public class Creature {
    /** Maximum health of a creature. */
    private static final int MAX_HEALTH = 100;

    /** Minimum health of a creature. */
    private static final int MIN_HEALTH = 1;

    /** Represents the health value of a dead creature. */
    private static final int DEAD_HEALTH = 0;

    /** Represents the minimum value to check if values are negative. */
    private static final int MIN_AMOUNT = 0;

    /** Number of milliseconds in a year (no leap years)*/
    private static final long MILLISECONDS_PER_YEAR = 1000 * 60 * 60 * 24 * 365;

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

        validateName(name);
        validateDate(dateOfBirth);
        validateHealth(health);

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
        if (damage < MIN_AMOUNT) {
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
        if (healAmount < MIN_AMOUNT) {
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
     * Calculates the age of this creature age in years based on its date of birth.
     *
     * @return the age of the creature in years
     */
    public int getAgeYears() {
        final long milliSecondsAlive;
        milliSecondsAlive = new Date().getTime() - dateOfBirth.getTime();

        return (int) (milliSecondsAlive / MILLISECONDS_PER_YEAR);
    }

    /**
     * Provides a formatted string containing the creature's details.
     *
     * @return a string with name, date of birth, age and health
     */
    public String getDetails() {
        final StringBuilder builder;
        final SimpleDateFormat formatter;

        builder = new StringBuilder();
        formatter = new SimpleDateFormat("MMM dd yyyy");

        builder.append("Name: ")
                .append(name)
                .append(", Date of Birth: ")
                .append(formatter.format(dateOfBirth))
                .append(", Age: ")
                .append(getAgeYears())
                .append(" years, Health: ")
                .append(health);

        return builder.toString();
    }

    /**
     * Validation method that checks if given name is valid.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if null or empty
     */
    private static void validateName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
    }

    /**
     * Validation method that checks if given date of birth is valid.
     *
     * @param dateOfBirth the date to validate
     * @throws IllegalArgumentException if null or in the future
     */
    private static void validateDate(final Date dateOfBirth) {
        if (dateOfBirth == null) {
            throw new IllegalArgumentException("Date of birth must not be null");
        }
        if (dateOfBirth.after(new Date())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
    }

    /**
     * Validation method that checks if health is valid.
     *
     * @param health the health to validate
     * @throws IllegalArgumentException if outside the valid range
     */
    private static void validateHealth(final int health) {
        if (health < MIN_HEALTH || health > MAX_HEALTH) {
            throw new IllegalArgumentException(
                    "Health must be between " + MIN_HEALTH + " and " + MAX_HEALTH);
        }
    }

    /**
     * Exception thrown when damage is negative
     * Unchecked (extends RuntimeException).
     */
    public static class DamageException extends RuntimeException{
        public DamageException(final String message) {
            super(message);
        }
    }

    /**
     * Exception thrown when healing is negative
     * Unchecked (extends RuntimeException).
     */
    public static class HealingException extends RuntimeException{
        public HealingException(final String message) {
            super(message);
        }
    }
}
