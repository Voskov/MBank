package config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class ExportSettings {
    public static void main(String[] args) {
        Properties prop = new Properties();
        OutputStream output = null;

        try{
            output = new FileOutputStream("MBank.config");

            prop.setProperty("DB_TYPE", "Derby");
            prop.setProperty("DB_NAME", "MBank");
            prop.setProperty("DB_ADDRESS", "jdbc:derby://localhost:1527");

            prop.store(output,null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null){
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
