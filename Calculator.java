/*
Lesson _06
In this exercise, you will use classes to create a simple calculator
The operation you will be performing will depend on the run variable
For this exercise please create all your classes in one file
Feel free to look at the example file while doing this exercise
Good luck!
 */

public class Calculator 
{
	public static void main(String[]args)
	{
		int run = 1;
		// 1 is addition
		// 2 is subtraction
		// 3 is multiplication
		// 4 is division
		
		//variables used for calculations
		double numOne = 34;
		double numTwo = 67;
		
		//make objects
		Addition Add = new Addition(numOne, numTwo);
		Subtraction Subtract = new Subtraction(numOne, numTwo);
		Multiplication Multiply = new Multiplication(numOne, numTwo);
		Division Divide = new Division(numOne, numTwo);
		//run conditional statements using the run variable
		if(run == 1) {
			System.out.println("The sum is " +Add.returnSum());
		}
		if(run == 2) {
			System.out.println("The difference is "+Subtract.returnDiff());
		}
		if(run == 3) {
			System.out.println("The product is " + Multiply.returnProduct());
		}
		if(run == 4) {
			System.out.println("The quotient is "+ Divide.returnQuotient());
		}
	}

}

/*
 Make a class called Addition
 * Make necessary variables
 * Make a constructor for that class
 * Make a function called performOperation() that returns the sum of both the numbers 
 */
class Addition{
	double num;
	double num2;
	public Addition(double n, double ntwo) //This is what we call the constructor, it acts like parameters (used to import variable from other classes)
	{
		num = n;
		num2 = ntwo;
	}
	public double returnSum(){
		return num+num2;
	}
}
class Subtraction{
	double num;
	double num2;
	public Subtraction(double n, double ntwo) {
		num = n;
		num2 = ntwo;
	}
	public double returnDiff() {
		return num - num2;
	}
}
class Multiplication{
	double num;
	double num2;
	public Multiplication(double n, double ntwo) {
		num = n;
		num2 = ntwo;
	}
	public double returnProduct() {
		return num*num2;
	}
}
class Division{
	double num;
	double num2;
	public Division(double n, double ntwo) {
		num = n;
		num2 = ntwo;
	}
	public double returnQuotient() {
		return num/num2;
	}
}



/*
Make a class called Subtraction
* Make necessary variables
* Make a constructor for that class
* Make a function called performOperation() that returns the difference of both the numbers 
*/

/*
Make a class called Multiplication
* Make necessary variables
* Make a constructor for that class
* Make a function called performOperation() that returns the product of both the numbers 
*/

/*
Make a class called Division
* Make necessary variables
* Make a constructor for that class
* Make a function called performOperation() that returns the quotient of both the numbers 
*/
