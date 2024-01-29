
// -----------------------------------------------------
// Assignment 0
// Question: 2
// Written by: Soofia Garmeh 40201823
// -----------------------------------------------------

// The purpose of this program is to create a driver that lets the 
//bookstore program run. the program starts off by asking he user 
//how many books they want to have in their bookstore, then lets 
//the user add as many books as they want and, update any book, 
//or find the books written by a specific author. finally this
//program can show the books with a price lower than the given 
//value by the user and terminates if the used enters 5, the program terminates

package Bookstore;

import java.util.Scanner;//imports the scanner

/**
 * @author sophia
 *
 */
public class Driver {

	// introducing variables
	final private static String PASSWORD = "249";
	private static String enteredPassword;
	static int choice;
	static int maxBooks = 0;
	static int numOfUsedTries = 0; // used for password option 1
	static int numOfAddedBooks = 0; // used for option 1

	// a method to check password
	public static boolean checkPassword() {
		return (enteredPassword.equals(PASSWORD));
	}

	// main method
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// welcome message
		System.out.print(
				"Welcome to the bookstore program!\nPlease enter the maximum number of books you wish the bookstore to have:");
		Scanner in = new Scanner(System.in);// introduces the scanner
		maxBooks = in.nextInt();// stores the max number of books
		in.nextLine();// clears the scanner
		Book[] inventory = new Book[maxBooks];// creates a Book array to keep track of book objects

		while (true) { // exterior do-while that keeps printing the main menu until user enters 5

			while (true) {// middle loop that prints the main menu and checks for password in case of
							// option 1 or 2
				System.out.print("\nWhat do you want to do?\n" + "\t1.\tEnter new books (password required)\n" + //
						"\t2.\tChange information of a book (password required)\n" + //
						"\t3.\tDisplay all books by a specific author\n" + //
						"\t4.\tDisplay all books under a certain a price.\n" + //
						"\t5.\tQuit\n" + //
						"please enter your choice >");
				choice = in.nextInt();// stores the entered option
				in.nextLine();

				if (choice == 1 || choice == 2) { // checks password for option 1 or 2
					System.out.print("Please enter the password:");
					for (int i = 0; i < 3; i++) { // checks for 3 times
						enteredPassword = in.nextLine(); // stores the entered password

						if (checkPassword()) { // checks if the password is correct
							break; // leaves the for loop if the right password is entered
						} else {
							if (choice == 1) {// if the option entered is 1, program must exit after 12 illegal
												// passwords
								numOfUsedTries++;
								if (numOfUsedTries != 12 || i != 2) {
									System.out.print("Wrong password, please try agian:");
								}

								else {// TERMINTATE THE PROGRAM AFTER 12 ILLEGAL PASSWORDS
									System.out.println(
											"Program detected suspicous activities and will terminate immediately!");
									System.exit(0);
								}
							}
							if (choice == 2) {// if option 2 is entered, the program simply redisplays the main menu
												// after 3 illegal passwords
								if (i != 2) {

									System.out.print("Wrong password, please try agian:");
								}
							}
						}
					} // ---------------end of checking for password for option 1 and 2

					// if(( choice==1 || choice==2)) checkPassword())

					switch (choice) {

					case 1: { // case 1: adding new book(s)
						if (checkPassword()) {
							System.out.print("How many books do you wish to add to the bookstore?");
							numOfAddedBooks = in.nextInt();// stores the number of added books
							in.nextLine();

							// checks if there is enough space for the user to add the books
							if (Book.findNumberOfCreatedBooks() + numOfAddedBooks > maxBooks) {
								// warns if the total number of books is more than the limit
								System.out.println(
										"There is not enough space to add this number of books to the bookstore!\n"
												+ "You can only add " + (maxBooks - Book.findNumberOfCreatedBooks())
												+ " to this bookstore");
								break;
							} else {
								// int numberOfBooks = Book.findNumberOfCreatedBooks() + numOfAddedBooks;
								// stores the information of each added book and creates a book object by each

								for (int i = 0; i < numOfAddedBooks; i++) {
									System.out.println(
											"Please enter the information of the new book nomber " + i + " below:");
									System.out.print("Title: ");
									String newTitle = in.nextLine();
									System.out.print("Author: ");
									String newAuthor = in.nextLine();
									System.out.print("ISBN: ");
									long newISBN = in.nextLong();
									in.nextLine();
									System.out.print("Price: ");
									double newPrice = in.nextDouble();
									in.nextLine();
									inventory[Book.findNumberOfCreatedBooks()] = new Book(newTitle, newAuthor, newISBN,
											newPrice);

								}
							}
						}
					}
						break;// end of adding new book(s)

					case 2: {// updating an existing book
						if (checkPassword()) {
							System.out.print("Which book number do you wish to update? Starting from book number 0: ");
							int index = in.nextInt(); // store the index typed by the user
							in.nextLine();
							// replays the error message and asks user to try again
							while (index >= Book.findNumberOfCreatedBooks()) {
								System.out.print(
										"There is no book existing at this index. Please try again: if you wish to quit this operation, enter -1");
								index = in.nextInt();
								in.nextLine();
								if (index == -1) {
									break; // gets out of the loop if the entered number is -1, redisplays the main menu
								}
								System.out.println("The current informatino of book number " + index + ":");
								System.out.println(inventory[index]); // prints the information of the chosen book
								while (true) {
									System.out.print("What information would you like to change?\n" + "	   1. author\n"
											+ "    2. title\n" + "	   3. ISBN\n" + "	   4. price\n"
											+ "	   5. Quit\n" + "Enter your choice >");
									int option = in.nextInt();
									in.nextLine();
									switch (option) {
									case 1: {
										System.out.print("Please enter the new name of the author: ");
										inventory[index].setAuthor(in.nextLine());
									}

									case 2: {
										System.out.print("Please enter the new title of the book: ");
										inventory[index].setTitle(in.nextLine());
									}

									case 3: {
										System.out.print("Please enter the new ISBN of the book: ");
										inventory[index].setISBN(in.nextInt());
										in.nextLine();
									}
									case 4: {
										System.out.print("Please enter the new price of the book: ");
										inventory[index].setPrice(in.nextInt());
										in.nextLine();
									}
									case 5: {// exits this operation if user enters 5
										break;
									}
									}

								}

							}

						}
					}
						break; // end of updating the information (case 2 )

					case 3: { // case 3, finding books by a specific author

						System.out.println("Enter the name of author you wish to search:");
						String AuthorName = in.nextLine(); // stores the name of the author user wishes to find books
															// from
						System.out.println(Book.findBooksBy(inventory, AuthorName)); // finds books by that specific
																						// author
						break;
					}

					case 4: {
						System.out.println("Please enter the max price you want for the books: ");
						double pr = in.nextDouble();
						in.nextLine();
						System.out.println(Book.findCheaperThan(inventory, pr)); // finds books by that specific author
						break;
					}

					case 5: {
						System.out.println("Thanks for using the bookstore program!");
						System.exit(0);
						break;
					}
					}
				} // end of switch

			} // end of middle loop for redisplaying the main menu

		} // end of exterior loop that keeps printing the main menu until user enters 5

	} // end of main method
}
