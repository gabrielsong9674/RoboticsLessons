package Exercises;

/*
Lesson _04
In this exercise you will use for loops to create a refrigerator program 
You are going to use this program cool some food to a cretain temperature
 */

/*
 * @author Surya
 */

public class Exercise 
{
	public static void main(String[]args)
	{
		double coolingSpeed = 10; //Temperature of object decreases 10 degrees per hours
		double initialTempOfFood = 81;
		double tempRequired = 51;
		double time = 0;//should be in hours
		while(initialTempOfFood > 51) {
			initialTempOfFood-=10;
			time++;
		}
		System.out.println("It took " + time+ " hours to decrease the temperature to "+ tempRequired);
	}

}
