package com.ibm.wallet.ui;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.wallet.bean.Wallet;
import com.ibm.wallet.service.ServiceInterface;

public class MainClass {

	private static ApplicationContext context;

	private static ServiceInterface serviceInterface;

	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		serviceInterface = (ServiceInterface) context.getBean("serviceInterface");
		while (true) {
			System.out.println("1.Create Account");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposit");
			System.out.println("4.Transfer");
			System.out.println("5.Check balance");
			System.out.println("0.Exit");
			System.out.println("Choice?");
			int choice = in.nextInt();
			switch (choice) {

			case 1:
				createAccount();
				break;
			case 2:
				withdrawAmount();
				break;
			case 3:
				depositAmount();
				break;
			case 4:
				transferAmount();
				break;
			case 5:
				checkBalance();
				break;
			case 0:
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	private static void checkBalance() {
		System.out.println("Enter wallet Id");
		int walletId = in.nextInt();

		Wallet wallet = serviceInterface.getBalanceById(walletId);
		System.out.println("Amount Deposited,current balance=" + wallet.getWalletBalance());
	}

	private static void transferAmount() {
		System.out.println("Enter your wallet id");
		int walletId = in.nextInt();
		System.out.println("Enter receiver wallet id ");
		int receiverWalletId = in.nextInt();
		System.out.println("enter the Amount to be transfered ");
		int amount = in.nextInt();
		double walletBalance = serviceInterface.transfer(walletId, amount);
		System.out.println(
				"Balance Amount after transfered to " + receiverWalletId + ",current balance=" + walletBalance);

	}

	private static void depositAmount() {
		System.out.println("Enter wallet Id");
		int walletId = in.nextInt();
		System.out.println("Amount?");
		int amount = in.nextInt();

		double accountBalance = serviceInterface.deposit(walletId, amount);
		System.out.println("Amount Deposited,current balance=" + accountBalance);

	}

	private static void createAccount() {
		System.out.println("Name");
		in.nextLine();
		String walletHolderName = in.nextLine();
		System.out.println("Opening balance");
		double walletBalance = in.nextInt();
		Wallet wallet = new Wallet();
		wallet.setWalletHolderName(walletHolderName);
		wallet.setWalletBalance(walletBalance);
		System.out.println("Account created with id  " + wallet.getWalletId());
		System.out.println("Remember this ID!!!");
	}

	private static void withdrawAmount() {
		System.out.println("Enter your wallet id");
		int walletId = in.nextInt();
		System.out.println("Amount");
		int amount = in.nextInt();

		double accountBalance = serviceInterface.withdraw(walletId, amount);
		System.out.println("Amount withdrawn,current balance=" + accountBalance);

	}
}
