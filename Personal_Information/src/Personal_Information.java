import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Personal_Information{
	public static void main(String[]args) throws IOException {
		
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
		}
		catch (java.io.IOException ex){
			System.out.println("I/O Errors: File is not found");
				}	
		}
	
	
public static void login(){
		
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


