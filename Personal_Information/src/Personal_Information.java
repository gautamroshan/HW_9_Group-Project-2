import java.io.*;
import java.util.Scanner;
public class Personal_Information{
	public static void main(String[]args) throws IOException{
		try{
		File accountInfo = new File("account.txt");
		PrintWriter output = new PrintWriter(accountInfo);
		System.out.println("Welcome to the InfoCabinet"+"\nType and Enter,\n(1)for creating an account.\n(2)for returning users login.\n(3)to exit.");
		
		}
		
catch (java.io.IOException ex){
	System.out.println("I/O Errors: File is not found");
		}
	}
}

