package de.neuefische.paulkreft;

import java.io.FileNotFoundException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        GuestRepo guestRepo = new GuestRepo();


        guestRepo.addGuest("Karl");
        guestRepo.addGuest("Ute");
        guestRepo.addGuest("Sviat");
        guestRepo.addGuest("Paul");

        System.out.println(guestRepo.getGuestsFromFile("guests.txt"));
    }
}