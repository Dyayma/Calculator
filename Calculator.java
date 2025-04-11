package com.mycompany.calculator;
import java.util.Scanner;


public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calcu calculator = new Calcu();  
        boolean isSecretUnlocked = false;

        System.out.println("Choose an operation:");
        System.out.println("1 - Add");
        System.out.println("2 - Subtract");
        System.out.println("3 - Multiply");
        System.out.println("4 - Divide");
        System.out.println("5 - Power (Requires passcode)");
        System.out.print("Enter your choice: ");

        String choiceStr = scanner.nextLine();

        if (choiceStr.matches("\\d+")) {
            int choice = Integer.parseInt(choiceStr);

            if (choice == 5) {
                System.out.println("Enter passcode to unlock the secret operation: ");
                String passcode = scanner.nextLine();
                String unlockResult = calculator.unlockSecret(passcode);
                System.out.println(unlockResult);

                isSecretUnlocked = calculator.isSecretUnlocked();

                if (isSecretUnlocked) {
                    System.out.print("Enter base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    double exponent = scanner.nextDouble();
                    double result = calculator.power(base, exponent);
                    if (!Double.isNaN(result)) {
                        System.out.println("Result of " + base + " ^ " + exponent + " = " + result);
                    }
                } else {
                    System.out.println("Secret operation is locked.");
                }

            } else if (choice >= 1 && choice <= 4) { 
                System.out.print("Enter first number: ");
                double num1 = scanner.nextDouble();
                System.out.print("Enter second number: ");
                double num2 = scanner.nextDouble();

                if (choice == 1) {
                    System.out.println("Result of " + num1 + " + " + num2 + " = " + calculator.add(num1, num2));
                } else if (choice == 2) {
                    System.out.println("Result of " + num1 + " - " + num2 + " = " + calculator.subtract(num1, num2));
                } else if (choice == 3) {
                    System.out.println("Result of " + num1 + " * " + num2 + " = " + calculator.multiply(num1, num2));
                } else if (choice == 4) {
                    double result = calculator.divide(num1, num2);
                    if (!Double.isNaN(result)) {
                        System.out.println("Result of " + num1 + " / " + num2 + " = " + result);
                    }
                }
            } else {
                System.out.println("Invalid choice!");
            }
        } else {
            System.out.println("Invalid input.");
        }
    }
}


public class Calcu {
    
    private double result;
    private boolean isSecretUnlocked;
    private String secretPasscode;

    public Calcu() {
        this.result = 0;
        this.isSecretUnlocked = false;
        this.secretPasscode = "1234";  
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public boolean isSecretUnlocked() {
        return isSecretUnlocked;
    }

   
    public String unlockSecret(String passcode) {
        if (passcode.equals(this.secretPasscode)) {
            this.isSecretUnlocked = true;
            return "Access Granted!";
        } else {
            this.isSecretUnlocked = false;
            return "Access Denied!";
        }
    }

   
    public double add(double num1, double num2) {
        this.result = num1 + num2;
        return this.result;
    }

    public double subtract(double num1, double num2) {
        this.result = num1 - num2;
        return this.result;
    }

    public double multiply(double num1, double num2) {
        this.result = num1 * num2;
        return this.result;
    }

    public double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println("Error: Cannot divide by zero.");
            return Double.NaN;
        }
        this.result = num1 / num2;
        return this.result;
    }

    public double power(double base, double exponent) {
        if (this.isSecretUnlocked) {
            this.result = Math.pow(base, exponent);
            return this.result;
        } else {
            System.out.println("Secret operation is locked. Enter the passcode first.");
            return Double.NaN;
        }
    }
}
