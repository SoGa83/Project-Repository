

// This program allows us to implement a Book class.
// This class includes attributes such as title, author, ISBN, and price
// Additionally, this class contains the following methods: A constructor (in order to create a new book object), 
// mutators and accessors, a method to find the number of created books, equals method (to compare 2 book objects),
// and a toString (to print each book object)

package Bookstore;

/**
 * @author sophia
 *
 */
public class Book {
	
	//introduces attributes
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private static int count=0;
		
	//constructor that also initializes the values for the attributes
	/**
	 * @param title
	 * @param author
	 * @param ISBN
	 * @param price
	 */
	public Book (String title, String author, long ISBN, double price){
        this.title= title;
        this.author= author;
        this.ISBN= ISBN;
        this.price= price;
        count++;//increases the number of total books as a book object is being created
	}

	//accessors and mutators
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	//returns the number of created books
	public static int findNumberOfCreatedBooks(){
	    return count;
	   }
	//compares 2 book objects and returns true if both price and ISBN are equal
	public boolean equals(Book b) {
		boolean bool =ISBN==b.getISBN() && price==b.getPrice();
		return bool;
	}
	
	//toString method that prints the attributes of the book object
	public String toString() {
		String str = "\nAuthor: " + author +"\nTitle: "+ title+ "\nISBN: "+ISBN+ "\nPrice: "+ price;
		return str;
	} 
	
	//a method to find books by a specific author 
	public static String findBooksBy(Book[] inventory, String authorName) {
		String str ="";
		for (int i=0;i<inventory.length;i++) {
			
			if (inventory[i] != null && inventory[i].getAuthor().equals(authorName)) {
				str+= inventory[i].toString();
				System.out.println();
			}
			else {
				 str+=  "Sorry, there is no book written by this author in the bookstore!";
				 break;
			}
			
		}
		return str;
		
	}
	
	public static String findCheaperThan(Book[] inventory, double pr ) {
		String str ="";
		for (int i=0;i<inventory.length;i++) {
			
			if (inventory[i] != null && inventory[i].getPrice()<pr) {
				str+= inventory[i].toString();
				System.out.println();
			}
			
			
		}
		return str;
		
	}
}
