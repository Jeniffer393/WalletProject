package com.ibm.wallet.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ibm.wallet.bean.Wallet;
import com.ibm.wallet.exception.*;
import com.ibm.wallet.jdbc.DataBase;

public class ServiceInterfaceImpl implements ServiceInterface {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	@Override
	public int createAccount(String walletHolderName, double walletBalance) {

		Wallet wallet = null;
		String sql = "INSERT into Wallet2 (wallet_name,wallet_balance) VALUES('" + wallet.getWalletHolderName() + "',"
				+ wallet.getWalletBalance() + ")";
		return jdbcTemplate.update(sql);
	}

	public Wallet getBalanceById(int walletId) {
		Wallet wallet = null;
		try {
			Connection connection = DataBase.getConnection();
			String sql = "SELECT * FROM Wallet2 WHERE wallet_id='" + walletId + "'";
			java.sql.Statement statement = connection.createStatement();
			java.sql.ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				String name = result.getString(2);
				int balance = result.getInt(3);
				wallet = new Wallet(walletId, name, balance);
			} else {
				System.out.println("Wallet with id:" + walletId + "not Found!");
			}
			connection.close();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		} catch (java.sql.SQLException sqle) {
			sqle.printStackTrace();
		}

		return wallet;

	}

	@Override
	public double checkBalance(int walletId) {

		Wallet wallet = getBalanceById(walletId);
		return wallet.getWalletBalance();

	}

	@Override
	public double deposit(int walletId, double amount) {

		double walletBalance = 0;
		try {
			ServiceInterface serviceInterface = new ServiceInterfaceImpl();
			Wallet wallet = serviceInterface.getBalanceById(walletId);

			Connection connection = DataBase.getConnection();

			String sql = "UPDATE Wallet2 SET wallet_balance=? WHERE wallet_id=?";

			walletBalance = wallet.getWalletBalance() + amount;
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(1, walletBalance);
			statement.setInt(2, walletId);
			statement.executeUpdate();
			connection.close();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		} catch (java.sql.SQLException sqle) {
			System.err.println(sqle.getMessage());
		}

		return walletBalance;
	}

	@Override
	public double withdraw(int walletId, double amount) {

		double walletBalance = 0;
		try {

			walletBalance = getBalanceById(walletId).getWalletBalance() - amount;
			if (walletBalance < 0) {
				System.out.println("Insufficient amount");
			}

			Connection connection = DataBase.getConnection();
			String sql = "UPDATE Wallet2 SET wallet_balance=? WHERE wallet_id=?";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setDouble(1, walletBalance);
			statement.setInt(2, walletId);
			statement.executeUpdate();
			connection.close();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		} catch (java.sql.SQLException e) {
			System.err.println(e.getMessage());
		}

		return walletBalance;

	}

	@Override
	public double transfer(int walletId, double amount) {

		Wallet wallet = getBalanceById(walletId);

		return wallet.getWalletBalance() - amount;
	}

}
