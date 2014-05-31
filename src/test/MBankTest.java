package test;

import main.services.MBank;
import org.junit.Test;

/**
 * Created by Einstine on 30/05/2014.
 */
public class MBankTest {

    @Test
    public void testMBankIsSingleton(){
        MBank bank = MBank.getMBank();
        MBank bank2 = MBank.getMBank();

       // TODO LATER ON, WHEN OUR SINGELTON WILL HAVE SOME FIELDS AND WILL STORE DATA,
       // WE CAN TEST THAT WHEN UPDATING SOME FIELD FROM OBJECT 1, IT WILL BE UPDATED  FOR OBJECT 2 AS WELL


    }
}
