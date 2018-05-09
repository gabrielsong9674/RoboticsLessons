import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FortuneTeller 
{
	//these variables are used to store input from the user
	static Scanner scan = new Scanner(System.in);
	static String firstName = " "; // used for input of first name
	static String lastName = " "; //used for input of last name
	static String numString; // I used this string to convert dollars to a string
	static int month; // birth month
	static int year; // birth year
	static int day;  //birthday
	static int num; //used for input of the bank account number
	static double dollars; //used for favorite number and generating amount $
	static int balance; //used for favorite number input
	
	//these variables are used to generate the fortunes
	static char favoriteLetter;
	static String both; //combines first and last name
	static String reverse;// reverses first and last name
	static String favoritePalindrome; //combines both and reverse
	static String favoriteColor; //red or blue
	static String favoriteAnimal; //cat or dog
	static String favoriteCar; //F150 or Minivan
	static String favoriteNum; 
	static int intDollars;
	static String math;
	
	public static void main(String[] args)
	{
		//gets the first name from the user and stores it in the String variable firstName
		System.out.println("What is your first name?");
		firstName = scan.next();
		//System.out.println("Thank you " + firstName + ".");
		
		//gets the last name from the user and stores it in the String variable lastName
		System.out.println("What is your last name?");
		lastName = scan.next();
		//System.out.println("Thank you " + firstName + " " + lastName + ".");
		 
		//gets the birth month from the user and stores it in the String variable birthMonth
		System.out.println("What month were you born? Enter a number");
		month = scan.nextInt();
		//System.out.println(month);
		
		//gets the birth year from the user and stores it in the String variable birthYear
		System.out.println("What year were you born? Enter four numbers");
		year = scan.nextInt();
		//System.out.println(year);
		
		//gets the birth day from the user and stores it in the String variable birthDay
		System.out.println("What day were you born? Enter a number");
		day = scan.nextInt();
		//System.out.println(day);
		
		//gets the bank account number from the user and stores it in the String variable bankNum
		System.out.println("What is your bank account number?");
		num = scan.nextInt();
		//System.out.println(num);
		
		//gets the bank account balance from the user and stores it in the String variable balance
		System.out.println("How much is in your bank account? Include decimals please");
		dollars = scan.nextDouble();
		
		generateFortune();
	}

	public static void getFavoriteLetter()//first letter of lastname capitalized
	{
		if(lastName.equalsIgnoreCase("Tator"))
		{
			System.out.println("You will teach math next year!");
		}
		else 
		{
		favoriteLetter = lastName.toUpperCase().charAt(0);
		//each letter in a string is assigned a number
		//charAt will print the letter at a certain location
		//for example "grace" g-0, r-1, a-2, c-3, e-4
		System.out.println("Your favorite letter is " + favoriteLetter + " .");
		}
	}
	public static void getFavoritePalindrome()
	{
		both = firstName.toLowerCase() + lastName.toLowerCase();
		reverse = new StringBuffer(both).reverse().toString();
		favoritePalindrome = both + reverse;
		System.out.println("Your favorite palindrome is " + favoritePalindrome + ".");
	}
	public static void getFavoriteColor()//blue if their birthday day divides evenly by their birthday month, red otherwise
	{
		if(day % month == 0)// % checks for remainders after dividing 
		{
			favoriteColor = "blue";
		}
		else
		{
			favoriteColor = "red";
		}
		System.out.println("Your favorite color is " + favoriteColor + ".");
	}
	public static void getFavoriteAnimal() // a dog if their last name is longer than their first name, a cat otherwise.
	{
		if(lastName.length() > firstName.length())
		{
			favoriteAnimal = "dog";
		}
		else
		{
			favoriteAnimal = "cat";
		}
		System.out.println("Your favorite animal is a " + favoriteAnimal + ".");
	}
	public static void getFavoriteCar() //Tesla if their bank account number is greater than their birthday year, a Leaf otherwise
	{
		if(num > year)
		{
			favoriteCar = "Ford F150";
		}
		else
		{
			favoriteCar = "Minivan";
		}
		System.out.println("Your favorite car is a " + favoriteCar + "." );
	}
	public static void getFavoriteNum()// joining all digits of the amount of money in the bank, without the decimal place, and reversed. 
	{
	dollars = dollars * 100;
	intDollars = (int) dollars;
	System.out.println(intDollars);
	numString = Integer.toString(intDollars);
	favoriteNum = new StringBuffer(numString).reverse().toString();
	System.out.println("Your favorite number is " + favoriteNum + "." );
	
	}
	public static void getFavoriteLanguage()
	{
		System.out.println("Your favorite language is Java.");
	}
	public static void generateFortune()
	{
		getFavoriteLetter();
		getFavoritePalindrome();
		getFavoriteColor();
		getFavoriteAnimal();
		getFavoriteCar();
	 	getFavoriteNum();
	 	getFavoriteLanguage();
	}	
}
