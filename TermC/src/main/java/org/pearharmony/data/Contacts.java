package org.pearharmony.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Contacts {

    private final String contactsFilename = "contacts.pearharmony.file.dfwrg43t45dfgASDu3rufsuZGSUFefwefwfw3r";
    private String[] contacts = {
            "ThisIsASampleEntryToShowHowThisFileFormatWorks_ThisIsTheUsername",
            "ThisIsASampleEntryToShowHowThisFileFormatWorks_ThisIsTheIP"
    };

    public Contacts() {
        createContactFile();
        readContactsFromDisk();
    }

    private void createContactFile() {
        File contacts = new File(contactsFilename);
        if (!contacts.exists()) {
            try {
                contacts.createNewFile();
            } catch (IOException e) {
                System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
            }
            writeContactsToDisk();
        }
    }

    private void readContactsFromDisk() {
        String contactFileData;

        try {
            File contacts = new File(contactsFilename);
            Scanner contactsRead = new Scanner(contacts);
            contactFileData = contactsRead.nextLine();
        } catch (IOException e) {
            throw new RuntimeException("Something is dramatically wrong, the software is no longer usable. To fix the error, restart the software");
        }
        contacts = contactFileData.split("#+");
    }

    private void writeContactsToDisk() {
        StringBuilder contactFileData = new StringBuilder();
        for (String contact : contacts) {
            contactFileData.append(contact).append("#");
        }
        contactFileData.deleteCharAt(contactFileData.length() - 1);

        try {
            File contacts = new File(contactsFilename);
            FileWriter contactsWrite = new FileWriter(contacts);
            contactsWrite.write(String.valueOf(contactFileData));
            contactsWrite.close();
        } catch (IOException e) {
            System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
        }
    }

    private String[] add2RowsToArray(String[] contacts) {
        return Arrays.copyOf(contacts, contacts.length + 2);
    }

    public String getContactIP(String name) {
        // # -> control char = contact not found

        int posContactIP = Arrays.asList(contacts).indexOf(name) + 1;

        if (posContactIP != 0) {
            return contacts[posContactIP];
        } else {
            return "#";
        }
    }

    public int addContact(String name, String ip) {
        // 0 -> success
        // 1 -> contact already existing or identical with other contact (ip or name)
        // 2 -> contact contains unsupported chars

        if (name.matches("#") || ip.matches("#")) {
            return 2;
        }

        for (int i = contacts.length - 1; i >= 0; i--) {
            if (contacts[i].equals(name) || contacts[i].equals(ip)) {
                return 1;
            }
        }

        int contactsAltMax = contacts.length;
        contacts = add2RowsToArray(contacts);

        contacts[contactsAltMax] = name;
        contacts[contactsAltMax + 1] = ip;

        writeContactsToDisk();

        return 0;
    }

    public int removeContact(String name) {
        // 0 -> success
        // 1 -> entry no found

        int posContact = Arrays.asList(contacts).indexOf(name);

        if (posContact == -1 || posContact == contacts.length - 1) {
            return 1;
        } else {
            contacts[posContact + 1] = "";
            contacts[posContact] = "";
            writeContactsToDisk();
            return 0;
        }
    }
}
