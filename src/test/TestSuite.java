package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccountManagerTest.class, ClientManagerTest.class, ActivityManagerImplTest.class, DepositManagerTest.class})
public class TestSuite {

}