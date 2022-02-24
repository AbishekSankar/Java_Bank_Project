package com.abishek;

//Importing all necessary packages

import java.awt.*;
import java.awt.event.*;
import java.awt.FlowLayout;
import java.sql.*;
import javax.swing.*;
import java.time.LocalDate;
import java.lang.Math;

public class Main extends JFrame implements ActionListener {
    // declaring all necessary variables
    static JLabel label1, label2, label3, sep, label4;
    static JButton sign, create, deposit, withdraw, trans, balance, close, clear, signOut, delete;
    static JTextField txtA1, txtA2, txtA3;
    static Statement st1, st2, st3, st4;
    static ResultSet rs1;
    int numD, numF, numE;
    LocalDate today = LocalDate.now(); // to get system date
    String date = today.toString(); // converting object to string

    public Main() {
        super("DAB Bank");
        setLayout(new FlowLayout());
        try {
            // Checking connection with database
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
            System.out.println("Connection established");
            st1 = con.createStatement();
            rs1 = st1.executeQuery("select * from details");
        } catch (Exception e) {
            System.out.println("there was some error in establishing connection : " + e);
        }

        sep = new JLabel("WELCOME TO DAB BANK");
        add(sep);
        sep.setFont(new Font("Times New Roman", Font.BOLD, 35));
        sep = new JLabel("Existing User SignIn  :  ");
        add(sep);
        sep.setFont(new Font("Times New Roman", Font.BOLD, 30));
        label1 = new JLabel(" Account Number : ");
        add(label1);
        label1.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        txtA1 = new JTextField(15);
        add(txtA1);
        txtA1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label2 = new JLabel("        Name:          ");
        add(label2);
        label2.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        txtA2 = new JTextField(20);
        add(txtA2);
        txtA2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label4 = new JLabel("        Password:          ");
        add(label4);
        label4.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        txtA3 = new JTextField(20);
        add(txtA3);
        txtA3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        sign = new JButton("\nSIGN IN");
        sign.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        add(sign);
        signOut = new JButton("SIGN OUT");
        add(signOut);
        signOut.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        sep = new JLabel("New User SignIn  :  ");
        add(sep);
        sep.setFont(new Font("Times New Roman", Font.BOLD, 30));
        create = new JButton("CREATE NEW ACCOUNT");
        add(create);
        create.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label3 = new JLabel("                         Account Operations:                        ");
        add(label3);
        label3.setFont(new Font("Times New Roman", Font.BOLD, 30));
        deposit = new JButton("DEPOSIT");
        add(deposit);
        deposit.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        withdraw = new JButton("WITHDRAW");
        add(withdraw);
        withdraw.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        trans = new JButton("TRANSACTION");
        add(trans);
        trans.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        balance = new JButton("BALANCE");
        add(balance);
        balance.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        delete = new JButton("DELETE ACCOUNT");
        add(delete);
        delete.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        sep = new JLabel("                    To Clear:                  ");
        sep.setFont(new Font("Times New Roman", Font.BOLD, 30));
        add(sep);
        clear = new JButton("CLEAR");
        add(clear);
        clear.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        sep = new JLabel("                    To Exit:                  ");
        sep.setFont(new Font("Times New Roman", Font.BOLD, 30));
        add(sep);
        close = new JButton("CLOSE");
        add(close);
        close.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        // create,deposit,withdraw,trans,balance,clear,close,sign,signOut,delete are
        // connected to action-event
        create.addActionListener(this);
        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        trans.addActionListener(this);
        balance.addActionListener(this);
        clear.addActionListener(this);
        close.addActionListener(this);
        sign.addActionListener(this);
        signOut.addActionListener(this);
        delete.addActionListener(this);
        deposit.setEnabled(false);
        withdraw.setEnabled(false);
        trans.setEnabled(false);
        balance.setEnabled(false);
        signOut.setEnabled(false);
        label3.setEnabled(false);
        delete.setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 850);
        setVisible(true);
        setResizable(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            int rand = (int) (Math.random() * 9000 + 1000);// to generate random number
            String strB = "" + rand;
            String strA = JOptionPane.showInputDialog(null, "Please Enter Your NAME:");
            String strC = JOptionPane.showInputDialog(null, "Please Enter Your PASSWORD:");
            String strD = JOptionPane.showInputDialog(null, "Please Re-Enter Your PASSWORD:");

            if (!(strC.equals(strD)))// continues if both password is same
            {
                JOptionPane.showMessageDialog(null, "Sorry Cannot Create Account!!!!\nYour Password is Incorrect");

                deposit.setEnabled(false);
                withdraw.setEnabled(false);
                trans.setEnabled(false);
                balance.setEnabled(false);
                create.setEnabled(true);
                sign.setEnabled(true);
                signOut.setEnabled(false);

            } else if (strA == null)// continues if all the values are not left blank
            {
                JOptionPane.showMessageDialog(null,
                        "Sorry Cannot Create Account!!!!\nEither any one or both the values were left blank");

                deposit.setEnabled(false);
                withdraw.setEnabled(false);
                trans.setEnabled(false);
                balance.setEnabled(false);
                create.setEnabled(true);
                sign.setEnabled(true);
                signOut.setEnabled(false);
            } else {
                try {
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                    st2 = con.createStatement();
                    st2.executeUpdate("insert into details(UserName,AccNum,Password,Balance,Deposit,Withdraw)values('"
                            + strA + "','" + strB + "','" + strD + "',0,0,0)");
                    st2.close();
                    JOptionPane.showMessageDialog(null,
                            "ACCOUNT Created Successfully\nNew Account details:\nAccount Number:" + strB + "\nUsername:"
                                    + strA +
                                    "\nBalance:0");
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null,
                            " There is already a user with same ACCOUNT NUMBER ,Give another ACCOUNT NUMBER ");
                }
                rand = (int) (Math.random() * 9000 + 1000);
                label3.setEnabled(false);
                deposit.setEnabled(false);
                withdraw.setEnabled(false);
                trans.setEnabled(false);
                balance.setEnabled(false);
                create.setEnabled(true);
                sign.setEnabled(true);
                signOut.setEnabled(false);
            }
        } else if (e.getSource() == sign) {
            String num = txtA1.getText();
            String pwd = txtA3.getText();
            String name = txtA2.getText();
            try {
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                st1 = con.createStatement();
                rs1 = st1.executeQuery("Select * from details where (AccNum='" + num + "' AND Password='" + pwd
                        + "' AND UserName='" + name + "')");
                rs1.next();
                txtA1.setText(rs1.getString("AccNum"));
                txtA2.setText(rs1.getString("UserName"));
                txtA3.setText("");
                JOptionPane.showMessageDialog(null, "Logged in Successfully!!");

                label3.setEnabled(true);
                deposit.setEnabled(true);
                withdraw.setEnabled(true);
                trans.setEnabled(true);
                balance.setEnabled(true);
                create.setEnabled(false);
                signOut.setEnabled(true);
                sign.setEnabled(false);
                clear.setEnabled(true);
                delete.setEnabled(true);
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Invalid Password or Invalid Account Number or Invalid UserName");
                label3.setEnabled(false);
                deposit.setEnabled(false);
                withdraw.setEnabled(false);
                trans.setEnabled(false);
                balance.setEnabled(false);
                create.setEnabled(false);
                signOut.setEnabled(false);
                sign.setEnabled(true);
                clear.setEnabled(true);
                delete.setEnabled(false);
            }
        } else if (e.getSource() == signOut) {
            JOptionPane.showMessageDialog(null, "Logged Out SUCCESSFULLY!!!");
            txtA1.setText("");
            txtA2.setText("");
            txtA3.setText("");
            label3.setEnabled(false);
            deposit.setEnabled(false);
            withdraw.setEnabled(false);
            trans.setEnabled(false);
            balance.setEnabled(false);
            create.setEnabled(true);
            sign.setEnabled(true);
            clear.setEnabled(true);
            signOut.setEnabled(false);
            delete.setEnabled(false);
        } else if (e.getSource() == deposit) {
            String strC = JOptionPane.showInputDialog(null, "Please enter the amount to be DEPOSITED");
            numD = Integer.parseInt(strC);
            if (numD <= 0) {
                JOptionPane.showMessageDialog(null, " NO Amount Deposited");
            } else {
                try {
                    Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                    int num, num1;
                    st3 = con.createStatement();
                    rs1 = st3.executeQuery("select * from details where Accnum like '" + txtA1.getText() + "'");
                    rs1.next();
                    num1 = rs1.getInt("Deposit");
                    num = rs1.getInt("Balance");

                    JOptionPane.showMessageDialog(null, "Amount Deposited Successfully , Amount is " + numD);
                    numF = num + numD;
                    num1 = num1 + numD;
                    st2 = con.createStatement();
                    String ss = "update details set Deposit=" + num1 + " where AccNum='" + txtA1.getText() + "'";
                    String aa = "update details set Balance=" + numF + " where AccNum='" + txtA1.getText() + "'";
                    st2.executeUpdate(ss);
                    st2.executeUpdate(aa);
                    st2.close();
                    st4 = con.createStatement();
                    st4.executeUpdate("insert into transactions(Tdate,UserName,AccNum,Withdraw,Deposit) values('" + date
                            + "','" + txtA2.getText() + "','" + txtA1.getText() + "',0," + numD + ")");
                    st4.close();
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Amount can not be DEPOSITED");
                    System.out.println(e2.getMessage());
                }
            }
        } else if (e.getSource() == withdraw) {
            try {
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                String strD = JOptionPane.showInputDialog(null, "Please enter the amount to be WITHDRAWN");
                numE = Integer.parseInt(strD);
                st3 = con.createStatement();
                rs1 = st3.executeQuery("select * from details where Accnum like '" + txtA1.getText() + "'");
                rs1.next();
                numF = rs1.getInt("Balance");
                if ((numF > numE) && (numE > 100)) {
                    int num1;
                    num1 = rs1.getInt("Withdraw");
                    JOptionPane.showMessageDialog(null, "Amount Withdrawn is " + numE);
                    numF = numF - numE;
                    num1 = num1 + numE;
                    st2 = con.createStatement();
                    String pp = "update details set Withdraw=" + num1 + " where AccNum='" + txtA1.getText() + "'";
                    String pp1 = "update details set Balance=" + numF + " where AccNum='" + txtA1.getText() + "'";
                    st2.executeUpdate(pp);
                    st2.executeUpdate(pp1);
                    st2.close();
                    st4 = con.createStatement();
                    st4.executeUpdate("insert into transactions(Tdate,UserName,AccNum,Withdraw,Deposit) values('" + date
                            + "','" + txtA2.getText() + "','" + txtA1.getText() + "'," + numE + ",0)");
                    st4.close();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Sorry can not Withdraw\nAvailable Denominations 100,200,500,2000");
                }
                st3.close();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, " Can't WITHDRAW ");
            }
        } else if (e.getSource() == balance) {
            String num = txtA1.getText();

            try {
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                st3 = con.createStatement();
                rs1 = st3.executeQuery("select * from details where Accnum like '" + num + "'");
                rs1.next();
                numF = rs1.getInt("Balance");
                st3.close();
                JOptionPane.showMessageDialog(null, "Balance is " + numF);
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, " Balance null ");
            }
        } else if (e.getSource() == trans) {
            JOptionPane.showMessageDialog(null, "Your all Transactions details\n");

            try {
                String num = txtA1.getText();
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                st3 = con.createStatement();
                rs1 = st3.executeQuery("select * from transactions where AccNum like '" + num + "'");
                while (rs1.next()) {
                    String tDate = rs1.getString("Tdate");
                    String uname = rs1.getString("UserName");
                    String aNum = rs1.getString("AccNum");
                    int w = rs1.getInt("Withdraw");
                    int d = rs1.getInt("Deposit");
                    if (w > 0 && d == 0)
                        JOptionPane.showMessageDialog(null,
                                "Transaction Date:  " + tDate + "\nUsername:  " + uname + "\nAccount Number:  " + aNum
                                        + "\nWithdraw amount:  " + w);
                    else
                        JOptionPane.showMessageDialog(null,
                                "Transaction Date:" + tDate + "\nUsername:" + uname + "\nAccount Number:" + aNum
                                        + "\nDeposit amount:  " + d);
                }
                st3.close();
            } catch (Exception e3) {
                JOptionPane.showMessageDialog(null, "Cannot view transactions");
            }
        } else if (e.getSource() == delete) {
            try {
                Connection con = DriverManager.getConnection("jdbc:ucanaccess://D:\\JavaProject\\ABD_Bank.accdb");
                st4 = con.createStatement();
                st4.executeUpdate("delete from details where AccNum='" + txtA1.getText() + "'");
                st4.close();
                st3 = con.createStatement();
                st3.executeUpdate("delete from transactions where AccNum='" + txtA1.getText() + "'");
                st3.close();
                JOptionPane.showMessageDialog(null, "Your Account deleted Successfully");
                txtA1.setText("");
                txtA2.setText("");
                txtA3.setText("");
                label3.setEnabled(false);
                deposit.setEnabled(false);
                withdraw.setEnabled(false);
                trans.setEnabled(false);
                balance.setEnabled(false);
                create.setEnabled(false);
                signOut.setEnabled(false);
                sign.setEnabled(true);
                clear.setEnabled(true);
                delete.setEnabled(false);

            } catch (Exception e4) {
                JOptionPane.showMessageDialog(null, "Sign In to delete Your Account:");
            }
        } else if (e.getSource() == clear) {
            txtA1.setText("");
            txtA2.setText("");
            txtA3.setText("");
            label3.setEnabled(false);
            deposit.setEnabled(false);
            withdraw.setEnabled(false);
            trans.setEnabled(false);
            balance.setEnabled(false);
            create.setEnabled(true);
            sign.setEnabled(true);
            delete.setEnabled(false);
        } else if (e.getSource() == close) {
            JOptionPane.showMessageDialog(null, "Thank-you for using DAB Bank!!!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
