package ProjectAdmin;

import java.sql.SQLException;
import java.util.Scanner;

public class UsingActions {
	public static void main(String[] args) {
		Actions mysql = new Actions();
		mysql.Connect();
		System.out.println("Starting Project...");
		Scanner sc1 = new Scanner (System.in);
		
		int tmp = 0;
		do {
		System.out.println("\n\t\t---MENU---");
	
		System.out.println("1: ADD IDENTITY INFORMATION TO DATABASE");
		System.out.println("2: DELETE IDENTITY INFORMATION FROM DATABASE");
		System.out.println("3: ADD TELEPHONE INFORMATION TO DATABASE");
		System.out.println("4: DELETE TELEPHONE INFORMATION FROM DATABASE");
		System.out.println("5: ADD EXTERNAL ACCOUNT TO DATABASE");
		System.out.println("6: DELETE EXTERNAL ACCOUNT FROM DATABASE");
		System.out.println("7: DELETE MOOMOOBANK ACCOUNT FROM DATABASE");
		System.out.print("CHOOSE ONE ACTION: ");

		tmp = sc1.nextInt();
		
		System.out.println();
		if (tmp == 1) {
			System.out.println("Please enter infomation: ");
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Your ID: ");
			String idcus = sc2.nextLine();
			System.out.print("Your name: ");
			String namecus = sc2.nextLine();
			System.out.print("Your gender: ");
			String sexcus = sc2.nextLine();
			System.out.print("Your birthday (YYYY-MM-DD): ");
			String birthcus = sc2.nextLine();
			System.out.print("Your address: ");
			String address = sc2.nextLine();
			
			if(mysql.addInfo(idcus, namecus, sexcus, birthcus, address)==1)
				System.out.println("Added customer successfully!");
			else
				System.out.println("Please check your information and try again!");
		}
		
		else if (tmp == 2) {
			System.out.print("Enter ID of the customer to delete: ");
			Scanner sc2 = new Scanner(System.in);
			String idcus = sc2.nextLine();
			
			if(mysql.delInfo(idcus)==1)
				System.out.println(idcus + " has been deleted!");
			else
				System.out.println("Please check your information and try again!");
		}
		
		else if (tmp == 3) {
			System.out.println("Add one telephone number into database: ");
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Choose brand: ");
			String brand = sc2.nextLine();
			System.out.print("Enter phone number: ");
			String phone = sc2.nextLine();
			System.out.print("Enter amount: ");
			long money = sc2.nextLong();
			
			if(mysql.addTel(phone, brand, money)==1)
				System.out.println("Added telephone number " + phone + " successfully!");
			else
				System.out.println("Please check your information and try again!");
			
		}
		
		else if (tmp == 4) {
			System.out.println("Choose one telephone number to delete: ");
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Enter phone number: ");
			String phone = sc2.nextLine();
			
			if(mysql.delPhone(phone)==1)
				System.out.println("Deleted telephone number " + phone + " successfully!");
			else
				System.out.println("Please check your information and try again!");

		}
		
		else if (tmp == 5) {
			System.out.println("Add external account: ");
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Enter ID: ");
			String exid = sc2.nextLine();
			System.out.print("Enter customer name: ");
			String exname = sc2.nextLine();
			System.out.print("Enter bank name: ");
			String excomp = sc2.nextLine();
			System.out.print("Enter balance: ");
			long exbal = sc2.nextLong();
			
			if(mysql.addExAcc(exid, exname, excomp, exbal)==1)
				System.out.println("Added external account successfully!");
			else
				System.out.println("Please check your information and try again!");
		}
		
		else if (tmp == 6) {
			System.out.println("Delete external account: ");
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Enter ID: ");
			String exid = sc2.nextLine();
			System.out.print("Enter bank name: ");
			String excomp = sc2.nextLine();
			
			if(mysql.delExAcc(exid, excomp)==1)
				System.out.println("Deleted external account successfully!");
			else
				System.out.println("Please check your information and try again!");
			
		}
		
		else if (tmp == 7) {
			System.out.println("Delete MooMoo account: ");
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Enter ID: ");
			String idacc = sc2.nextLine();
		
			if(mysql.delAccount(idacc)==1)
				System.out.println("Done!");
			else
				System.out.println("Please check your information and try again!");
		}
		
		else {
			System.out.println("You have pressed the wrong number...");
		}
		
		System.out.println();
		tmp=-1;
		System.out.print("Press 1 to continue: ");
		tmp = sc1.nextInt();
		
		} while(tmp==1);
		
		try {
			mysql.Disconnect();
			} catch (SQLException e) {
		e.printStackTrace();
		}
	}
}

