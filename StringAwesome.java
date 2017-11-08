
public class StringAwesome {
public static void main(String[] args) {
	String danny = "awesomiie";
	int length = danny.length();
	for (int i =  length; i > 0; i--) {
		System.out.println(danny.substring(0, i));
	}
}
}
