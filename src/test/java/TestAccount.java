public class TestAccount extends Account {
    public TestAccount(int ownerID, int accountID) {
        super(ownerID, accountID);
    }

    public TestAccount(int ownerID, int accountID, double initialBalance) {
        super(ownerID, accountID, initialBalance);
    }
}