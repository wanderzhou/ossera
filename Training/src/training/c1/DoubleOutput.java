package training.c1;

public class DoubleOutput extends NumberOutput {
	double value;

	public DoubleOutput(double value) {
		this.value = value;
		this.bytes = NumberConvertor.convertDoubleToBytes(value);
	}

	@Override
	void display() {
		System.out.println("type:" + Double.TYPE);
		
		System.out.println("bytes:");
		for(int i = 0; i < bytes.length; i++) {
			System.out.println("["+ (i+1) + "]" + bytes[i]);
		}
		
		long doubleToLongBits = Double.doubleToLongBits(value);
		String bin = Long.toBinaryString(doubleToLongBits);		
		
		if(bin.length() < 64) {
			bin = "0000000000000000000000000000000000000000000000000000000000000000".substring(0, 64 - bin.length()) + bin;
		}
		
		System.out.println("binary:" + bin);
		System.out.println("octal:" + Long.toOctalString(doubleToLongBits));
		System.out.println("decimal:" + Double.toString(value));
		System.out.println("hex:" + Long.toHexString(doubleToLongBits));
		
		String flag = bin.substring(0, 1);
		String exponent = bin.substring(1, 12);
		String mantissa = bin.substring(13);
		
		System.out.println(String.format("binary structure: flag[%s], exponent[%s], mantissa[%s]", flag, exponent, mantissa));		
	}

}
