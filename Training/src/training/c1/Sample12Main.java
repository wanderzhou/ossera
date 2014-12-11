package training.c1;

import java.util.Scanner;

/**
 * 
 * 用户输入一个值， 然后输入要截取这个值的二进制对应的起始位，把截取的二进制以八进制、十进制和十六进制的形式输出
 * 
 * @author admin
 *
 */
public class Sample12Main {

	public static void main(String[] args) {
		//get input
		Scanner scan = new Scanner(System.in);
		System.out.println("please input a number");		
		String userInput = scan.next();
		
		System.out.println("please input start position of bits(start from 1)");
		int startIndex = scan.nextInt(); //start from 1
		System.out.println("please input end position of bits(include)");
		int endIndex = scan.nextInt(); //include
		scan.close();
		
		System.out.println(String.format("User input %s, %d, %d", userInput, startIndex, endIndex));	
		
		//verify input
		if(startIndex < 1 || endIndex < 1 || startIndex > endIndex) {
			System.out.println("Invalid start position or end position, please check.");
			System.exit(0);
		}
		
		doIt(userInput, startIndex, endIndex);				
		
	}

	public static void doIt(String userInput, int startIndex, int endIndex) {
		//get type
		String numberType = null;
		if(!Util.isValidNumeric(userInput)) {
			if(userInput.length() == 1) {
				if(Character.isDefined(userInput.charAt(0))) {
					numberType = Character.TYPE.toString();
				}
			}			
		} else {
			numberType = Util.getType(userInput);
		}
		
		if(numberType == null) {
			System.out.println("invalid input");
			System.exit(0);
		}
		
		System.out.println("input type:" + numberType);
		NumberOutput numberOutput = Util.createNumberOutput(userInput, numberType);
		
		if(numberOutput == null) {
			System.out.println("undefined type");
		} else {		
			String bin = numberOutput.toBinaryString();
			System.out.println(bin);
			if(startIndex > bin.length() || endIndex > bin.length()) {
				System.out.println("Invalid range of start position or end position, please check.");
				System.exit(0);				
			}
			
			//String subBin = bin.substring(startIndex - 1, endIndex); from left
			
			//should from low bit(right side)
			int posStart = bin.length() - endIndex;
			int posEnd = bin.length() - startIndex;
			String subBin = bin.substring(posStart, posEnd + 1);

			long l = Long.parseLong(subBin, 2);
			
			System.out.println("binary:" + subBin);
			System.out.println("Ocatal:" + Long.toOctalString(l));
			System.out.println("Decimal:" + l);
			System.out.println("hex:" + Long.toHexString(l));
		}
	}

}
