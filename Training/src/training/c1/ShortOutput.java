package training.c1;

public class ShortOutput extends NumberOutput {

	short value;
	
	public ShortOutput(short value) {
		this.value = value;
		this.bytes = NumberConvertor.convertShortToBytes(value);
	}

	@Override
	void display() {
		System.out.println("type: " + Short.TYPE);
		
		System.out.println("bytes: "); 
		for(int i = 0; i < bytes.length; i++) {
			System.out.println("["+ (i+1) + "]" + bytes[i]);
		}
		
		System.out.println("binary:" + NumberConvertor.arrayToBinaryString(bytes));
		System.out.println("Octal:" + NumberConvertor.toOctalString(value, 0xffff));
		System.out.println("decimal:" + Integer.toString(value));
		System.out.println("hex:" + NumberConvertor.arrayToHexString(bytes));

	}

}
