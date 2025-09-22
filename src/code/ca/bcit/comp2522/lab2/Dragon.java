package ca.bcit.comp2522.lab2;

import java.util.Date;

/**
 * Represents a Dragon, a type of Creature with firepower.
 *
 * @author Calvin Nguyen
 * @author Taylor Hillier
 * @version 1.0
 */
public class Dragon extends Creature {

    /** Minimum firepower allowed. */
    private final static int MIN_FIREPOWER = 0;

    /** Maximum firepower allowed. */
    private final static int MAX_FIREPOWER = 100;

    /** Firepower consumed when breathing fire. */
    final static int BREATHE_FIRE_COST = 10;

    /** Damage dealt when breathing fire. */
    final static int BREATHE_FIRE_DAMAGE = 20;

    /** A dragon's current firepower. */
    private int firePower;

    /**
     * Constructs a Dragon with specified attributes.
     *
     * @param name          the dragon's name
     * @param dateOfBirth   the dragon's date of birth
     * @param health        the dragon's health
     * @param firePower     the dragon's firepower
     */
    Dragon(final String name,
           final Date   dateOfBirth,
           final int    health,
           final int    firePower)
    {
        super(name, dateOfBirth, health); // Calls Creature constructor

        validateFirePower(firePower); // Validates if firepower is within bounds
        this.firePower = firePower; // Assign firepower
    }

    /**
     * Provides details about this dragon including firepower.
     *
     * @return a formatted string of dragon details
     */
    @Override
    public String getDetails() {
        String creatureDetails;

        creatureDetails = super.getDetails();

        return creatureDetails + ", Firepower: " + firePower;
    }

    /**
     * This method makes a dragon breathe fire at another creature.
     *
     * @param target the creature to attack
     * @throws LowFirePowerException if firepower is too low
     */
    public void breatheFire(final Creature target) throws LowFirePowerException{

        if(firePower < BREATHE_FIRE_COST){
            throw new LowFirePowerException("Fire power is too low.");
        }

        firePower -= BREATHE_FIRE_COST;
        target.takeDamage(BREATHE_FIRE_DAMAGE);

    }

    /**
     * Restores this dragon's firepower by a specified amount.
     *
     * @param amount the firepower to restore
     */
    public void restoreFirePower(final int amount) {
        firePower += amount;

        if(firePower > MAX_FIREPOWER){
            firePower = MAX_FIREPOWER;
        }
    }

    /**
     * Validates firepower against the defined bounds
     *
     * @param firePower the firePower to validate
     * @throws IllegalArgumentException if firePower is outside range
     */
    private static void validateFirePower(final int firePower){
        if(firePower < MIN_FIREPOWER || firePower > MAX_FIREPOWER){
            throw new IllegalArgumentException(
                    "Firepower must be between " + MIN_FIREPOWER + " and " + MAX_FIREPOWER);
        }
    }

    /**
     * Exception class representing not enough firepower to perform an action
     */
    public static class LowFirePowerException extends Exception {
        public LowFirePowerException(String message) {
            super(message);
        }
    }
}


