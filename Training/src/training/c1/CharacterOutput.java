package training.c1;

public class CharacterOutput extends NumberOutput {
	
	char value;

	public CharacterOutput(char value) {
		this.value = value;
		this.bytes = NumberConvertor.convertCharacterToBytes(value);
	}

	@Override
	void display() {
		System.out.println("type:" + Character.TYPE);
		
		System.out.println("bytes:"); 
		for(int i = 0; i < bytes.length; i++) {
			System.out.println("["+ (i+1) + "]" + bytes[i]);
		}		
		
		System.out.println("binary:" + NumberConvertor.arrayToBinaryString(bytes));
		System.out.println("octal:" + NumberConvertor.toOctalString(value, 0xffff));		
		System.out.println("decimal:" + (int)value);
		System.out.println("hex:" + NumberConvertor.arrayToHexString(bytes));
	}

}
