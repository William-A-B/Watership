package uk.watership;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileManager {

    public FileManager() {

    }

    public String getBotToken() {
        String botToken = "";

        try {
            File tokenFile = new File("botToken.txt");
            Scanner tokenReader = new Scanner(tokenFile);

            while (tokenReader.hasNextLine() == true) {
                botToken = tokenReader.nextLine();
            }
            tokenReader.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Error Occured");
            e.printStackTrace();
        }
        return botToken;
    }
}
