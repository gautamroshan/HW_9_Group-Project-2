// Don't take any of this to heart.
// i have been really busy with work and family this break,
// so i had to whip this up real quick.
// if we can do something with this that would be great.
// unless you have a better idea then that would be even greater.

// This allows access to the scanner library
import java.util.Scanner;
public class AccountProjectTestFile1 {

	public static void main(String[] args) {
		// this will look at the selected file in a scan
		// this part i'm not really sure how it works, i found it on the Internet.
		// Roshan if you want to do some research on this or if you know how to make it work 
		// that's even better
		Scanner scan = new Scanner (new File("the\\dir\\myFile.extension"));
		// this will allow use of the scanner.
		    Scanner input = new Scanner (System.in);
		    String user = scan.nextLine();
		    String pass = scan.nextLine(); 
		    // This is where we will prompt user input.
		    String inputUser = input.nextLine();
		    String inputPass = input.nextLine(); 
		    // this will determine what the user has typed is correct or not
		    // and display it with either a correct or an incorrect message
		    if (inputUser.equals(user) && inputPass.equals(pass)) {
		        System.out.print("Greetings, " + user);
		    } else {
		        System.out.print("Sorry, either your password or username is incorrect.");
		    }
	}

}
