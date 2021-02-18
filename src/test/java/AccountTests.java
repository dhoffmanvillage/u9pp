import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.lang.reflect.*;

public class AccountTests {

    private Account testAccountWithBalance, testAccountWithoutBalance;
    private final int OWNERID = 1, ACCOUNTID = 1, INITIAL_BALANCE = 300;
    private final double EPSILON = .01;

    @BeforeEach 
    public void setup() {
        testAccountWithoutBalance = new TestAccount(OWNERID, ACCOUNTID);
        testAccountWithBalance = new TestAccount(OWNERID, ACCOUNTID, INITIAL_BALANCE);
    }

    @Test
    public void account_whenCheckModifierIsAbstract_returnTrue() {
        Class<Account> account = Account.class;

        assertTrue(Modifier.isAbstract(account.getModifiers()));
    }

    @Test
    public void constructor_whenCalledWithThreeParameters_setsTheBalance() {
        assertEquals(INITIAL_BALANCE, testAccountWithBalance.getBalance(), EPSILON);
    }

    @Test
    public void deposit_whenCalledWithAPositiveNumber_returnsTheBalanceIncreasedByThatAmount() {
        int depositAmt = 10;
        double newBalance = testAccountWithBalance.deposit(depositAmt);

        assertEquals(INITIAL_BALANCE + depositAmt, newBalance, EPSILON);
    }

    @Test
    public void deposit_whenCalledWithANegativeNumber_returnsTheBalanceDecreasedByThatAmount() {
        int depositAmt = -10;
        double newBalance = testAccountWithBalance.deposit(depositAmt);

        assertEquals(INITIAL_BALANCE + depositAmt, newBalance, EPSILON);
    }

    @Test
    public void withdrawl_whenCalledWithAPositiveNumber_returnsTheBalanceIncreasedByThatAmount() {
        int withdrawlAmt = 10;
        double newBalance = testAccountWithBalance.withdrawl(withdrawlAmt);

        assertEquals(INITIAL_BALANCE - withdrawlAmt, newBalance, EPSILON);
    }

    @Test
    public void withdrawl_whenCalledWithANegativeNumber_returnsTheBalanceDecreasedByThatAmount() {
        int withdrawlAmt = -10;
        double newBalance = testAccountWithBalance.withdrawl(withdrawlAmt);

        assertEquals(INITIAL_BALANCE - withdrawlAmt, newBalance, EPSILON);
    }

    @Test
    public void transfer_whenCalled_ShouldWithdrawlFromCallingAccountAndDepositInParameterAccount() {
        int transferAmt = 10;
        double newCallingBalance = testAccountWithBalance.transfer(transferAmt, testAccountWithoutBalance);
        double newReceivingBalance = testAccountWithoutBalance.getBalance();

        assertAll(
            () -> assertEquals(INITIAL_BALANCE - transferAmt, newCallingBalance, EPSILON),
            () -> assertEquals(transferAmt, newReceivingBalance, EPSILON)
        );
    }

    @Test
    public void isOverdraft_wheCalledWithAPositiveBalance_shouldReturnFalse() {
        assertFalse(testAccountWithBalance.isOverdraft());
    }

    @Test
    public void isOverdraft_wheCalledWithANegativeBalance_shouldReturnTrue() {
        testAccountWithoutBalance.withdrawl(10);
        assertTrue(testAccountWithoutBalance.isOverdraft());
    }


}
