import javax.swing.JOptionPane;

/*
Lesson _02
In this exercise, you will use if, else if, and else statements to determine dedication of robotics members depending on build season hours
For example:
300+ hours: dedication level = "dedication on steroids"
200+ hours: dedication level = "very dedicated"
150+ hours: dedication level = "really dedicated"
120+ hours: dedication level = "dedicated"
90+ hours: dedication level = "average dedication"
50+ hours: dedication level = "un-dedicated"
else: dedication level = "Eamon"
Good Luck Completing the exercise!
*/

/*
 * @author Surya
 */

public class ExerciseOne {
	public static void main(String[] args) {
		int buildSeasonHours;
		String dedicationLevel;
		String hours = JOptionPane.showInputDialog(null, "What are your hours?");
		buildSeasonHours = Integer.parseInt(hours);
		if (buildSeasonHours >= 300) {
			dedicationLevel = "dedication on steroids";
			System.out.println(dedicationLevel);
		} else if (buildSeasonHours >= 200 && buildSeasonHours <= 299) {
			dedicationLevel = "very dedicated";
			System.out.println(dedicationLevel);
		} else if (buildSeasonHours >= 150 && buildSeasonHours <= 199) {
			dedicationLevel = "really dedicated";
			System.out.println(dedicationLevel);
		} else if (buildSeasonHours >= 120 && buildSeasonHours <= 149) {
			dedicationLevel = "dedicated";
			System.out.println(dedicationLevel);
		} else if (buildSeasonHours >= 90 && buildSeasonHours <= 119) {
			dedicationLevel = "average dedication";
			System.out.println(dedicationLevel);
		} else if (buildSeasonHours >= 50 && buildSeasonHours <= 89) {
			dedicationLevel = "un-dedicated";
			System.out.println(dedicationLevel);
		} else {
			dedicationLevel = "Eamon";
			System.out.println(dedicationLevel);
		}
	}
}