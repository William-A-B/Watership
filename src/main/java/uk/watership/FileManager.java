package uk.watership;

import java.io.InputStream;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileManager {

    public FileManager() {

    }

    public String getBotToken() {
        String botToken = "";

        InputStream file = ClassLoader.getSystemResourceAsStream("botToken.txt");
//            File tokenFile = new File("/botToken.txt");
        Scanner tokenReader = new Scanner(file);

        while (tokenReader.hasNextLine() == true) {
            botToken = tokenReader.nextLine();
        }
        tokenReader.close();
        return botToken;
    }
}
