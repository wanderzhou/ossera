package training.c1;

public abstract class NumberOutput {
	
	byte[] bytes;
	
	abstract void display();

	public byte[] getBytes() {
		return bytes;
	}
	
	public String toBinaryString() {
		if(bytes == null || bytes.length == 0) {
			return null;
		}
		
		return NumberConvertor.arrayToBinaryString(bytes);
	}
	
}
