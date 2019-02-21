package com.cg.banking.client;
import java.util.Scanner;

import com.cg.banking.beans.Account;
import com.cg.banking.beans.Transaction;
import com.cg.banking.exceptions.AccountBlockedException;
import com.cg.banking.services.BankingServices;
import com.cg.banking.services.BankingServicesImpl;

public class MainClass extends Exception{
	Transaction transaction;
	static String status="Blocked";
	Account account=new Account();
	static Scanner sc= new Scanner(System.in);
	static BankingServices services= new BankingServicesImpl();
	public static void main(String args[]) throws Exception {
		
			mainScreen();
			int userChoice=sc.nextInt();
			startMenu(userChoice);
	}
	public static void startMenu(int userChoice) throws Exception  {
		switch(userChoice) {
		case 1: System.out.println("Enter the type of account you want to open:");
						System.out.println("Note:Savings Current");
						String accountType=sc.next();
						System.out.println("Enter your client Balance");
						long initialBalance=sc.nextLong();
						Account   account = services.openAccount(accountType,initialBalance);
						System.out.println("******ACCOUNT CREATED******");
						System.out.println(account);
						break;
		case 2:System.out.println("Enter the account number");
						long accountNumber=sc.nextLong();
						System.out.println(services.getAccountDetails(accountNumber));
						break;
		case 3 : System.out.println(services.getAllAccountDetails());
						break;
		case 4 : System.out.println("Enter Amount to be deposited: ");
						float depositeAmount=sc.nextFloat();
						System.out.println("Enter Account No : ");
						long depositeAccountNumber=sc.nextLong();
						System.out.println(services.depositAmount(depositeAccountNumber,depositeAmount));
						break;
		case 5: System.out.println("Enter Amount to be withdraw: ");
						float withdrawAmount=sc.nextFloat();
						System.out.println("Enter Account No : ");
						long withdrawAccountNumber=sc.nextLong();
						System.out.println("Enter PinNumber No : ");
					    int pinNumber=sc.nextInt();
					    account=services.getAccountDetails(withdrawAccountNumber);
					    if(pinNumber==account.getPinNumber())
					    	System.out.println(services.withdrawAmount(withdrawAccountNumber,withdrawAmount, pinNumber));
					    else {
					    	int count=0;
					    	do {
						    	System.out.println("Sorry!!! Wrong Pin...Enter PinNumber No Again : ");
						    	int pinNumber1=sc.nextInt();
						    	  if(pinNumber1==account.getPinNumber()) {
						    		  System.out.println(services.withdrawAmount(withdrawAccountNumber,withdrawAmount, pinNumber1));
						    		  break;
						    	  }
						    	count++;
						    if(count>1) {
						    	throw new AccountBlockedException("Account has been Blocked!!!");						    	
						    }
					    	}while(pinNumber!=account.getPinNumber());
					    }
						break;
		case 6: System.out.println("Enter Amount to be Transferred: ");
						float transferAmount=sc.nextFloat();
						System.out.println("Enter Sender Account No : ");
						long senderAccountNumber=sc.nextLong();
						System.out.println("Enter Reciever Account No : ");
						long recieverAccountNumber=sc.nextLong();
						System.out.println("Enter PinNumber No of Sender's Account: ");
					    int senderPinNumber=sc.nextInt();
						System.out.println(services.fundTransfer(senderAccountNumber, recieverAccountNumber, transferAmount, senderPinNumber));
						break;
		case 7:
						System.out.println("Enter Account No : ");
						long transactionAccountNumber=sc.nextLong();
						System.out.println(services.getAccountAllTransaction(transactionAccountNumber));
						break;
						default: 
						System.out.println("Invalid Choice,Please Try Again!!!!!!");
		}
		System.out.println("What do you want to do now ?");
		System.out.println("1. Continue");
		System.out.println("2. Exit");
		int choice =sc.nextInt();
		if(choice==2)
			System.exit(0);
		main(null);
	}
	public static void mainScreen(){
		System.out.println("__________________________________Welcome to My Bank________________"
				+ "__________________");
		System.out.println("Please enter any one of the given choices :");
		System.out.println("1. Create a account");
		System.out.println("2. Get your account details");
		System.out.println("3. Get all account details");
		System.out.println("4. Deposite Amount");
		System.out.println("5. Withdraw Amount");
		System.out.println("6. Fund Transfer");
		System.out.println("7. Transactions");
	}
	
}
