package voidFunctions;

/*
Lesson _05
In this exercise, you will use void functions to calculate magnitude and the direction of a vector from their x-component, and y-component

Feel free to look up the formulas for both finding the direction and the magnitude of a vector
Remember that you don't need parameters for this exercise, because the variables are static

Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */
 
public class exercise
{
	static double xComponent, yComponent, magnitude, direction;
	
	public static void main(String[]args)
	{
		xComponent = 6;
		yComponent = 8;
		calcTheta();
		calcMagnitude();
		System.out.println("The direction of the vector is " +direction);
		System.out.println("The magnitude of the vecotr is "+magnitude);
		
	}

	public static void calcTheta() //use this function to calculate the direction of the vector
	{
		direction = Math.atan(yComponent/xComponent);
	}
	
	public static void calcMagnitude() //use this function to calculate the magnitude of the vector
	{
		magnitude = Math.pow(Math.pow(xComponent, 2) + Math.pow(yComponent, 2),0.5);
		
	}
}
