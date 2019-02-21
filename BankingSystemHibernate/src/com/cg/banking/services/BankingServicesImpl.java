package com.cg.banking.services;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.daoservices.AccountDAO;
import com.cg.banking.daoservices.AccountDAOImpl;
import com.cg.banking.daoservices.TransactionDAO;
import com.cg.banking.daoservices.TransactionDAOImpl;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.exceptions.AccountNotFoundException;
import com.cg.banking.exceptions.BankingServicesDownException;
import com.cg.banking.exceptions.InsufficientAmountException;
import com.cg.banking.exceptions.InvalidAccountTypeException;
import com.cg.banking.exceptions.InvalidAmountException;
import com.cg.banking.exceptions.InvalidPinNumberException;

public class BankingServicesImpl implements  BankingServices{
	int PIN_NUMBER_COUNTER=500;
	AccountDAO service = new AccountDAOImpl();
	TransactionDAO transactionService=new TransactionDAOImpl() ;
	Account account;
	public int pinNumber() {
		return ++PIN_NUMBER_COUNTER;
	}
	@Override
	public Account openAccount(String accountType, float initBalance)
			throws InvalidAmountException, InvalidAccountTypeException, BankingServicesDownException {
		if(!(accountType.equalsIgnoreCase("Savings") | accountType.equalsIgnoreCase("Current")))
			throw new InvalidAccountTypeException("Invalid Account Type");
		if(initBalance<0 | initBalance<500 )
			throw new InvalidAmountException("Invalid Amount!!! Enter Amount > 500");
			Account account = new Account(accountType,initBalance);
			account.setAccountStatus("Active");
			account.setPinNumber(pinNumber());
		account = service.save(account);
		return account; 
	}

	@Override
	public List<Account> getAllAccountDetails() throws BankingServicesDownException {
		return service.findAll();
	}
	
	
	@Override
	public Account getAccountDetails(long accountNo) throws AccountNotFoundException, BankingServicesDownException {
		 account=service.findOne(accountNo);
		 if(account==null)
			 throw new AccountNotFoundException("Invalid Account Number");
		return account;
	}
	
	
	
	@Override
	public float depositAmount(long accountNo, float amount)
		throws AccountNotFoundException, BankingServicesDownException, AccountBlockedException {
		account=service.findOne(accountNo);
		if(account== null)
			throw new AccountNotFoundException("Enter correct account number!!! ");
		float finalAmount=(account.getAccountBalance())+amount;
		account.setAccountBalance(finalAmount);
		service.update(account);
		
		Transaction transaction=new Transaction();
		transaction.setAmount(amount);
		transaction.setAccount(account);
		transaction.setTransactionType("Credit");
		transactionService.save(transaction);
		return account.getAccountBalance();
	}

	@Override
	public float withdrawAmount(long accountNo, float amount, int pinNumber) throws InsufficientAmountException,
			AccountNotFoundException, InvalidPinNumberException, BankingServicesDownException, AccountBlockedException {
		account = service.findOne(accountNo);
		if(account==null)
			throw new AccountNotFoundException("Invalid Account Number!!! ");
		
		if(pinNumber==account.getPinNumber()) {
			if(amount<account.getAccountBalance()) {
			float newAmount=account.getAccountBalance()-amount;
			account.setAccountBalance(newAmount);
			service.update(account);
			
			Transaction transactionWith=new Transaction();
			transactionWith.setTransactionType("Money Withdrawn");
			transactionWith.setAmount(amount);
			transactionWith.setAccount(account);
			transactionService.save(transactionWith);
			return newAmount;
			}
			else
				throw new InsufficientAmountException("Insufficient Amount");
		}
		else
			throw new InvalidPinNumberException("Invalid PIN Number!!!");
	}
	
	@Override
	public boolean fundTransfer(long accountNoFrom, long accountNoTo, float transferAmount, int pinNumber)
			throws InsufficientAmountException, AccountNotFoundException, InvalidPinNumberException,
			BankingServicesDownException, AccountBlockedException {
		
		Transaction transactionFT=new Transaction();
		transactionFT.setAmount(transferAmount);
		
		account = service.findOne(accountNoFrom);
		if(account==null)
			throw new AccountNotFoundException("Ivalid Account Number!!! ");
		if(pinNumber==account.getPinNumber()) {
			if(transferAmount<account.getAccountBalance()) {
			float deductedAmount=account.getAccountBalance()-transferAmount;
			account.setAccountBalance(deductedAmount);
			service.update(account);
			
			transactionFT.setTransactionType("Money Transfered");
			//transactionFT.setAmount(transferAmount);
			transactionFT.setAccount(account);

			transactionService.save(transactionFT);
			transactionService.update(transactionFT);
			}
			else
				throw new InsufficientAmountException("Insufficient Amount!!!");
		}
		else
			throw new InvalidPinNumberException("Invalid Pin Number!!!");
			account =service.findOne(accountNoTo);
			float addedAmount=account.getAccountBalance()+transferAmount;
				account.setAccountBalance(addedAmount);
			    service.update(account);
				transactionFT.setTransactionType("Money Deposited");
				transactionFT.setAmount(transferAmount);
				transactionFT.setAccount(account);

				transactionService.save(transactionFT);
				transactionService.update(transactionFT);
				return true;
	}
	@Override
	public List<Transaction> getAccountAllTransaction(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException {
		account=service.findOne(accountNo);
		if(account==null)
			throw new AccountNotFoundException("Invalid Account Number");
		Account account=service.findOne(accountNo);
		List List=new LinkedList(account.transactions);
		
		if(List.isEmpty())
			throw new BankingServicesDownException("No Transactions to be Displayed!!!");
		else
			return List;
		}

	@Override
	public String accountStatus(long accountNo)
			throws BankingServicesDownException, AccountNotFoundException, AccountBlockedException {
		return null;
	}

}

