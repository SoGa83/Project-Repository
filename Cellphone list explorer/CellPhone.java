// -----------------------------------------------------
// Assignment: 3
// Question: 1
// Written by: Soofia Garmeh 40201823
// Due date: Dec 2
// -----------------------------------------------------
// The purpose of this program is to implement a cellphone class in ode to crate cellphone object with attributes such as serialnumber,brand, year and price
//it also has methods for cloning cellphone objects and comparing them with another cellphone object
package Pckg1;

import java.util.Scanner;
/**
 * The CellPhone class represents a basic cell phone with attributes such as serial number, brand, year, and price.
 */
public class CellPhone {
	private long serialNumber;
	private String brand;
	private int year;
	private double price;
	
	/**
     * getters and setters
     */
	public long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	/**
     * default constructor
     */
	public CellPhone() {
		
	}
	/**
     * parameterized constructor
     */
	public CellPhone(long serialNumber, String brand, int year, double price) {
		this.serialNumber= serialNumber;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	/**
     * copy constructor
     */
	public CellPhone(CellPhone c, long sn) {
		serialNumber = sn;
		this.brand = c.brand;
		this.year = c.year;
		this.price = c.price;
	}
	
	/**
     * method for cloning a cellphone object
     */
	@Override
	public CellPhone clone() {
		System.out.println("Please enter a new serial number");
		Scanner sc = new Scanner(System.in);
		return new CellPhone(this, sc.nextLong());
	}
	/**
     * method for comparing 2 cellphone objects
     */
	@Override
	public boolean equals(Object o) {
		if (o== null ||  o.getClass() != this.getClass()) {
			return false ;
		}
			else {
				CellPhone c = (CellPhone)o;
				return(c.getBrand().equals(this.getBrand()) && c.getYear()==this.getYear() && c.getPrice() == this.getPrice());
						
			}
		
	}
	@Override
	public String toString() {
		return ( "[" + serialNumber + ": " + brand +" "+ price+"$ " + year+ "]");
				
	}
	
	
	
}
