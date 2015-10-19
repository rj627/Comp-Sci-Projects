package sortLab;

public class APReviewMain {

	public static int[] testArray = {3, 4, 5};
	
	public static void increment(int n) { n++; }
	
	public static void firstTestMethod()
	{
		for (int i = 0; i < testArray.length; i++)
		{
			increment(testArray[i]);
			System.out.print(testArray[i] + " ");
		}
	}
	
	public static void main(String[] args) 
	{
		APReviewMain.firstTestMethod();
	}

}
