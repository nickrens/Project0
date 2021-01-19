package dev.rens.app;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import dev.rens.entities.Account;
import dev.rens.entities.User;
import dev.rens.exceptions.AccountNameUsedException;
import dev.rens.exceptions.AmountMustBePositiveException;
import dev.rens.exceptions.InsufficientFundsException;
import dev.rens.exceptions.InvalidUsernameOrPasswordException;
import dev.rens.exceptions.NotAbleToRemoveAccountException;
import dev.rens.exceptions.UsernameHasBeenTakenException;
import dev.rens.services.AccountService;
import dev.rens.services.AccountServiceImpl;
import dev.rens.services.SuperUserService;
import dev.rens.services.SuperUserServiceImpl;
import dev.rens.services.UserService;
import dev.rens.services.UserServiceImpl;

public class App {

	private static Scanner scan = new Scanner(System.in);
	//initialize all services
	private static AccountService aServ = new AccountServiceImpl();
	private static SuperUserService supServ = new SuperUserServiceImpl();
	private static UserService uServ = new UserServiceImpl();
	
	public static void main(String[] args) {
		System.out.println("Wellcome to the Bank of Scrooge McDuck");
		
		int menu = 0;
		
		while(menu != 3) {
			mainMenu();
			menu = tryMenu();
			
			
			switch(menu) {
			case(1):{
				createMenu();
			}break;
			case(2):{
				User u = loginMenu();
				
				
				if(u == null) {
					System.out.println("Please try again with a valid username and password");
				}else if(u.isSuperUser()) {
					int menu2 = -1;
					System.out.println("\nLogged in as: " + u.getUsername());
					
					while(menu2 != 0) {
						superUserMenu();
						menu2 = tryMenu();
						
						switch(menu2) {
						case(1):{
							seeAllUsers();
						}break;
						case(2):{
							createMenu();
						}break;
						case(3):{
							updateUser();
						}break;
						case(4): {
							deleteUser();
						}break;
						case(0):{
							System.out.println("User logged out");
						}break;
						default:{
							System.out.println("Not a valid option please try a valid option");
						}break;
						}
						
					}
				}else {
					int menu2 = -1;
					System.out.println("\nLogged in as: " + u.getUsername());
					
					while(menu2 != 0) {
						userMenu();
						menu2 = tryMenu();
						
						switch(menu2) {
						case(1):{
							showAccounts(u);
						}break;
						case(2):{
							Account account = selectAccount(u);
							int menu3 = -1;
							
							while(menu3 != 0) {
								if(account != null) {
									accountMenu();
									menu3 = tryMenu();
									
									switch(menu3) {
									case(1):{
										//view balance
										System.out.println("The ballance on this account is: " + aServ.viewBalance(account));
									}break;
									case(2):{
										//make deposit
										System.out.println("Enter ammount you would like to deposit");
										
										try {
											double amount = scan.nextDouble();
											aServ.depositToAccount(account, amount);
											System.out.println("Your new balance on this account is: " + aServ.viewBalance(account));
										} catch (AmountMustBePositiveException e) {
											System.out.println(e.getMessage());
										} catch (InputMismatchException e) {
											System.out.println("Please input a number\n");
											scan.next();
										}
									}break;
									case(3):{
										//make withdrawal
										System.out.println("Enter the amount you would like to withdraw");
	
										try {
											double amount = scan.nextDouble();
											aServ.withdrawFromAccount(account, amount);
											System.out.println("Your new balance on this account is: " + aServ.viewBalance(account));
										} catch (InsufficientFundsException e) {
											System.out.println(e.getMessage());
										} catch (InputMismatchException e) {
											System.out.println("Please input a number\n");
											scan.next();
										} catch (AmountMustBePositiveException e) {
											System.out.println(e.getMessage());
										}
									}break;
									case(0):{
										System.out.println("Returning to User menu\n\n");
									}break;
									default:{
										System.out.println("Not a valid option please try a valid option");
									}break;
									}
								}else {
									System.out.println("Account not found");
									menu3 = 0;
								}
							}
							
						}break;
						case(3):{
							//create an account
							Account a = createAccount(u);
							if(a != null) {
								System.out.println("Account Created ");
							}
						}break;
						case(4):{
							deleteAccount(u);
						}break;
						case(0):{
							System.out.println("User loged out");
						}break;
						default:{
							System.out.println("Not a valid input\n\n");
						}break;
						}
					}
				}
				
			}break;
			case(3):{
				System.out.println("Program ending");
			}break;
			default:{
				System.out.println("Not a Valid input\n\n");
			}break;
			}
			
		}
	}
	
	@SuppressWarnings("finally")
	public static int tryMenu() {
		int menu = -1;
		try {
			menu = scan.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("Please input a number");
			scan.next();
			menu = -1;
		}finally {
			return menu;
		}
	}
	
	public static void mainMenu() {
		System.out.println("\nMain menu");
		System.out.println("----------------------------");
		System.out.println("Enter a number to select what to do next");
		System.out.println("1: create a new user account");
		System.out.println("2: login");
		System.out.println("3: exit program");
	}
	
	public static void userMenu() {
		System.out.println("\nUser menu");
		System.out.println("----------------------------");
		System.out.println("1: see all accounts");
		System.out.println("2: select an account");
		System.out.println("3: create an account");
		System.out.println("4: delete an empty account");
		System.out.println("0: logout");
	}
	
	public static void superUserMenu() {
		System.out.println("\nSuperUser menu");
		System.out.println("----------------------------");
		System.out.println("1: See all users");
		System.out.println("2: create a new user");
		System.out.println("3: update a user");
		System.out.println("4: delete a user");
		System.out.println("0: logout");
	}
	
	public static void accountMenu() {
		System.out.println("\nAccount menu");
		System.out.println("----------------------------");
		System.out.println("1: view ballance");
		System.out.println("2: make a deposit");
		System.out.println("3: make a withdrawl");
		System.out.println("0: return to user menu");
	}
	
	public static void deleteUser() {
		Set<User> users = supServ.viewAllUsers();
		
		System.out.println("Enter the name of the user you wish to update");
		String username = scan.next();
		User user = null;
		
		for(User u: users) {
			if(u.getUsername().equals(username)) {
				user = u;
				break;
			}
		}
		
		if(user == null) {
			System.out.println("The username you used in not valid");
			return;
		}else {
			System.out.println("User to be deleted: ");
			System.out.println(user.toString());
			if(supServ.deleteUser(user)) {
				System.out.println("User has been deleted");
			}else {
				System.out.println("Unable to delete user");
			}
			
		}
	}
	
	public static void updateUser() {
		Set<User> users = supServ.viewAllUsers();
		
		System.out.println("Enter the username of the user you wish to update");
		String username = scan.next();
		User user = null;
		
		for(User u: users) {
			if(u.getUsername().equals(username)) {
				user = u;
				break;
			}
		}
		
		if(user == null) {
			System.out.println("The username you used is not valid");
			return;
		}else {
			System.out.println("The user you selected: ");
			System.out.println(user.toString());
			
			String newUsername = null;
			String newPassword = null;
			String superUser = null;
			
			if(choice("username")) {
				System.out.println("Enter new username you would like to use");
				newUsername = scan.next();
			}
			
			if(choice("password")) {
				System.out.println("Enter new password you would like to use");
				newPassword = scan.next();
			}
			
			if(choice("superuser")) {
				System.out.println("Enter t if you would like to make this user a superuser or f if you want to demote superuser to user");
				superUser = scan.next();
			}
			
			if(newUsername != null) {
				user.setUsername(newUsername);
			}
			if(newPassword != null) {
				user.setPassword(newPassword);
			}
			if(superUser != null) {
				boolean sup;
				if(superUser.equals("t")) {
					sup = true;
				}else {
					sup = false;
				}
				
				user.setSuperUser(sup);
			}
			
			user = supServ.updateUser(user);
			System.out.println("Updated user: ");
			System.out.println(user.toString());
		}
	}
	
	public static boolean choice(String field) {
		System.out.println("Would you like to alter the " + field + " field");
		System.out.println("enter y for yes or n for no");
		String choice = scan.next();
		
		if(choice.toLowerCase().equals("y")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void seeAllUsers() {
		Set<User> users = supServ.viewAllUsers();
		
		for(User u: users) {
			System.out.println(u.toString());
		}
	}
	
	public static void showAccounts(User u) {
		Set<Account> accounts = uServ.ShowBankAccounts(u);
		if(accounts.isEmpty()) {
			System.out.println("you currently do not have any accounts");
		}else {
			int count = 1;
			for(Account a: accounts) {
				System.out.println("Account: " + count);
				System.out.println(a.toString());
				count++;
			}
		}
	}
	
	public static Account selectAccount(User u) {
		Set<Account> accounts = uServ.ShowBankAccounts(u);
		System.out.println("Enter the name of the account you would like to select");
		String accountName = scan.next();
		
		for(Account a: accounts) {
			if(a.getName().equals(accountName)) {
				return a;
			}
		}
		return null;
	}
	
	public static Account createAccount(User u) {
		System.out.println("Create account");
		System.out.println("----------------------------");
		System.out.println("Enter name you would like to give account");
		String name = scan.next();
		
		Account a = null;
		try {
			a = uServ.makeBankAccount(name, u);
		} catch (AccountNameUsedException e) {
			System.out.println(e.getMessage());
		}
		return a;
	}
	
	public static boolean deleteAccount(User u) {
		Set<Account> accounts = uServ.ShowBankAccounts(u);
		System.out.println("Enter the name of the account you would like to delete");
		String accountName = scan.next();
		Account account = null;
		
		for(Account a: accounts) {
			if(a.getName().equals(accountName)) {
				account =  a;
			}
		}
		
		if(account == null) {
			System.out.println("You did not select a valid account");
			return false;
		}else if(account.getBalance() != 0) {
			System.out.println("You still have a balance on this account");
			System.out.println("You must have a balance of zero to delete an account");
			return false;
		}else {
			System.out.println("Deleting account: ");
			System.out.println(account.toString());
			try {
				uServ.deleteBankAccount(account);
			} catch (NotAbleToRemoveAccountException e) {
				System.out.println(e.getMessage());
			}
			return true;
		}
	}
	
	public static void createMenu() {
		System.out.println("Create a new User:");
		System.out.println("----------------------------");
		System.out.println("Enter the username you with to have");
		String username = scan.next();
		System.out.println("Enter the password you want to use");
		String password = scan.next();
		System.out.println("Enter t if you would like to be a superuser of f if you would like to create a normal user");
		String sup = scan.next();
		boolean superUser;
		if(sup.equals("t")) {
			superUser = true;
		}else {
			superUser = false;
		}
		User u;
		try {
			u = uServ.createNewUser(username, password, superUser);
			System.out.println("you have created the new user:");
			System.out.println(u.getUsername());
		} catch (UsernameHasBeenTakenException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public static User loginMenu() {
		System.out.println("Login User: ");
		System.out.println("----------------------------");
		System.out.println("Enter you username: ");
		String username = scan.next();
		System.out.println("Enter your password");
		String password = scan.next();
		User u = null;
		try {
			u = uServ.login(username, password);
		} catch (InvalidUsernameOrPasswordException e) {
			System.out.println(e.getMessage());
		}
		return u;
	}
}
