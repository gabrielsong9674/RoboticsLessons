
public class ExerciseOne
{
	public static void main(String[]args)
	{
		int variable = 2;
		double x1 = -2;
		double y1 = -1;
		double x2 = 0;
		double y2 = 0;
		/*
		if variable is equal to 1 then calculate the distance between 2 points
		if variable  is equal to 2 then calculate the slope between 2 points
		*/
		if(variable == 1) {
			System.out.println("The distance between (" +x1+ ", " +y1+") and (" +x2+ "," +y2+ ") is " +calcDist(x1, x2, y1, y2));
		}
		else if(variable == 2){
			System.out.println("The slope of (" + x1+ ", " +y1+") and (" +x2+ "," +y2 + ") is " +calcSlope(y2, y1, x2, x1));		}
	
	}
	
	static double calcDist(double numx1, double numx2, double numy1, double numy2)//make sure you enter parameters
	{
		return Math.pow(Math.pow(numx2 - numx1, 2) + Math.pow(numy2 - numy1, 2),.5);
			
	}
	
	static double calcSlope(double numy1, double numy2, double numx1, double numx2)//make sure you enter parameters
	{
		return numy2 - numy1 / numx2 - numx1;
		
	}
}