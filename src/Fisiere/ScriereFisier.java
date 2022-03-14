package Fisiere;

import java.io.FileWriter;
import java.io.IOException;

public class ScriereFisier {
    /*
    Scrie logul aventurii in fisierul cu numele specificat
    */
    public void scriereLog(String fileName, StringBuilder log) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(String.valueOf(log));
            fileWriter.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
