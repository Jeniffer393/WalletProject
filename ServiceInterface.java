package com.ibm.wallet.service;

import com.ibm.wallet.bean.Wallet;
import com.ibm.wallet.exception.*;
import org.springframework.jdbc.core.JdbcTemplate;

public interface ServiceInterface {
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
    public int createAccount(String walletHolderName,double walletBalance) throws InsufficientBalance ;
	public Wallet getBalanceById(int walletId);
	public double checkBalance(int walletId);
	public double deposit(int walletId,double amount);
	public double withdraw(int walletId,double amount);
	public double transfer(int walletId,double amount);
	

}
