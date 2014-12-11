package training.c1;

import java.text.NumberFormat;
import java.util.Scanner;

/**
 * 
 * 以多种方式输出用户输入的一个值
 * 
 * @author admin
 *
 */
public class Sample11Main {

	public static void main(String[] args) {
	
		//waiting for user input
		System.out.println("Please input a number or a character: ");
		Scanner scan = new Scanner(System.in);
		String userInput = scan.next();
		scan.close();
		
		doIt(userInput);
		
	}


	public static void doIt(String userInput) {
		//System.out.println(userInput);
		//verify user input
		String numberType = null;
		if(!Util.isValidNumeric(userInput)) {
			if(userInput.length() == 1) {
				if(Character.isDefined(userInput.charAt(0))) {
					numberType = Character.TYPE.toString();
				}
			} else {
				//try double parse
				
			}
		} else {
			numberType = Util.getType(userInput);
		}
		
		if(numberType == null) {
			System.out.println("invalid input");
			System.exit(0);
		}
				
		//get corresponding type by value range
		
		NumberOutput numberOutput = Util.createNumberOutput(userInput, numberType);
		
		if(numberOutput == null) {
			System.out.println("undefined type");
		} else {		
			numberOutput.display();
		}
	}
	
	
	/**
	 * 
	 * @param minIntegerDigits
	 * @param maxIntegerDigits
	 * @param minFracDigits
	 * @param maxFracDigits
	 * @return
	 */
	public static NumberFormat format(int minIntegerDigits, int maxIntegerDigits, int minFracDigits, int maxFracDigits) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumIntegerDigits(minIntegerDigits);
		nf.setMaximumIntegerDigits(maxIntegerDigits);
		nf.setMinimumFractionDigits(minFracDigits);
		nf.setMaximumFractionDigits(maxFracDigits);
		
		return nf;
		
	}	


}
