package com.mycompany.mycontacts;

import javax.swing.*;

public class Person {

    private String name;
    private String mobilePhone;
    private String work;
    private String email;
    boolean valid = true;

    public Person(String name, String mobilePhone, String work, String email) {
        setName(name);
        setMobilePhone(mobilePhone);
        setWork(work);
        setEmail(email);
    }

    public Person(String name, String mobilePhone, String email) {
        setName(name);
        setMobilePhone(mobilePhone);
        setEmail(email);
    }

    public Person(String name) {
        setName(name);
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The name cannot be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (name.length() > 15) {
            JOptionPane.showMessageDialog(null, "The name cannot be greater than 10 letters", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else {
            for (char x : name.toCharArray()) {
                if (!Character.isLetter(x) && x != ' ') {
                    JOptionPane.showMessageDialog(null, "The name cannot contain numbers or symbols", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    valid = false;
                    break;
                }
            }
            if (valid) {
                this.name = name;
            }
        }
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        if (mobilePhone.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The mobile phone cannot be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (mobilePhone.trim().length() != 11) {
            JOptionPane.showMessageDialog(null, "The mobile phone must be 11 number", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (!mobilePhone.startsWith("010") && !mobilePhone.startsWith("011") && !mobilePhone.startsWith("012")
                && !mobilePhone.startsWith("015")) {
            JOptionPane.showMessageDialog(null, "The email can not be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println("This number is not an egyptian number so it is invalid");
            valid = false;
        }
        for (char x : mobilePhone.toCharArray()) {
            if (!Character.isDigit(x)) {
                JOptionPane.showMessageDialog(null, "The mobile phone cannot be Symbols or letter", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                valid = false;
                break;
            }
        }
        if (valid) {
            this.mobilePhone = mobilePhone.trim();
        }
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {

        if (work.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The work phone cannot be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (work.trim().length() <= 5 && work.trim().length() >= 11) {
            JOptionPane.showMessageDialog(null, "The work phone invalid", "ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        for (char x : work.toCharArray()) {
            if (!Character.isDigit(x)) {
                JOptionPane.showMessageDialog(null, "The home phone cannot be Symbols or letter", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                valid = false;
                break;
            }
        }
        if (valid) {
            this.work = work.trim();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_.+-/#/%{3,15}]+@[a-zA-Z]{5}+.[a-zA-Z]{3}+$";

        if (email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "The email can not be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (email.matches(emailRegex)) {
            this.email = email.trim();
        } else {
            JOptionPane.showMessageDialog(null, "The Email is invalid", "ERROR", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }
    }
}
