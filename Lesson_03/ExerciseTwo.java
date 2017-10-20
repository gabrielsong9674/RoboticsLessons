
/*
Lesson _03
In this exercise, you will use nested for loops in order to print the following:
 \:::::::/
  \:::::/
   \:::/
    \:/
Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */

public class ExerciseTwo {
	
	public static void main(String[] args) {
		int i;
		for ( i = 1; i <= 4; i++) 
		{
			for (int j = 1; j < i+1; j++) {
				System.out.print(" ");
			}
			System.out.print("\\");
			for (int k = 0; k < 9-(2*i); k ++) {
				System.out.print(":");
			}
			System.out.print("/");
			System.out.println();
		}
	}
	
}
