package org.hillel;
import org.hillel.homework_1.inMemoryJourneyServiceTable;

public class Starter {
    public static void main(String[] args) throws Exception {

        System.out.println("HomeWork");

        final inMemoryJourneyServiceTable inMemory = new inMemoryJourneyServiceTable();
        inMemory.find("Odessa", "Lviv");
        inMemory.find("Odessa", "Kiev");
        inMemory.find("Kiev", "Lviv");
        inMemory.find("Kiev", "Odessa");
    }
}
