package training.c1;

public class Sample12MainTest {

	public static void main(String[] args) {
		
		Sample12Main.doIt("1", 2, 6);
		System.out.println();
		
		Sample12Main.doIt("1", 2, 8);
		System.out.println();
		//Sample12Main.doIt("1", 2, 9);
		
		Sample12Main.doIt("-128", 1, 6);
		System.out.println();
		
		Sample12Main.doIt("127", 6, 6);
		System.out.println();
				

		Sample12Main.doIt("12734545", 6, 30);
		System.out.println();

		Sample12Main.doIt("12734545.56", 6, 30);
		System.out.println();
		
	}

}
