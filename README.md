# Unit 9 Programming Project

In this programming project you are going to create an ATM system for a bank. Your ATM should be able to handle deposits, withdrawls, and transfers between various `Account`s.

You can create any helper methods and classes that you want to help you fulfill the requirements in the best style possible. Your code must have the following:

## Part A: `Account` Class

An `Account` is a base class that represents the functionality that all `Account`s have. However, you should not be able to create an instance of an `Account` (it should be `abstract`).

Each `Account` must have the following methods:

- An overloaded constructor that takes the ID of the account owner as the first parameter, the accountID as the second parameter, and an optional third paramter that can take an initial balance as a `double`
- `getBalance` that returns the amount of money in the account as a `double`
- `deposit` that takes a `double` and adds that value to the balance of the `Account` and returns that new balance
- `withdrawl` that takes a `double` and subtracts that value from the balance of the `Account` and returns that new balance
- `transfer` that takes a `double` as the first parameter and another `Account` as as the second parameter. It withdrawls the amount in the first parameter and deposits that same amount into the `Account` given in the second parameter and returns the new balance of the current `Account` (not the parameter)
- `getAccountNumber` which returns the ID number of this `Account`
- `getOwnerID` which returns the ID number of the owner of this `Account`
- `isOverdraft` which returns true if there is a negative balance
- `advanceTime` takes an int and moves the days opened of the `Account` forward by that amount.
- `getDaysOld` which tells you how old this `Account` is in days

## Part B: `CheckingAccount` Class

Create a `CheckingAccount`. A `CheckingAccount` is an `Account` but it also has the following methods:

- Constructors that follow the rules from `Account`
- `writeACheck` that takes a `double` as its first parameter, an `Account` as its second parameter, and an `int` as its third parameter. The check will "clear" in the number of days represented in the third parameter, at which point a transfer from the `CheckingAccount` into the `Account` will trigger.
- `receiveACheck` that takes a `double` as its first parameter, an `Account` as its second parameter, and an `int` as its third parameter. The check will "clear" in the number of days represented in the third parameter, at which point a transfer from the `Account` into this `CheckingAccount` will trigger.
- `advanceTime` overrides the `Account advanceTime`. Does the same functionality as the parent method, and also resolves any "pending" transactions.

## Part C: `SavingsAccount` Class

Create a `SavingsAccount`. A `SavingsAccount` is an `Account` but it also has the following methods:

- Constructors that follow the rules from `Account` but also take a starting interest rate as another parameter.
- `setInterestRate` which takes a `double` and changes the interest rate
- `getInterestRate` which returns the current interest rate
- `advanceTime` overrides the `Account advanceTime`. Does the same funcationlity as the parent method, and also increases the balance based on the interest rate. Use newBalance = balance(e^(interestRate*yearsOldAccountIs)) for your formula.

## Part D: `User` Class

Create a `User`. Every `User` contains an ID number, a PIN number, and an `ArrayList` of `Account`s associated with them. `User`s can change their PIN, but not their ID. They can also `closeAccout` an `Account` they own, or `openAccount` a new `Account`. Be sure toe include the following methods:

- `getID()`
- `getPin()`
- `getAccounts()`
- `setPin(int newPin)`
- `openAccount(Account newAccount)`
- `closeAccount(Account oldAccount)`

## Grading

- Attempted All Code: 15 points
- All Code is DRY: 15 points
- All Code Properly JavaDoc'ed: 20 points
- Passes All Test Cases: 50 points

## Bonus

Create an `ATM` that would allow someone to actually use and interact with this system
