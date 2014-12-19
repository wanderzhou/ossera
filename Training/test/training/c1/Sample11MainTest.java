package training.c1;

import java.math.BigDecimal;

public class Sample11MainTest {

	public static void main(String[] args) {
		//byte
		Sample11Main.doIt("1");
		System.out.println();
		
		Sample11Main.doIt("-1");
		System.out.println();
		
		Sample11Main.doIt("-128");
		System.out.println();
		
		Sample11Main.doIt("127");
		System.out.println();
		
		//short
		Sample11Main.doIt("128");
		System.out.println();
		
		Sample11Main.doIt("-129");
		System.out.println();
		
		Sample11Main.doIt("-32768");
		System.out.println();
		
		Sample11Main.doIt("32767");
		System.out.println();
		
		//int
		Sample11Main.doIt("-32769");
		System.out.println();
		
		Sample11Main.doIt("32768");
		System.out.println();
		
		Sample11Main.doIt(String.valueOf(Integer.MIN_VALUE));
		System.out.println();
		
		Sample11Main.doIt(String.valueOf(Integer.MAX_VALUE));
		System.out.println();
		
		//long
		Sample11Main.doIt(String.valueOf((long)Integer.MIN_VALUE - 1));
		System.out.println();
		
		Sample11Main.doIt(String.valueOf((long)Integer.MAX_VALUE + 1));
		System.out.println();	
		
		Sample11Main.doIt(String.valueOf(Long.MIN_VALUE));
		System.out.println();
		
		Sample11Main.doIt(String.valueOf(Long.MAX_VALUE));
		System.out.println();
		
		//float
		Sample11Main.doIt(String.valueOf("12.356"));
		System.out.println();
		
		Sample11Main.doIt(String.valueOf("-234309.33"));
		System.out.println();	
		

		Sample11Main.doIt(BigDecimal.valueOf(Float.MIN_VALUE).toPlainString());
		System.out.println();

		
		Sample11Main.doIt(BigDecimal.valueOf(Float.MAX_VALUE).toPlainString());
		System.out.println();	
		
		Sample11Main.doIt(BigDecimal.valueOf(Float.MIN_VALUE + 1.0).toPlainString());
		System.out.println();
		
		Sample11Main.doIt(BigDecimal.valueOf(Float.MAX_VALUE - 1.0).toPlainString());
		System.out.println();
		
		
	}

}
