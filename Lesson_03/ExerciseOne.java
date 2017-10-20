/*
Lesson _03
In this exercise, you will use for loops in order to print both even and odd numbers
The number of odd/even numbers will depend on the variable count.
If the value for count is 5, your exercise Result should look like the following:
Here are 5 even numbers:
2
4
6
8
10
Here are 5 odd numbers:
1
3
5
7
9
Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */

public class ExerciseOne 
{
	public static void main(String[]args)
	{
		int count = 6;
		System.out.println("Here are "+count+" even numbers");
		for(int i = 2; i <= count*2; i += 2) {
			System.out.println(i);
		}
		System.out.println("Here are "+count+" odd numbers");
		for(int j = 1; j <= count*2; j += 2) {
			System.out.println(j);
		}
	}
}
