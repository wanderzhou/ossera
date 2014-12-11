package training.c1;

public class ByteOutput extends NumberOutput {
	
	byte value;
	
	public ByteOutput(byte value) {
		this.value = value;
		this.bytes = new byte[]{value};
	}

	@Override
	void display() {
		System.out.println("type:" + Byte.TYPE);
		System.out.println("binary:" + NumberConvertor.toBinaryString(value));
		//System.out.println("Octal:" + NumberConvertor.toOctalString(value));
		System.out.println("Octal:" + NumberConvertor.toOctalString(value, 0xff));
		System.out.println("decimal:" + Byte.toString(value));
		System.out.println("hex:" + NumberConvertor.toHexString(value));	
	}

}
