package de.neuefische.paulkreft;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class GuestRepo {
    private Map<String, Guest> guestList;
    private String guestsFile;

    public GuestRepo() {
        guestList = new HashMap<>();
        guestsFile = "guests.txt";

        createGuestsTxt();
    }

    private void createGuestsTxt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/" + guestsFile))) {
            writer.write("Guest List" + "\n");
        } catch (IOException exception) {
            System.out.println("Problem writing " +guestsFile);
        }
    }

    private void writeGuestToFile(String name) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + guestsFile))) {
            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.out.println("Error while reading guest list");
        }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/" + guestsFile))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }

            writer.write(name + "\n");
        } catch (IOException exception) {
            System.out.println("Could not write " + name + " to guest list");
        }
    }

    public void addGuest(String name) {
        Guest guest = new Guest(UUID.randomUUID().toString(), name);
        writeGuestToFile(name);

        guestList.put(guest.id(), guest);
    }

    public List<String> getGuestsFromFile(String fileName) throws FileNotFoundException {
        List<String> guests = new ArrayList<>();

        if (!fileName.equals(guestsFile)) {
            throw new FileNotFoundException();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName))) {
            String line = reader.readLine();

            while (line != null) {
                if (line.equals("Guest List")) {
                    line = reader.readLine();
                    continue;
                }

                guests.add(line);
                line = reader.readLine();
            }
        } catch (IOException exception) {
            System.out.println("Error while reading guest list");
        }

        return guests;
    }

    public void setGuestList(Map<String, Guest> guestList) {
        this.guestList = guestList;
    }

    public Map<String, Guest> getGuestList() {
        return guestList;
    }

}
