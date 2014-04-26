package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImportDbSettings {
    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("MBank.config");

            prop.load(input);

            System.out.println(prop.getProperty("DB_NAME"));
            System.out.println(prop.getProperty("DB_TYPE"));
            System.out.println(prop.getProperty("DB_ADDRESS"));
            System.out.println(prop.getProperty("DB_ADDRESS") + "/" + prop.getProperty("DB_NAME"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public static Properties loadDbProperties(){
        Properties prop = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream("MBank.config");
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }
}
