package test;

import main.services.MBank;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MBankTest {

    @Test
    public void testMBankIsSingleton(){
        MBank bank = MBank.getMBank();
        MBank bank2 = MBank.getMBank();

        assertEquals(bank, bank2);
        assertEquals(bank.toString(), bank2.toString());

    }


}
