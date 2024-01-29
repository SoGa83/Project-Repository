
// this program implements a linked list of cellphone objects and has additional methods

package Pckg1;
/**
 * error when there is no such element existing in the list
 */
class NoSuchElementException extends Exception {
	
	/**
     * Constructs a NoSuchElementException with the specified detail message.
     *
     * @param message the detail message
     */
    public NoSuchElementException(String message) {
        super(message);
    }
	
}
/**
 * Represents a linked list of CellNode objects that store CellPhone data.
 */
public class CellList {
	/**
     * Represents a node in the linked list storing CellPhone data.
     */
	class CellNode{
		CellPhone phone; //an object of the cellphone, represents the data stored in a node 
		CellNode next; //a pointer to a cellnode object, must be of type cell node
		
		/**
	     * constructors
	     */
		public CellNode(){
			phone = null;
			next = null;
					
		}
		
		public CellNode(CellPhone phone,CellNode next ){
			this.phone = phone;
			 this.next = next;
		}
		public CellNode(CellNode c){
			this.phone = c.phone; //sets the cellphone object
			this.next= c.next; //craetes a pointer to the next node
			
			
		}
		/**
	     * clone method for a node
	     */
		public CellNode clone() {
			return new CellNode(this.phone.clone(), this.next.clone());
		}
		/**
	     * getters and setters
	     */
		public CellPhone getPhone() {
			return phone;
		}
		public void setPhone(CellPhone phone) {
			this.phone = phone;
		}
		public CellNode getNext() {
			return next;
		}
		public void setNext(CellNode next) {
			this.next = next;
		}
		
	}// end of cellnode class
	
	 //attributes of celllist class
	CellNode head; //a pointe to the head 
	int size ;
	/**
     * default constructor
     */
	public CellList() { //creates an empty list 
		head = new CellNode();
		size = 0;
	}
	/**
     * Constructs a CellList by copying another CellList.
     *
     * @param l the CellList to copy
     */
	public CellList (CellList l) {//copy constructor 
		if (l.head == null) {
			head = null;
		}
		else {
			head = null; 
			CellNode t1,t2,t3;
			t1 = l.head;
			t2= null;
			t3= null; 
			
			while (t1 != null) {
				if ( head ==null) { 
					t2 = new CellNode(t1);
					head = t2;
				}
				else {
					t3 = new CellNode(t1);
					t2.next  = t3;
					t2 = t3;
				}
				t1 = t1.next;
			}
			t2 = t3 = null;
		}
		this.size = l.size;//fixes the size of the copy list 
	}
	/**
     * Adds a CellPhone to the start of the list.
     *
     * @param c the CellPhone to add
     */
	public void addToStart(CellPhone c){
		CellNode n = new CellNode(c,head);
		head = n;
		n = null;
		size++;
		
	}
	/**
     * Checks if the index is valid within the list.
     *
     * @param index the index to check
     * @return true if the index is valid, otherwise throws NoSuchElementException
     * @throws NoSuchElementException if the index is invalid
     */
	public boolean isIndexValid(int index) throws NoSuchElementException {
	    if (index < 0 || index >= size) {
	        throw new NoSuchElementException("Invalid index: " + index);
	    }
	    return true;
	}
	 /**
     * finds the selected pointer
     *
     * @return selected pointer
     */
	private CellNode goToPointer(int index) { //loop through list until finds the index and returns the pointer
		CellNode t = head;
		
			for (int i = 0; i < index; i++)
				t = t.next;
		
		return t;
	}
	
	 /**
     * inserts at an index
     * @param CellPhone phone
     * @param int index
     * 
     */
	public void insertAtIndex(CellPhone phone, int index)  {
		try {
	    if (index < 0 || index > size) {
	        throw new NoSuchElementException("Invalid index");
	    }

	    CellNode newNode = new CellNode(phone, null);
	    if (index == 0) {
	        newNode.next = head;
	        head = newNode;
	    } else {
	        CellNode prev = goToPointer(index - 2);
	        newNode.next = prev.next;
	        prev.next = newNode;
	    }
	    size++;
		}catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	/**
     * deletes from the start
     * 
     */
	public void deleteFromStart() {
	    if (head != null) {
	        head = head.next;
	        size--;
	    }
	    
	}
	/**
     * deletes from an index
     * @param int index
     * 
     */
	
	public void deleteFromIndex(int index) {
		try {
	    if (!isIndexValid(index)) {
	        return; 
	    }

	    if (index == 0) {
	        head = head.next;
	    } else {
	        CellNode prevNode = goToPointer(index - 2);
	        prevNode.next = prevNode.next.next;
	    }
	    size--;
		} catch(NoSuchElementException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	/**
     * replaces at an index
     * @param CellPhone newPhone
     * @param int index
     * 
     */
	public void replaceAtIndex(int index, CellPhone newPhone) {

	    CellNode t = head;
	    for (int i = 0; i < index; i++) {
	        t = t.next;
	    }

	    t.phone = newPhone;
	}
	/**
     * find cellphone with a specified serial number
     * @param long serialNumber
     * @return the pointer to that node
     */
	public  CellNode find(long serialNumber) {
	    CellNode t = head;
	    int iterations = 0;

	    while (t != null) {
	        iterations++;
	        if (t.phone.getSerialNumber() == serialNumber) {
	            System.out.println("Iterations taken: " + iterations);
	            return t; // Node found with the specified serial number
	        }
	        t = t.next;
	    }

	    System.out.println("Iterations taken: " + iterations);
	    return null; // Serial number not found in any node
	}
	/**
     *  checks if the list contains s node with a specific serial number
     *  @param long serialNumber
     *  @return true if contains
     */
	public boolean contains(long serialNumber) {
	    CellNode t = head;

	    while (t != null) {
	        if (t.phone.getSerialNumber() == serialNumber) {
	            return true; // Serial number found in a node
	        }
	        t = t.next;
	    }

	    return false; // Serial number not found in any node
	}
	/**
     * shows content of the list
     * 
     */
	public void showContents() {
		System.out.println("size: "+ size);
	    CellNode t = head;

	    System.out.println("List Contents:");
	   int count =0;
	    while (t.next != null) {
	        System.out.print(t.phone); // Assuming CellPhone has a toString() method
	        System.out.print(" ---> ");
	        t = t.next;
	        count++;
	        if (count %3==0)
	        	System.out.println();
	    }
	    System.out.println(" X ");
	}
	
	/**
     * checks for equality of 2 lists
     * @param CellList other
     * @return true of equal
     * 
     */
	public boolean equals(CellList other) {
	    if (other == null || size != other.size) {
	        return false; // If otherList is null or sizes are different, lists are not equal
	    }

	    CellNode t1= head;
	    CellNode t2 = other.head;

	    while (t1 != null) {
	        if (!t1.phone.equals(t2.phone)) {
	            return false; // If CellPhone objects are not equal, lists are not equal
	        }
	        t1 = t1.next;
	        t2 = t2.next;
	    }
	    // If all comparisons passed, lists contain similar objects
	    return true;
	}
	 /**
     * Clones the CellList.
     *
     * @return a new CellList with the same contents as this list
     */
	@Override
	public CellList clone() {
		return new CellList(this);
		
	}

}
