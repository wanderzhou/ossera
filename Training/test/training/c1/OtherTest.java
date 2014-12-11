package training.c1;

public class OtherTest {

	public static void main(String[] args) {

		Sample11Main.doIt("1000");
		
		System.out.println(NumberConvertor.toBinaryString((byte)1000));
		
		//
		Sample11Main.doIt("10.20");
		Sample11Main.doIt(String.valueOf((int)10.20));
		
		
		Sample11Main.doIt("400.20");
		Sample11Main.doIt("400");
		//取整数部分的第一位
		Sample11Main.doIt(String.valueOf((byte)400.20));	
		

	}

}
