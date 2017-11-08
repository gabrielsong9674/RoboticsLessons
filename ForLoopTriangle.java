
public class ForLoopTriangle {
		public static void main(String[] args) {
			String danny = "awee";
			int length = danny.length();
			for (int i =  length; i > 0; i--) {
				System.out.print(danny.substring(0, i));
				for(int k =length * 2 +2; k > i * 2 +1; k--) {
					System.out.print("*");
				}
				
				
				System.out.print(danny.substring(0, i));
				System.out.println();
				
			}
		//7  1
		//6  3
		//5  5
		//4  7
		//3  9
		//2  11
		//1  13
			
			
		}
}
