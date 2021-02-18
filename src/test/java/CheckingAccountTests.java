import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckingAccountTests {

    private final int OWNERID = 1, ACCOUNTID = 1, INITIAL_BALANCE = 300;
    private final double EPSILON = .01;

    private CheckingAccount checkingAccount;
    private TestAccount otherAccount;

    @BeforeEach
    public void setup() {
        checkingAccount = new CheckingAccount(OWNERID, ACCOUNTID, INITIAL_BALANCE);
        otherAccount = new TestAccount(OWNERID, ACCOUNTID);
    }

    @Test
    public void checkingAccount_InheritsFromAccount_ReturnsTrue() {
        assertTrue(checkingAccount instanceof Account);
    }


    @Test
    public void writeACheck_WhenDaysAreMoreThanZero_DoesNotImmediatelyWithdrawl() {
        checkingAccount.writeACheck(10, otherAccount, 2);
        assertEquals(INITIAL_BALANCE, checkingAccount.getBalance(), EPSILON);
    }

    @Test
    public void recieveACheck_WhenDaysAreMoreThanZero_DoesNotImmediatelyDeposit() {
        checkingAccount.recieveACheck(10, otherAccount, 2);
        assertEquals(INITIAL_BALANCE, checkingAccount.getBalance(), EPSILON);
    }

    @Test
    public void advanceTime_whenCalled_ResolvesRelevantPendingTransactions() {
        checkingAccount.writeACheck(10, otherAccount, 2);
        checkingAccount.recieveACheck(20, otherAccount, 2);
        checkingAccount.writeACheck(10, otherAccount, 3);

        checkingAccount.advanceTime(2);

        assertEquals(INITIAL_BALANCE + 10, checkingAccount.getBalance(), EPSILON);
    }

    
}
