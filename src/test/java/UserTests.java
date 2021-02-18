import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTests {
    private User testUser;
    private final int DEFAULT_USERID = 1, DEFAULT_PIN = 1234;

    @BeforeEach
    public void setup() {
        testUser = new User(DEFAULT_USERID, DEFAULT_PIN);
    }

    @Test
    public void changePin_whenCalled_ShouldUpdateThePin() {
        int newPin = 98124156;

        testUser.setPin(newPin);

        assertEquals(newPin, testUser.getPin());
    }

    @Test
    public void openAccount_whenCalled_ShouldAddTheAccountToTheUsersList() {
        int id = testUser.getID();
        Account accountOne = new CheckingAccount(id, 1);
        Account accountTwo = new SavingsAccount(id, 2, .1);

        testUser.openAccount(accountOne);
        testUser.openAccount(accountTwo);

        assertThat(testUser.getAccounts(), containsInAnyOrder(accountOne, accountTwo));
    }

    @Test
    public void closeAccount_whenCalled_ShouldRemoveTheAccountFromTheList() {
        int id = testUser.getID();
        Account accountOne = new CheckingAccount(id, 1);
        Account accountTwo = new SavingsAccount(id, 2, .1);

        testUser.openAccount(accountOne);
        testUser.openAccount(accountTwo);
        testUser.closeAccount(accountOne);

        assertAll(
            () -> assertThat(testUser.getAccounts(), contains(accountTwo)),
            () -> assertThat(testUser.getAccounts(), not(contains(accountOne)))
        );
    }
}