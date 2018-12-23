//File: Account.java
//Author: Haleigh Jayde Doetschman
//Date: Feb 2, 2018
//Purpose: Creates account objects and contains methods for modifying and
//viewing account content

public class Account {

    private static double checkingBalance = 0;
    private static double savingsBalance = 0;
    private static int totalWithdrawals = 0;
    private static final double SERVICECHARGE = 1.5;
    private String account = "";

    /* Account(double balance) {
        checkingBalance = balance;
    }
     */
    Account(String acctName) {
        this.account = acctName;
        double balance = balanceMethod(acctName);
    }

    public static double withdrawMethod(double withdrawAmount, String account) throws InsufficientFunds {
        if (account.equalsIgnoreCase("checking")) {
            totalWithdrawals++;
            if (totalWithdrawals > 4) {
                if (checkingBalance >= (SERVICECHARGE + withdrawAmount)) {
                    checkingBalance -= (SERVICECHARGE + withdrawAmount);
                } else {
                    throw new InsufficientFunds();
                }
            } else {
                if (checkingBalance >= withdrawAmount) {
                    checkingBalance -= withdrawAmount;
                } else {
                    throw new InsufficientFunds();
                }
            }
            return checkingBalance;
        } else if (account.equalsIgnoreCase("savings")) {
            totalWithdrawals++;
            if (totalWithdrawals > 4) {
                if (savingsBalance >= (SERVICECHARGE + withdrawAmount)) {
                    savingsBalance -= (SERVICECHARGE + withdrawAmount);
                } else {
                    throw new InsufficientFunds();
                }
            } else {
                if (savingsBalance >= (withdrawAmount)) {
                    savingsBalance -= withdrawAmount;
                } else {
                    throw new InsufficientFunds();
                }
            }
            return savingsBalance;
        } else {
            return 0;
        }
    }

    public static double depositMethod(double depositAmount, String account) {
        if (account.equalsIgnoreCase("checking")) {
            checkingBalance += depositAmount;
            return checkingBalance;
        } else if (account.equalsIgnoreCase("savings")) {
            savingsBalance += depositAmount;
            return savingsBalance;
        } else {
            return 0;
        }
    }

    public static double transferToMethod(double transferAmount, String account) {
        if (account.equalsIgnoreCase("checking")) {
            checkingBalance += transferAmount;
            savingsBalance -= transferAmount;
            return checkingBalance;
        } else if (account.equalsIgnoreCase("savings")) {
            savingsBalance += transferAmount;
            checkingBalance -= transferAmount;
            return savingsBalance;
        } else {
            return 0;
        }
    }

    public static double balanceMethod(String account) {
        if (account.equalsIgnoreCase("checking")) {
            return checkingBalance;
        } else if (account.equalsIgnoreCase("savings")) {
            return savingsBalance;
        } else {
            return 0;
        }
    }

    public static double getCheckingPlusCharges() {
        if (totalWithdrawals > 4) {
            return checkingBalance + SERVICECHARGE;
        }
        return checkingBalance;
    }

    public static double getChecking() {
        return checkingBalance;
    }

    public static double getSavingsPlusCharges() {
        if (totalWithdrawals > 4) {
            return checkingBalance + SERVICECHARGE;
        }
        return savingsBalance;
    }

    public static double getSavings() {
        return savingsBalance;
    }

    //constructor
    //throw insufficientfunds exception in withdraw method
}
