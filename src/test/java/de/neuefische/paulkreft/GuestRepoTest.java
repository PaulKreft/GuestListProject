package de.neuefische.paulkreft;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GuestRepoTest {

    @Test
    public void getGuestListTest_whenCreated_thenEmpty() {
        // GIVEN
        GuestRepo emptyGuestRepo = new GuestRepo();
        emptyGuestRepo.setGuestList(new HashMap<>());

        Map<String, Guest> emptyGuestList = emptyGuestRepo.getGuestList();

        // THEN
        assertTrue(emptyGuestList.isEmpty());
    }

    @Test
    public void getGuestListTest_whenSaveKarlAndUte_thenMapContainsKarlAndUte() {
        // GIVEN
        GuestRepo guestRepo = new GuestRepo();
        guestRepo.addGuest("Karl");
        guestRepo.addGuest("Ute");

        Map<String, Guest> guestList = guestRepo.getGuestList();
        List<String> guestNames = guestList.values().stream().map(Guest::name).toList();

        // THEN
        assertTrue(guestNames.contains("Karl") && guestNames.contains("Ute"));
    }

    @Test
    public void addGuestTest_whenSaveTheodoreAndAnnette_thenTxtFileContainsTheodoreAndAnnette() {
        // GIVEN
        GuestRepo guestRepo = new GuestRepo();
        guestRepo.addGuest("Theodore");
        guestRepo.addGuest("Annette");

        Path fileName = Path.of("src/main/resources/guests.txt");

        List<String> allLines = new ArrayList<>();

        try {
            allLines = Files.readAllLines(fileName);
        } catch (IOException exception) {
            System.out.println("Some error while testing guests.txt");
        }

        // THEN
        assertTrue(allLines.contains("Theodore") && allLines.contains("Annette"));
    }

    @Test
    void getGuestsFromFileTest_whenStephanAndMaxWrittenWrittenToGuestsTxt_thenReadsStephanAndMax() throws FileNotFoundException {
        // GIVEN
        GuestRepo guestRepo = new GuestRepo();

        String stephan = "Stephan";
        String max = "Max";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/guests.txt"))) {
            writer.write(stephan + "\n");
            writer.write(max + "\n");
        } catch (IOException exception) {
            System.out.println("Could not write " + stephan + " and " + max + " to guest list");
        }

        // WHEN
        List<String> namesInGuestsTxt = guestRepo.getGuestsFromFile("guests.txt");

        // THEN
        assertTrue(namesInGuestsTxt.contains("Stephan") && namesInGuestsTxt.contains("Max"));
    }

    @Test
    void getGuestsFromFileTest_whenReadGuestTxt_thenFileNotFoundException() {
        // GIVEN
        GuestRepo guestRepo = new GuestRepo();

        assertThrows(
                // WHEN
                FileNotFoundException.class,
                // THEN
                () -> guestRepo.getGuestsFromFile("guest.txt"));
    }
}