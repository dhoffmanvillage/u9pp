import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SavingsAccountTests {

    private final int OWNERID = 1, ACCOUNTID = 1, INITIAL_BALANCE = 300;
    private final double INTEREST_RATE = .1, EPSILON = .01;

    private SavingsAccount savingsAccount;

    @BeforeEach
    public void setup() {
        savingsAccount = new SavingsAccount(OWNERID, ACCOUNTID, INTEREST_RATE, INITIAL_BALANCE);
    }

    @Test
    public void savingsAccount_InheritsFromAccount_ReturnsTrue() {
        assertTrue(savingsAccount instanceof Account);
    }

    @Test
    public void advanceTime_WhenLessThanAYearHasPassed_ShouldNotChangeBalance() {
        int daysAdvanced = 100;

        savingsAccount.advanceTime(daysAdvanced);

        assertEquals(INITIAL_BALANCE, savingsAccount.getBalance(), EPSILON);
    }

    @Test
    public void advanceTime_WhenMoreThanAYearHasPassed_ShouldUsePertForAccruingInterest() {
        int daysAdvanced = 365 * 2;
        double expectedBalance = INITIAL_BALANCE * Math.exp(INTEREST_RATE * 2);

        savingsAccount.advanceTime(daysAdvanced);
        
        assertEquals(expectedBalance, savingsAccount.getBalance(), EPSILON);
    }
    
}
