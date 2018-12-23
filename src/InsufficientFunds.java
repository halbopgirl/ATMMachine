//File: InsufficientFunds.java
//Author: Haleigh Jayde Doetschman
//Date: Feb 2, 2018
//Purpose: Creates user defined exception InsufficientFunds

public class InsufficientFunds extends Exception {

    //user defined checked exception
    public InsufficientFunds() {
    }

    public InsufficientFunds(Throwable funds) {
        super(funds);
    }
}
