// @C. Prickartz

package org.pearharmony.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Contacts {

    private final String contactsFilename = "contacts.pearharmony.file.dfwrg43t45dfgASDu3rufsuZGSUFefwefwfw3r";
    private String[] contacts = { // contacts array always contains sample entry = do not remove!
            "ThisIsASampleEntryToShowHowThisFileFormatWorks_ThisIsTheUsername",
            "ThisIsASampleEntryToShowHowThisFileFormatWorks_ThisIsTheIP"
    };

    public Contacts() {
        createContactFile();
        readContactsFromDisk();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void createContactFile() { // create new contact file if it is no found
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

    private void readContactsFromDisk() { // read contact file from disk
        String contactFileData;

        try {
            File contacts = new File(contactsFilename);
            Scanner contactsRead = new Scanner(contacts);
            contactFileData = contactsRead.nextLine();
        } catch (IOException e) {
            throw new RuntimeException("Something is dramatically wrong, the software is no longer usable. To fix the error, restart the software");
        }
        contacts = contactFileData.split("#+"); // split contact string in array at control char '#'
    }

    private void writeContactsToDisk() { // write file to disk
        StringBuilder contactFileData = new StringBuilder();
        for (String contact : contacts) { // convert contacts array to single string
            contactFileData.append(contact).append("#");
        }
        contactFileData.deleteCharAt(contactFileData.length() - 1); // remove last char -> always '#'

        try { // write to disk
            File contacts = new File(contactsFilename);
            FileWriter contactsWrite = new FileWriter(contacts);
            contactsWrite.write(String.valueOf(contactFileData));
            contactsWrite.close();
        } catch (IOException e) {
            System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
        }
    }

    private String[] add2RowsToArray(String[] contacts) { // add to rows to array and return
        return Arrays.copyOf(contacts, contacts.length + 2);
    }

    public String getContactIP(String name) {
        // # -> control char = contact not found

        // get position of name in array -> add one for address index
        int posContactIP = Arrays.asList(contacts).indexOf(name.toLowerCase()) + 1;

        if (posContactIP != 0) { // check if lookup was successful
            return contacts[posContactIP];
        } else {
            return "#";
        }
    }

    public String getContactName(String ip) {
        // # -> control char = contact not found

        // get position of ip in array -> subtracted one for name index
        int posContactIP = Arrays.asList(contacts).indexOf(ip.toLowerCase()) - 1;

        if (posContactIP > 0) { // check if lookup was successful
            return contacts[posContactIP];
        } else {
            return "#";
        }
    }

    public int addContact(String name, String ip) {
        // 0 -> success
        // 1 -> contact already existing or identical with other contact (ip or name)
        // 2 -> contact contains unsupported chars

        // check if name is valid
        if (name.matches("#") || ip.matches("#")) {
            return 2;
        }

        for (int i = contacts.length - 1; i >= 0; i--) { // check for duplicates
            if (contacts[i].equalsIgnoreCase(name) || contacts[i].equalsIgnoreCase(ip)) {
                return 1;
            }
        }

        int contactsAltMax = contacts.length;
        contacts = add2RowsToArray(contacts); // enlarge array

        // add contact in to newly created space
        contacts[contactsAltMax] = name.toLowerCase();
        contacts[contactsAltMax + 1] = ip.toLowerCase();

        writeContactsToDisk();

        return 0;
    }

    public int removeContact(String name) {
        // 0 -> success
        // 1 -> entry no found

        // search for contact
        int posContact = Arrays.asList(contacts).indexOf(name.toLowerCase());

        if (posContact == -1 || posContact == contacts.length - 1) { // check if search was successful
            return 1;
        } else { // remove contact from array and write to disk
            contacts[posContact + 1] = "";
            contacts[posContact] = "";
            writeContactsToDisk();
            return 0;
        }
    }
}
