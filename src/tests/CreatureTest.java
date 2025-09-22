package ca.bcit.comp2522.lab2;

import java.util.Date;

public class CreatureTest {

    // ----- Date construction constants -----
    private static final int YEAR_BASE_1900               = 1900; // Date(int year) uses year-1900
    private static final int MONTH_JANUARY_INDEX_ZERO     = 0;
    private static final int MONTH_JUNE_INDEX_ZERO        = 5;
    private static final int MONTH_SEPTEMBER_INDEX_ZERO   = 8;

    private static final int DAY_01 = 1;
    private static final int DAY_15 = 15;
    private static final int DAY_30 = 30;

    private static final int YEAR_2000 = 2000;
    private static final int YEAR_2005 = 2005;
    private static final int YEAR_2010 = 2010;

    // ----- Initial creature stats -----
    private static final int DRAGON_HEALTH_INITIAL     = 90;
    private static final int DRAGON_FIREPOWER_INITIAL  = 40;

    private static final int ELF_HEALTH_INITIAL        = 80;
    private static final int ELF_MANA_INITIAL          = 20;

    private static final int ORC_HEALTH_INITIAL        = 85;
    private static final int ORC_RAGE_INITIAL          = 10;

    // ----- Labels -----
    private static final String LABEL_DETAILS_HEADER = "=== Creature Details (polymorphism without arrays) ===";
    private static final String LABEL_FIGHT_HEADER   = "=== Let them fight (with friendly error handling) ===";
    private static final String LABEL_FINAL_STATUS   = "=== Final Status ===";

    private static final String LABEL_D2E = "[Dragon -> Elf] breatheFire";
    private static final String LABEL_E2O = "[Elf -> Orc] castSpell";
    private static final String LABEL_O2D = "[Orc -> Dragon] berserk (direct)";

    public static void main(String[] args){
        // Build valid past birthdates using named constants (no magic numbers)
        final java.util.Date dragonBirthDate = new java.util.Date(
                YEAR_2000 - YEAR_BASE_1900,
                MONTH_JANUARY_INDEX_ZERO,
                DAY_01
        );
        final java.util.Date elfBirthDate = new java.util.Date(
                YEAR_2005 - YEAR_BASE_1900,
                MONTH_JUNE_INDEX_ZERO,
                DAY_15
        );
        final java.util.Date orcBirthDate = new java.util.Date(
                YEAR_2010 - YEAR_BASE_1900,
                MONTH_SEPTEMBER_INDEX_ZERO,
                DAY_30
        );

        // Create creatures
        final Dragon dragon = new Dragon("Smolder", dragonBirthDate, DRAGON_HEALTH_INITIAL, DRAGON_FIREPOWER_INITIAL);
        final Elf    elf    = new Elf   ("Aelwyn",  elfBirthDate,    ELF_HEALTH_INITIAL,   ELF_MANA_INITIAL);
        final Orc    orc    = new Orc   ("Gor",     orcBirthDate,    ORC_HEALTH_INITIAL,   ORC_RAGE_INITIAL);

        // ----- Polymorphism without arrays: reuse a single Creature reference -----
        System.out.println(LABEL_DETAILS_HEADER);

        Creature c;

        c = dragon; // Dragon as Creature
        printDetailsAndType(c);

        c = elf;    // Elf as Creature
        printDetailsAndType(c);

        c = orc;    // Orc as Creature
        printDetailsAndType(c);

        // ----- Combat with friendly error handling -----
        System.out.println(LABEL_FIGHT_HEADER);

        // Dragon -> Elf
        try {
            System.out.println(LABEL_D2E);
            dragon.breatheFire(elf);
            System.out.println("Result: " + elf.getDetails());
        } catch (Dragon.LowFirePowerException e) {
            System.out.println("Friendly: Dragon has too little firepower: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Friendly (unexpected runtime): " + e.getMessage());
        }

        // Elf -> Orc
        try {
            System.out.println(LABEL_E2O);
            elf.castSpell(orc);
            System.out.println("Result: " + orc.getDetails());
        } catch (Elf.LowManaException e) { // checked in your Elf
            System.out.println("Friendly: Elf doesn't have enough mana: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Friendly (unexpected runtime): " + e.getMessage());
        }

        // Orc -> Dragon (direct call—no reflection)
        try {
            System.out.println(LABEL_O2D);
            orc.berserk(dragon); // now public
            System.out.println("Result: " + dragon.getDetails());
        } catch (Orc.LowRageException e) { // unchecked in your Orc
            System.out.println("Friendly: Orc doesn't have enough rage: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Friendly (unexpected runtime): " + e.getMessage());
        }

        // ----- Final status of each creature -----
        System.out.println();
        System.out.println(LABEL_FINAL_STATUS);
        System.out.println(dragon.getDetails());
        System.out.println(elf.getDetails());
        System.out.println(orc.getDetails());
    }

    // Helper (no arrays/collections): prints details and runtime type info
    private static void printDetailsAndType(final Creature c) {
        System.out.println(c.getDetails());
        System.out.println(" - getClass(): " + c.getClass().getName());
        if (c instanceof Dragon) {
            System.out.println(" - instanceof: Dragon");
        } else if (c instanceof Elf) {
            System.out.println(" - instanceof: Elf");
        } else if (c instanceof Orc) {
            System.out.println(" - instanceof: Orc");
        }
        System.out.println();
    }
}
