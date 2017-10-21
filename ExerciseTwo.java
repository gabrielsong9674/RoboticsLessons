
/*
Lesson _01
In this exercise, we will use variables to perform Arithmetic operations
You will print the sum, the difference, the product and the quotient
Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */

public class ExerciseTwo 
{

    public static void main(String[] args) 
	{
		double numOne, numTwo, sum, diff, product, quotient;
		numOne = 4.5676;
		numTwo = 7.7765;
		sum = numOne + numTwo;
		diff = numOne - numTwo;
		product = numOne * numTwo;
		quotient = numOne / numTwo;
		System.out.println("The sum of " + numOne + " and " + numTwo + " is " + sum + ".");
		System.out.println("The difference of "+numOne+ "and" + numTwo + "is" + diff + "." );
		System.out.println("The product of " +numOne+ "and" +numTwo+ "is" + product +"." );
		System.out.println("The quotient of " +numOne+ "and" +numTwo+ "is" + quotient +"." );
  	}
    
}
