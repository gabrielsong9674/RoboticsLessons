
public class forLoopsComplex {
	public static void main(String[] args) {

		for (int i = 0; i < 6; i++) {
			System.out.print("|");
			for (int j = 0; j < i; j++) {
				System.out.print("*");
			}
			System.out.print("\\");
			for(int k = 12; k > 2*i +1 ; k--) {
				System.out.print("*");
			}
			System.out.print("/");
			for(int l = 0; l < i; l++) {
				System.out.print("*");
			}
			System.out.print("|");
			System.out.println();
			
			
		}
	}
}
