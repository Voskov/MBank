package main;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Einstine on 30/05/2014.
 */
public class MBank {

    protected static Logger LOGGER = Logger.getLogger(MBank.class.getName());

    private static MBank mBank;

    public static MBank getMBank(){
        if (mBank == null){
            mBank = new MBank();
            LOGGER.log(Level.INFO, "Creating MBank singleton");
        }
        LOGGER.log(Level.INFO, "Returning MBank singleton");
        return mBank;
    }
}
