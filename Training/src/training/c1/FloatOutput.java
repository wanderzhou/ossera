package training.c1;

public class FloatOutput extends NumberOutput {

	float value;
	
	public FloatOutput(float value) {
		this.value = value;
		this.bytes = NumberConvertor.convertFloatToBytes(value);
	}

	@Override
	void display() {
		System.out.println("type: " + Float.TYPE);
		
		System.out.println("bytes:");
		for(int i = 0; i < bytes.length; i++) {
			System.out.println("["+ (i+1) + "]" + bytes[i]);
		}		
		
		int floatToIntBits = Float.floatToIntBits(value);
		String bin = Integer.toBinaryString(floatToIntBits);		
		if(bin.length() < 32) {
			bin = "00000000000000000000000000000000".substring(0, 32 - bin.length()) + bin;
		}
		
		System.out.println("binary:" + bin);
		System.out.println("Octal:" +Integer.toOctalString(floatToIntBits));		
		System.out.println("decimal:" + Float.toString(value));
		System.out.println("hex:" + Integer.toHexString(floatToIntBits));
		
		String flag = bin.substring(0, 1);
		String exponent = bin.substring(1, 9);
		String mantissa = bin.substring(9);
		
		System.out.println(String.format("binary structure: flag[%s], exponent[%s], mantissa[%s]", flag, exponent, mantissa));

	}

}
