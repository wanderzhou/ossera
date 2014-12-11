package training.c1;

import java.nio.ByteBuffer;

public class NumberConvertor {
    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' ,
        '6' , '7' , '8' , '9' , 'a' , 'b' ,
        'c' , 'd' , 'e' , 'f'  
    };
    
	public static byte[] convertCharacterToBytes(char value) {
		return new byte[] {
				(byte)(value >>> 8),
				(byte)value
				
		};
	}	
	
	/*
	 * 
	 */
	public static byte[] convertIntegerToBytes(int value) {
		return new byte[] {
				(byte)(value >>> 24),
				(byte)(value >>> 16),
				(byte)(value >>> 8),
				(byte)value				
		};
	}
	
	public static String toBinaryString(byte value) {
		char[] c = new char[]{'0','0','0','0','0','0','0','0'};
		int charPos = 8;
		
		do {
			c[--charPos] = (digits[(value & 1)]);
			value >>= 1;
		} while(charPos > 0);
		
		return new String(c);
	}
	
	public static String toOctalString(byte value) {
		char[] c = new char[]{'0','0','0'};
		int charPos = 3;
		
		do {
			c[--charPos] = (digits[(value & 7)]);
			value = (byte)((value & 0xff) >>> 3);
		} while(value > 0); 
		
		return new String(c);
	}	
	
	public static String toOctalString(int value, int mask) {
		char[] c = new char[32];
		int charPos = 32;
		
		do {
			c[--charPos] = (digits[(value & 7)]);
			value = (value & mask) >>> 3;
		} while(value != 0); 
		
		return new String(c, charPos, 32 - charPos);
	}		
	
	public static String toHexString(byte value) {
		char[] c = new char[]{'0','0'};
		int charPos = 2;
		
		do {
			c[--charPos] = (digits[(value & 15)]);
			value >>= 4;
		} while(charPos > 0);
		
		return new String(c);
	}	
	
	public static String arrayToBinaryString(byte[] arr) {
		String s = "";
		for(byte b : arr) {
			s += toBinaryString(b);
		}
		
		return s;
	}
	
	public static String arrayToHexString(byte[] arr) {
		String s = "";
		for(byte b : arr) {
			s += toHexString(b);
		}
		
		return s;
	}	
	
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] convertLongToBytes(long value) {
		return ByteBuffer.allocate(8).putLong(value).array();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] convertFloatToBytes(float value) {
		return ByteBuffer.allocate(4).putFloat(value).array();
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] convertDoubleToBytes(double value) {
		return ByteBuffer.allocate(8).putDouble(value).array();
	}
	
	
	public static byte[] convertShortToBytes(short value) {
		return new byte[] { (byte) (value >>> 8), (byte) value };
	}	
		

}
