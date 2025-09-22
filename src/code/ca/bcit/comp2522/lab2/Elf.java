package ca.bcit.comp2522.lab2;   // Package declaration

import java.util.Date;            // Importing Date for creature birthdate

/**
 * Elf is a subclass of Creature that has mana and can cast spells.
 *
 * @author Calvin Nguyen
 * @author Taylor Hillier
 * @version 1.0
 */
public class Elf extends Creature {

    /** Maximum mana an Elf can have. */
    public static final int MAX_MANA = 50;

    /** Minimum mana an Elf can have. */
    public static final int MIN_MANA = 0;

    /** Damage dealt by a spell. */
    public static final int SPELL_DAMAGE = 10;

    /** Mana cost required to cast a spell. */
    public static final int SPELL_MANA_COST = 5;

    /** Current mana of the Elf */
    private int mana;

    /**
     * Constructs an Elf with the given name, date of birth, health, and mana.
     *
     * @param name        the name of the Elf
     * @param dateOfBirth the date of birth of the Elf
     * @param health      the starting health
     * @param mana        the starting mana
     */
    public Elf(final String name,
               final Date   dateOfBirth,
               final int    health,
               final int    mana)
    {
        super(name, dateOfBirth, health);   // Call parent constructor

        validateMana(mana); // Ensure initial mana is within bounds
        this.mana = mana;   // Assign mana
    }

    /**
     * Returns a string with the creature details including mana.
     *
     * @return a formatted string of Elf details
     */
    @Override
    public String getDetails() {
        String creatureDetails;

        creatureDetails = super.getDetails();   // Get base Creature details

        return creatureDetails + ", Mana: " + mana;   // Append mana info
    }

    /**
     * Casts a spell on a target creature.
     * Reduces Elf's mana and deals damage to the target.
     *
     * @param target the creature being attacked
     * @throws LowManaException if mana is insufficient to cast
     */
    public void castSpell(final Creature target) throws LowManaException {
        if(mana < SPELL_MANA_COST){
            throw new LowManaException("Need at least "
                    + SPELL_MANA_COST
                    + " mana to cast a spell"); // Checked exception
        }

        mana -= SPELL_MANA_COST;       // Reduce mana cost
        target.takeDamage(SPELL_DAMAGE); // Deal damage to target
    }

    /**
     * Restores the Elf's mana by the given amount.
     * Cannot exceed MAX_MANA.
     *
     * @param amount mana to restore
     */
    public void restoreMana(final int amount){

        mana += amount;   // Increase mana

        if(mana > MAX_MANA){
            mana = MAX_MANA;  // Cap at MAX_MANA
        }
    }

    /**
     * Validates mana value against defined bounds.
     *
     * @param mana the mana to validate
     * @throws IllegalArgumentException if mana is outside MIN/MAX
     */
    private static void validateMana(final int mana){
        if(mana < MIN_MANA || mana > MAX_MANA){
            throw new IllegalArgumentException("Mana must be between "
                    + MIN_MANA
                    + " and "
                    + MAX_MANA);
        }
    }

    /**
     * Exception class representing insufficient mana to perform an action.
     * Declared as a static inner class since it is specific to Elf.
     */
    public static class LowManaException extends Exception {
        public LowManaException(String message){
            super(message);
        }
    }
}
