package com.ibm.wallet.bean;

import org.springframework.stereotype.Component;

@Component
public class Wallet {
	  
	private int walletId;
	private String walletHolderName;
	private static double walletBalance;
	
	public Wallet(){}
	
	
	public Wallet(int walletId, String walletHolderName, double walletBalance) {
		this.walletId = walletId;
		this.walletHolderName = walletHolderName;
		this.walletBalance = walletBalance;
	}


	public int getWalletId() {
		return walletId;
	}


	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}


	public String getWalletHolderName() {
		return walletHolderName;
	}


	public void setWalletHolderName(String walletHolderName) {
		this.walletHolderName = walletHolderName;
	}


	public static double getWalletBalance() {
		return walletBalance;
	}


	public static void setWalletBalance(double walletBalance) {
		walletBalance = walletBalance;
	}


	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", walletHolderName=" + walletHolderName + ", walletBalance="
				+ walletBalance + "]";
	}
	
	
	
	
	
}