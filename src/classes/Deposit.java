package classes;

import java.util.Date;

/**
 * Created by Ariel Voskov on 4/25/2014.
 */
public class Deposit {
    private long deposit_id;
    private long client_id;
    private double balance;
    private DepositType type;
    private long estimated_balance;
    private Date opening_date;
    private Date closing_date;
}
