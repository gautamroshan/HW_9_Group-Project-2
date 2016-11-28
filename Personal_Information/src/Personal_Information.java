import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Personal_Information{


	public static void main(String[]args) throws IOException {
		System.out.println("Welcome to the InfoCabinet");
		starter();
	}



	// This is where the user will start
	// They will be given 3 options
	// They can either Create an Account,
	// Login an Account, or Exit the program
	public static void starter() throws IOException{
		System.out.println("\nType and Enter,\n(1)for creating an account."
				+ "\n(2)for returning users login."
				+ "\n(3)to exit.");
		Scanner input = new Scanner(System.in);
		int userCommand = input.nextInt();
		if(userCommand==1){
			createAccount();
		}
		else if(userCommand==2){
			login();
		}

		else if(userCommand==3){
			System.out.println("Thanks for using InfoCabinet!");
			System.exit(0);
		}
	}




	// this is how the file system works and shows
	// which files we use to store the user inputed information.
	public static void createAccount()throws IOException{
		try{
			File accountInfo = new File("account.txt");
			File Information = new File("information.txt");
			ArrayList<String> temp = new ArrayList<String>();
			ArrayList<String> info = new ArrayList<String>();
			Scanner inputFromInformation = new Scanner(Information);
			Scanner inputFromAccount = new Scanner(accountInfo);
			while (inputFromAccount.hasNextLine()){
				temp.add(inputFromAccount.nextLine());
			}
			Scanner input = new Scanner(System.in);
			// This is where we will start the new account process
			// first by telling us who you are.
			System.out.println("Enter your First Name");
			String firstName = input.next();
			
			System.out.println("Enter your Last Name");
			String lastName = input.next();
			// This will give the user recommended
			// user names if they cannot think of one.
			RecommendedUserNames(firstName,lastName);
			// here they will be prompt to enter their preferred user name
			System.out.println("Enter your preferred username");
			String UserName = input.next();
			// This will prompt the user for a password
			// The password will have to meet these requirements
			System.out.println("Your password should meet the following requirements:");
			System.out.println("-It must have at least eight characters.\n-It must consist of only letters and digits."
							+ "\n-It must contain more than two digits and two characters.");
			System.out.println("\nEnter your new Password");
			String Password = input.next();
			
			// this is what will verify the user password and see if it meets the recommended requirements
			// for security reasons of course.
			String Pass;

			boolean check=false;
			if(validigits(Password)&&letter_digit_check(Password))check=true;
			while(!check){
				System.out.println("Please recheck the password requirement and try again.");
				Password = input.next();
				if(validigits(Password)&&letter_digit_check(Password))check=true;
			}
			do{
				System.out.println("\nPlease re-enter the Password");
				Pass = input.next();
				if(!Pass.equals(Password))System.out.println("Passwords do not match!");
			}while(!Pass.equals(Password));
			boolean CreateAccount = true;
			System.out.println("You need to fill the information for completion of your account registration");
			editInfo(UserName,CreateAccount);
			PrintWriter output = new PrintWriter(accountInfo);
			temp.add(UserName+" "+Password);
			for (int i=0; i<temp.size(); i++){
				output.println(temp.get(i));
			}
			output.close();
			System.out.println("Your account has been created and your information has been saved"
					+ "\nYou have been logged out for this session\n");
			starter();
		}
		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
		}	
	}

		// this is the custom method used for the Recommended User Name 
	private static void RecommendedUserNames(String first, String last) {
		String [][] usernames = new String [2][2];
		int [] RandomNumbers = new int [4];
		for (int i=0; i < 4; i++) {
			RandomNumbers[i]=(int)(Math.random()*100);
		}
		int k = 0;
		System.out.println("Choose a unique username, some of the recommended ones for you are below");
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				if (k%2==0) usernames[i][j] = first+last+RandomNumbers[k];
				else usernames[i][j] = last+first+RandomNumbers[k];
				k++;
				System.out.println(usernames[i][j]+ " ");
			}
		System.out.println();
	}



	// this is where the login part of the code starts
	// the user will be asked to login after he or she has made an account 
	// the user will then be asked to enter their user name and password
	// if it does not match our data you will be denied entry into your account
	public static void login(){
		try{
			File Information = new File("information.txt");
			File Account = new File("account.txt");
			ArrayList<String> account = new ArrayList<String>();
			ArrayList<String> info = new ArrayList<String>();
			Scanner inputFromAccount = new Scanner(Account);
			Scanner inputFromInformation = new Scanner(Information);
			while (inputFromInformation.hasNextLine()){
				info.add(inputFromInformation.nextLine());
			}
			while (inputFromAccount.hasNext()){
				account.add(inputFromAccount.next());
			}
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
			boolean checker=false;
			System.out.println("Login Successful!");
			do{
				System.out.println("Please enter \n(1)to view your saved information"
						+ "\n(2)to edit your saved information"
						+ "\n(3)to logout");
				int command = input.nextInt();
				boolean temp = false;
				if(command==1||command==2||command==3)checker=true;
				else System.out.println("Invalid Input! Please try again");
				switch(command){
				case 1: viewInfo(username);break;	
				case 2: editInfo(username,temp);break;
				case 3: starter();
				}
			}while(!checker);
		}

		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
		}	
	}

	// this is a captcha you know, because its not official without one.
	public static String captcha(){
		String captcha="";
		for(int i=0; i<8; i+=2){
			captcha+=(char)(65+Math.random()*26)+" ";
			captcha+=(char)(97+Math.random()*26);
		}
		return captcha;

	}

	// this is what will allow the user to view their entered information
	public static void viewInfo(String user){
		try{
			File Information = new File("information.txt");
			ArrayList<String> info = new ArrayList<String>();
			Scanner inputFromInformation = new Scanner(Information);
			while (inputFromInformation.hasNext()){
				info.add(inputFromInformation.next());
			}
			String captcha,caption;
			do{
				captcha = captcha();
				System.out.println("Enter the text below including space:");
				System.out.println(captcha);
				Scanner input = new Scanner(System.in);
				caption = input.nextLine();
				if(!caption.equals(captcha))System.out.println("Please try again");

			}while(!caption.equals(captcha));





			boolean check=false;
			for(int i=0; i<info.size();i++){
				if(user.equals(info.get(i))){
					check=true;
					System.out.println("Name: "+info.get(i+1)+" "+info.get(i+2));
					System.out.println("Birthdate: "+info.get(i+3));
					System.out.println("Bank Information: "+info.get(i+4));
					System.out.println("Social Security Number: "+info.get(i+5));
					break;
				}					
			}
			if(!check)System.out.println("No information found.\nYou have been logged out.");
			starter();
		}

		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
		}	
	}




	// This is where we allow the user to edit their personal info 
	public static void editInfo(String user, boolean Registration){
		try{
			File Information = new File("information.txt");
			ArrayList<String> info = new ArrayList<String>();
			Scanner inputFromInformation = new Scanner(Information);
			if (Registration){
				while (inputFromInformation.hasNextLine()){
					info.add(inputFromInformation.nextLine());
				}

				info.add(Editor(user));
				PrintWriter output = new PrintWriter(Information);
				for (int i=0; i<info.size(); i++){
					output.println(info.get(i));
				}
				output.close();
			}
			else{
				System.out.println("If you would like to change any information, "
						+ "\nYou will need to change all the information, for security reasons");
				while (inputFromInformation.hasNextLine()){
					info.add(inputFromInformation.nextLine());
				}
				String uname=" ";
				int index=0;
				for(int i=0; i<info.size();i++){
					uname="";
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
		}

		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
		}	
	}




 // This is where the user will enter their new personal information
	public static String Editor(String username){
		String main = username+" ";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your first and last name(for example: John Lennon):");
		main+=input.nextLine()+" ";
		System.out.println("Enter your date of birth (as MM/DD/YYYY)");
		main+=input.nextLine()+" ";
		System.out.println("Enter your bank name and account number (as BANKOFMARS-10789)");
		main+=input.nextLine()+" ";
		System.out.println("Enter your social security number (as 109382193)");
		main+=input.nextLine();
		System.out.println("Your new information has been saved");
		return main;

	}



// this is to make sure the password is atleast 8 characters long
	public static boolean validigits(String pass){
		if (pass.length()>=8){
			return true;
		}
		else 
			return false;
	}



// this is to make sure the password meets the recommended requirements.
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
		if (sum>=2 && count>=2)
			return true;
		else 
			return false;
	}

}