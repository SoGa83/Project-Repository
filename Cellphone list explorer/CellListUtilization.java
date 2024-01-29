package Pckg1;
// -----------------------------------------------------
// Assignment: 3
// Question: 1
// Written by: Soofia Garmeh 40201823
// Due date: Dec 2
// -----------------------------------------------------
// this program checks the methods implemented in the celllist class
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 *  class for doing operations on CellList.
 */
public class CellListUtilization {
	
	/**
     * Initializes a CellList by reading data from a file.
     *
     * @param list the CellList to initialize
     */

	public static void initializeList(CellList list){
		
		try {
			Scanner sc = new Scanner ( new FileInputStream("Cell_info.txt"));
			while(sc.hasNextLine()) {
				
					long serialNumber = sc.nextLong();
					String brand = sc.next();
					double price = sc.nextDouble();
					int year = sc.nextInt();
	                    CellPhone cellPhone = new CellPhone(serialNumber,brand, year, price); //creates the cellphone object
	                    list.addToStart(cellPhone); //adds it to the start of the list 
	                   
	                }
	            
		
		}
		catch (FileNotFoundException e) {
			System.out.println("File could not be opened");
		}
		
	}
	 /**
     * Main method for checking different celllist methods
     *
     * @param args command-line arguments
     */
	public static void main(String[] args) {
		System.out.println("Welcome to CellListUtilization program!");
		CellList list1 = new CellList();
		CellList list2 = new CellList();
		CellPhone phone1 = new CellPhone(4758279, "GooglePixel", 2000, 123.50);
		CellPhone phone2 = new CellPhone(4758262, "Nothing", 2000, 103.00);
		
		Scanner in = new Scanner(System.in);
		
		initializeList(list1); //initializes the first list 
		list1.showContents();// shows the contents of list1
		
		System.out.println("\n --------- Cloning a CellList:");
		list2 = list1.clone();
		list2.showContents();
		
		System.out.println("\n ---------- Adding to start: ");
		list1.addToStart(phone1);
		list1.showContents();
		
		System.out.println("\n ---------- inserting at an index ");
		System.out.println("enter an index:");
		int n = in.nextInt();
		list1.insertAtIndex(phone2,n);
		list1.showContents();
		
		System.out.println("\n ---------- Deleting from an index: ");
		System.out.println("enter an index:");
		 n = in.nextInt();
		list1.deleteFromIndex(n);
		list1.showContents();
		
		System.out.println("\n ---------- Deleting from start: ");
		list1.deleteFromStart();
		list1.showContents();
		
		System.out.println("\n --------- Replacing at an index: ");
		System.out.println("enter an index:");
		n = in.nextInt();
		list1.replaceAtIndex(n,phone1);
		list1.showContents();
		
		System.out.println("\n --------- checking for equality: ");
		if(list1.equals(list2))
		System.out.println("two lists are equal" );
		else 
		System.out.println("two lists are not equal" );
		
		System.out.print("\n ---------- Searching for a sepcific seial number: \nPlease enter a serial number: ");
		
		long sn = in.nextLong();
		if(list1.contains(sn)) {
			System.out.println(list1.find(sn).phone); 
		}
		
		
		
		
		
		
	}
	

}
