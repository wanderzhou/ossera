package training.c1;

public class LongOutput extends NumberOutput {

	long value;
	
	public LongOutput(long value) {
		this.value = value;
		this.bytes = NumberConvertor.convertLongToBytes(value);
	}

	@Override
	void display() {
		System.out.println("type:" + Long.TYPE);
		
		System.out.println("bytes:");
		for(int i = 0; i < bytes.length; i++) {
			System.out.println("["+ i+1 + "]" + bytes[i]);
		}		
		
		System.out.println("binary:" + Long.toBinaryString(value));
		System.out.println("Octal:" + Long.toOctalString(value));
		System.out.println("decimal:" + Long.toString(value));
		System.out.println("hex:" + Long.toHexString(value));
	}

}
