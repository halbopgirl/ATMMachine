//File: ATMMachine.java
//Author: Haleigh Jayde Doetschman
//Date: Feb 2, 2018
//Purpose: Creates and implements an ATM Machine Object

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class ATMMachine extends JPanel {

    //Create items for GUI
    //Buttons
    private JButton withdraw = new JButton("Withdraw");
    private JButton deposit = new JButton("Deposit");
    private JButton transferTo = new JButton("Transfer to");
    private JButton balance = new JButton("Balance");
    //Radio Buttons
    private JRadioButton checking = new JRadioButton("Checking");
    private JRadioButton savings = new JRadioButton("Savings");
    //Output Text Field
    private JTextField enterAmount = new JTextField("");
    
    //Currency Value Format
        DecimalFormat currency = new DecimalFormat("0.00");

//Constructor to build GUI 
    public ATMMachine() {
        setLayout(new BorderLayout());
        

//buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonsPanel2 = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonsPanel3 = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        withdraw.setToolTipText("Withdraw funds");
        deposit.setToolTipText("Deposit funds");
        transferTo.setToolTipText("Transfer to selected account");
        balance.setToolTipText("View account balance");
        buttonsPanel.add(withdraw);
        buttonsPanel.add(deposit);
        buttonsPanel2.add(transferTo);
        buttonsPanel2.add(balance);
        buttonsPanel3.add(checking);
        buttonsPanel3.add(savings);
        inputPanel.add(enterAmount);
        enterAmount.setPreferredSize(new Dimension(180, 30));
        
        
        
        //io panel
        JPanel ATMPanel = new JPanel();
        ATMPanel.setLayout(new GridLayout(4, 10));
        ATMPanel.add(buttonsPanel);
        ATMPanel.add(buttonsPanel2);
        ATMPanel.add(buttonsPanel3);
        ATMPanel.add(inputPanel);
        add(ATMPanel, BorderLayout.CENTER);

//Radio Button Management
        ButtonGroup radios = new ButtonGroup();
        radios.add(checking);
        radios.add(savings);
        
        

//Button Event Handlers
//withdraw event handler
        withdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double fundsNeeded = 0;
                try {
                    fundsNeeded = getAmount();
                    if (fundsNeeded % 20 != 0) {
                        JOptionPane.showMessageDialog(null, "Withdrawals Must Be in $20 Increments");
                    } else {
                        if (checking.isSelected()) {
                            double fundsAvailable = Account.getCheckingPlusCharges();
                            /*if (fundsNeeded > fundsAvailable) {
                                throw new InsufficientFunds();
                            }*/
                            double newBalance = Account.withdrawMethod(fundsNeeded, "checking");
                            JOptionPane.showMessageDialog(null, "Withdrawal succeeded!\nYour new balance is: $" + (currency.format(newBalance)));
                        } else if (savings.isSelected()) {
                            double fundsAvailable = Account.getSavingsPlusCharges();
                            /*if (fundsNeeded > fundsAvailable) {
                                throw new InsufficientFunds();
                            }*/
                            double newBalance = Account.withdrawMethod(fundsNeeded, "savings");
                            JOptionPane.showMessageDialog(null, "Withdrawal succeeded!\nYour new balance is: $" + (currency.format(newBalance)));
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select an account");
                        }
                    }
                } catch (InsufficientFunds exception) {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds for This Transaction");
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Not a Valid Amount");
                }
                enterAmount.setText("");
            }
        }
        );

//deposit event handler
        deposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double fundsAdded = 0;
                try {
                    fundsAdded = getAmount();
                    if (checking.isSelected()) {
                        double newBalance = Account.depositMethod(fundsAdded, "checking");
                        JOptionPane.showMessageDialog(null, "Deposit succeeded!\nYour new balance is: $" + (currency.format(newBalance)));
                    }
                    else if (savings.isSelected()) {
                        double newBalance = Account.depositMethod(fundsAdded, "savings");
                        JOptionPane.showMessageDialog(null, "Deposit succeeded!\nYour new balance is: $" + (currency.format(newBalance)));
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select an account");
                    }
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Not a Valid Amount");
                }
                enterAmount.setText("");
            }
        }
        );

//transfer event handler
        transferTo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double fundsNeeded = 0;
                try {
                    fundsNeeded = getAmount();
                    if (checking.isSelected()) {
                        double fundsAvailable = Account.getSavings();
                        if (fundsNeeded > fundsAvailable) {
                            throw new InsufficientFunds();
                        }
                        double newBalance = Account.transferToMethod(fundsNeeded, "checking");
                        JOptionPane.showMessageDialog(null, "Transfer succeeded!\nYour new balance is: $" + (currency.format(newBalance)));
                    }
                    else if (savings.isSelected()) {
                        double fundsAvailable = Account.getChecking();
                        if (fundsNeeded > fundsAvailable) {
                            throw new InsufficientFunds();
                        }
                        double newBalance = Account.transferToMethod(fundsNeeded, "savings");
                        JOptionPane.showMessageDialog(null, "Transfer succeeded!\nYour new balance is: $" + (currency.format(newBalance)));
                    } else {
                       JOptionPane.showMessageDialog(null, "Please Select Account"); 
                    }
                } catch (InsufficientFunds exception) {
                    JOptionPane.showMessageDialog(null, "Insufficient Funds for This Transaction");
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Not a Valid Amount");
                }
                enterAmount.setText("");
            }
        }
        );

//balance event handler
        balance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (checking.isSelected()) {
                    double balance = Account.balanceMethod("checking");
                    JOptionPane.showMessageDialog(null, "Your checking account balance is: $" + (currency.format(balance)));
                }
                else if (savings.isSelected()) {
                    double balance = Account.balanceMethod("savings");
                    JOptionPane.showMessageDialog(null, "Your savings account balance is: $" + (currency.format(balance)));
                } else {
                    JOptionPane.showMessageDialog(null, "Please Select Account"); 
                }
                enterAmount.setText("");
            }
        }
        );
    }//end ATMMachinePanel

    public void display() {
        setVisible(true);
    }

    double getAmount() {
        double getAmount = Double.parseDouble(enterAmount.getText());
        double amount = Double.parseDouble(currency.format(getAmount));
        return amount;
    }

    public static void main(String[] args) {
        Account checkingAccount = new Account("checking");
        Account savingsAccount = new Account("savings");

        ATMMachine myATM = new ATMMachine();
        ATMMachineFrame myFrame = new ATMMachineFrame("ATM Machine", 300, 180);
        myFrame.add(myATM);
        myFrame.display();

    }//end main

}//end class

