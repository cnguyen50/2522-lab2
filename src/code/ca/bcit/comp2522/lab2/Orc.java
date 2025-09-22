package ca.bcit.comp2522.lab2;   // Package declaration

import java.util.Date;            // Import Date for creature birthdate

/**
 * Orc is a subclass of Creature that uses rage for special attacks.
 *
 * @author Calvin Nguyen
 * @author Taylor Hillier
 * @version 1.0
 */
public class Orc extends Creature {

    /** Minimum rage allowed. */
    public static final int MIN_RAGE = 0;

    /** Maximum rage allowed. */
    public static final int MAX_RAGE = 30;

    /** Rage cost for berserk attack. */
    public static final int RAGE_COST = 5;

    /** Rage gained when using berserk. */
    public static final int RAGE_INCREMENT = 5;

    /** Rage level needed to deal double damage */
    public static final int DOUBLE_DAMAGE_THRESHOLD = 20;

    /** Base damage dealt by berserk. */
    public static final int BERSERK_DAMAGE = 15;

    /** Damage multiplier when enraged */
    public static final int DOUBLE_DAMAGE_MULTIPLIER = 2;

    /** Current rage level of the Orc */
    private int rage;

    /**
     * Constructs an Orc with the given attributes.
     *
     * @param name       the name of the Orc
     * @param dateOfBirth the date of birth
     * @param health     starting health
     * @param rage       starting rage
     * @throws IllegalArgumentException if rage is outside the valid range
     */
    public Orc(final String name,
               final Date dateOfBirth,
               final int health,
               final int rage)
    {
        super(name, dateOfBirth, health);   // Call Creature constructor

        validateRage(rage); // Ensure rage is within valid limits
        this.rage = rage;   // Initialize rage
    }

    /**
     * Executes a berserk attack on a target creature.
     * Consumes rage and deals damage based on rage threshold.
     *
     * @param target the creature to attack
     * @throws LowRageException if rage is below the cost threshold
     */
    public void berserk(final Creature target){
        if(rage < RAGE_COST) {
            throw new LowRageException("Need more than " + RAGE_COST + " rage to use berserk"); // Custom unchecked exception
        }

        // Using berserk increases rage
        rage += RAGE_INCREMENT;
        if(rage > MAX_RAGE) {
            rage = MAX_RAGE;
        }

        if(rage >= DOUBLE_DAMAGE_THRESHOLD){
            // If sufficiently enraged, deal double damage
            target.takeDamage(BERSERK_DAMAGE * DOUBLE_DAMAGE_MULTIPLIER);
        } else {
            // Otherwise, deal normal berserk damage
            target.takeDamage(BERSERK_DAMAGE);
        }
    }

    /**
     * Returns a string with the creature's details and rage.
     *
     * @return details string
     */
    @Override
    public String getDetails(){
        String creatureDetails = super.getDetails();   // Get base Creature details
        return creatureDetails + ", Rage: " + rage;    // Append rage info
    }

    /**
     * Validates rage value against defined bounds.
     *
     * @param rage the rage to validate
     * @throws IllegalArgumentException if rage is outside MIN/MAX
     */
    private static void validateRage(final int rage)
    {
        if(rage < MIN_RAGE || rage > MAX_RAGE){
            throw new IllegalArgumentException(
                    "Rage has to be between " + MIN_RAGE
                            + " and " + MAX_RAGE);
        }
    }

    /**
     * Exception thrown when an Orc does not have enough rage
     * to perform a berserk attack.
     *
     * Unchecked (extends RuntimeException).
     */
    public static class LowRageException extends RuntimeException {
        public LowRageException(String message){
            super(message);
        }
    }
}
