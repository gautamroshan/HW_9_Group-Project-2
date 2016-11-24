import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Personal_Information{
	
	
	public static void main(String[]args) throws IOException {
		starter();
		}
	
	
	public static void starter() throws IOException{
		System.out.println("Welcome to the InfoCabinet"+"\nType and Enter,\n(1)for creating an account.\n(2)for returning users login.\n(3)to exit.");
		Scanner input = new Scanner(System.in);
		int userCommand = input.nextInt();
		if(userCommand==1){
			createAccount();
		}
		else if(userCommand==2){
			login();
		}
	
		else if(userCommand==3){
			System.exit(0);
			System.out.println("Thanks for using InfoCabinet!");
			}
	}
		

	public static void createAccount()throws IOException{
		try{
			File accountInfo = new File("account.txt");
			ArrayList<String> temp = new ArrayList<String>();
			Scanner inputFromAccount = new Scanner(accountInfo);
			while (inputFromAccount.hasNextLine()){
				temp.add(inputFromAccount.nextLine());
			}
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your first name");
		String FirstName = input.next();
		System.out.println("Enter your last name");
		String LastName = input.next();
		System.out.println("Enter your prefered username");
		String UserName = input.next();
		System.out.println("Your password should meet the following requirements:");
		System.out.println("-It must have at least eight characters.\n-It must consist of only letters and digits.\n-It must contain at least two digits and two characters.");
		System.out.println("\nEnter your new Password");
		String Password = input.next();
		boolean check=false;
		if(validigits(Password)&&letter_digit_check(Password))check=true;
		while(!check){
			System.out.println("Please recheck the password requirement and try again.");
			Password = input.next();
			if(validigits(Password)&&letter_digit_check(Password))check=true;
		}
		PrintWriter output = new PrintWriter(accountInfo);
		temp.add(UserName+" "+Password);
		for (int i=0; i<temp.size(); i++){
			output.println(temp.get(i));
		}
		output.close();
		System.out.println("Account created for "+FirstName+" "+LastName);
		System.out.println();
		System.out.println();
		System.out.println();
		starter();
		}
		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
				}	
		}
	
	
	public static void login(){
	try{
		File Information = new File("information.txt");
		File Account = new File("account.txt");
		ArrayList<String> account = new ArrayList<String>();
		ArrayList<String> info = new ArrayList<String>();
		Scanner inputFromAccount = new Scanner(Account);
		Scanner inputFromInformation = new Scanner(Information);
		while (inputFromInformation.hasNext()){
			info.add(inputFromInformation.next());
		}
		while (inputFromAccount.hasNext()){
			account.add(inputFromAccount.next());
		}
	boolean passwordcheck=false;
	boolean check=false;
	String PassWord="";
	String UserName="";
	String username="";
	Scanner input = new Scanner(System.in);
	do{
	System.out.println("Enter your username");
	username = input.next();
	for(int i=0; i<account.size()-1;i++){
		UserName=account.get(i);
		PassWord=account.get(i+1);
		if(UserName.equals(username)){
		System.out.println("Enter your password");
		String password = input.next();
		if (PassWord.equals(password))check=true;
		}
	}
	if(!check)System.out.println("User not found! Please try again");
	}while(!check);
	int userindex=0;
	for(int i=0; i<info.size();i++){
		if(username.equals(info.get(i)))userindex=1;
	}
	if (userindex==0){
		info.add(username);
		PrintWriter output = new PrintWriter(Information);
		for (int i=0; i<info.size(); i++){
			output.println(info.get(i));
		}
		output.close();
	}
	
	System.out.println("Login Sucessful!\nPlease enter \n(1)to view your saved information\n(2)to edit your saved information\n(3)to logout");
	int command = input.nextInt();
	boolean checker=false;
	do{
	if(command==0||command==1||command==2||command==3)checker=true;
	if(command==1){
		viewInfo(username);	
	}
	else if(command==2){
		editInfo(username);
	}
	else if(command==3){
		starter();
	}
	}while(!checker);
	}
	
	catch (java.io.IOException ex){
		System.out.println("I/O Errors: File is not found");
			}	
}
	
	
	public static void viewInfo(String user){
		try{
			File Information = new File("information.txt");
			ArrayList<String> info = new ArrayList<String>();
			Scanner inputFromInformation = new Scanner(Information);
			while (inputFromInformation.hasNext()){
				info.add(inputFromInformation.next());
			}
			boolean check=false;
			for(int i=0; i<info.size();i++){
				if(user.equals(info.get(i))){
					check=true;
					System.out.println("Name: "+info.get(i+1)+" "+info.get(i+2));
					System.out.println("Birthdate: "+info.get(i+3));
					System.out.println("Bank Information: "+info.get(i+4));
					System.out.println("Social Security Number: "+info.get(i+5));
				}					
			}
			if(!check)System.out.println("Information not found in the system");
		}
		
		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
				}	
	}
	
	
	public static void editInfo(String user){
		try{
			File Information = new File("information.txt");
			ArrayList<String> info = new ArrayList<String>();
			Scanner inputFromInformation = new Scanner(Information);
			System.out.println("Please change all your informations for security reasons");
			//Actually because this program cannot handle changing only one specific info :D
			while (inputFromInformation.hasNextLine()){
				info.add(inputFromInformation.nextLine());
			}
			int index=0;
			
			for(int i=0; i<info.size();i++){
				String uname="";
				String name=info.get(i);
				for (int j=0; j<name.length(); j++){
					String temp = name.charAt(j)+"";
					if (!temp.equals(" "))
					uname+=name.charAt(j);
				}
				if(user.equals(uname)){
					 index=i;
				}		
			}
			info.set(index,Editor(user));
			PrintWriter output = new PrintWriter(Information);
			for (int i=0; i<info.size(); i++){
				output.println(info.get(i));
			}
			output.close();
			Scanner input = new Scanner(System.in);
			System.out.println("Enter (1) to logout");
			int command = input.nextInt();
			if (command==1)starter();
		 }
		
		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
				}	
	}
	
	public static String Editor(String username){
		String main = username+" ";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your first and last name(for example: John Lennon):");
		main+=input.nextLine()+" ";
		System.out.println("Enter your date of birth (as MM/DD/YYYY)");
		main+=input.nextLine()+" ";
		System.out.println("Enter your social security number (as 109382193)");
		main+=input.nextLine()+" ";
		System.out.println("Enter your bank name and account number (as BANKOFMARS-10789)");
		main+=input.nextLine();
		System.out.println("Your new information has been saved");
		return main;
		
	}
	
	
	public static boolean validigits(String pass){
	if (pass.length()>8){
        return true;
	}
	else 
		return false;
}



	public static boolean letter_digit_check(String pass){
	int sum=0;
	int count=0;
	for (int i=0; i<pass.length(); i++){
		if (Character.isDigit(pass.charAt(i))){
			sum++;
		}
		else if(Character.isLetter(pass.charAt(i))){
			count++;
		}
	}
	if (sum>2 && count>2)
		return true;
	else 
		return false;
	}

}


