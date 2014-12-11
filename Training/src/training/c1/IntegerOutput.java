package training.c1;

public class IntegerOutput extends NumberOutput {

	int value;
	
	public IntegerOutput(int value) {
		this.value = value;
		this.bytes = NumberConvertor.convertIntegerToBytes(value);
	}

	@Override
	void display() {
		System.out.println("type: " + Integer.TYPE);
		
		System.out.println("bytes:"); 
		for(int i = 0; i < bytes.length; i++) {
			System.out.println("["+ (i+1) + "]" + bytes[i]);
		}
		
		//System.out.println("binary:" + NumberConvertor.arrayToBinaryString(bytes));
		System.out.println("binary:" + Integer.toBinaryString(value));
		System.out.println("octal:" + Integer.toOctalString(value));
		//System.out.println("octal:" + NumberConvertor.toOctalString(value, 0xffffffff));
		System.out.println("decimal:" + Integer.toString(value));
		System.out.println("hex:" + Integer.toHexString(value));
		//System.out.println("hex:" + NumberConvertor.arrayToHexString(bytes));
	}

}
