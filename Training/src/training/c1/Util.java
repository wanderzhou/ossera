package training.c1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static byte[] ConvertIntToBytes(int value) {

		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16),
				(byte) (value >>> 8), (byte) value };
	}

	public static int ConvertBytesToInt(byte[] b) {
		return (b[0] << 24) + ((b[1] & 0xFF) << 16) + ((b[2] & 0xFF) << 8)
				+ (b[3] & 0xFF);
	}

	public static byte[] convertLongToBytes(long value) {
		byte[] arr = new byte[8];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (byte) (value & 0xff);
			value = value >>> 8;
		}
		return arr;
	}

	public static long ConvertBytesToLong(byte[] bytes) {
		long n = 0;
		for (int k = bytes.length - 1; k >= 0; k--) {
			n = (long) (n << 8);
			n = (long) (n | ((long) bytes[k] & 0xff));
		}
		return n;
	}


	public static byte[] ConvertIntToHex32Bytes(int n) {

		String hexString = Integer.toHexString(n);
		int len = hexString.length();
		
		for (int i = 0; i < 8 - len; i++) {
			hexString = "0" + hexString;
		}

		return hexString.toUpperCase().getBytes();

	}

	public static int ConvertHex32BytesToInt(byte[] bytes) {
		String hexString = new String(bytes);

		return Integer.parseInt(hexString, 16);
	}

	
	public static byte[] ConvertIntToHexBytes(int n, int bytesCount) {


		String hexString = Integer.toHexString(n);
		int len = hexString.length();
		
		for (int i = 0; i < bytesCount - len; i++) {
			hexString = "0" + hexString;
		}

		return hexString.toUpperCase().getBytes();		

	}	
	
	public static int convertHexBytesToInt(byte[] bytes) {
		String hexString = new String(bytes);
		return Integer.parseInt(hexString, 16);
	}


	public static String byte2hex(byte b) {
		StringBuffer buf = new StringBuffer();
		char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		int high = ((b & 0xf0) >> 4);
		int low = (b & 0x0f);
		buf.append(hexChars[high]);
		buf.append(hexChars[low]);
		return buf.toString();
	}

	public static String formatArrayToString(byte[] bytes) {
		String s = "";
		if (bytes != null) {
			s += "[" + bytes.length +"]";
			int i = 0;
			for (byte b : bytes) {
				s += Byte.toString(b);
				if(i++ != bytes.length - 1){
					s  += ",";
				}
			}
		}
		return s;
	}
	
	public static String fillString(String srcStr, int requiredLen, String strFill) {
		String result = srcStr;
		if(srcStr.length() < requiredLen) {
			int count = requiredLen - srcStr.length();
			for(int i = 0; i < count; i++) {
				result += strFill;
			}
		}
		return result;
	}
	
	public static boolean IsNaturalNumber(String strNumber) {
		Pattern notNaturalPattern = Pattern.compile("[^0-9]");
		Pattern naturalPattern = Pattern.compile("0*[0-9][0-9]*");
		Matcher m1 = notNaturalPattern.matcher(strNumber);
		Matcher m2  = naturalPattern.matcher(strNumber);
		
		return m2.matches() && !m1.matches();
	}
	
	
	public static boolean IsPositiveNumber(String strNumber) {
		Pattern notPositivePattern = Pattern.compile("[^0-9.]");
		Pattern positivePattern = Pattern.compile("^[.][0-9]+$|[0-9]*[.]*[0-9]+$");
		Pattern twodotPattern = Pattern.compile("[0-9]*[.][0-9]*[.][0-9]*");
		
		return !notPositivePattern.matcher(strNumber).matches() &&
			positivePattern.matcher(strNumber).matches() &&
			!twodotPattern.matcher(strNumber).matches();
	}	
	
	/**
	 * 
	 * valid : 1, 1.2, -34, a, 
	 * invalid : .1, 12., 
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValidNumeric(String s) {
		String str = s;
		if(str.startsWith("-")) {
			str = str.substring(1);
		}
		int dotCount = 0;
		
		if(str.startsWith(".") || str.endsWith(".")) {
			return false;
		}
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '.') {
				dotCount++;
			} else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		
		if(dotCount > 1) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * 根据用户的输入和输入的类型，创建NumberOutput子类
	 * 	
	 * @param userInput
	 * @param numberType
	 * @return
	 */
	public static NumberOutput createNumberOutput(String userInput,
			String numberType) {
		NumberOutput numberOutput = null;
		if(numberType.equals(Byte.TYPE.toString())) {
			numberOutput = new ByteOutput(Byte.parseByte(userInput));
		} else if(numberType.equals(Character.TYPE.toString())) {
			numberOutput = new CharacterOutput(userInput.charAt(0));
		} else if(numberType.equals(Short.TYPE.toString())) {
			numberOutput = new ShortOutput(Short.parseShort(userInput));
		} else if (numberType.equals(Integer.TYPE.toString())) {
			numberOutput = new IntegerOutput(Integer.parseInt(userInput));
		} else if (numberType.equals(Long.TYPE.toString())) {
			numberOutput = new LongOutput(Long.parseLong(userInput));
		} else if(numberType.equals(Float.TYPE.toString())) {
			numberOutput = new FloatOutput(Float.parseFloat(userInput));
		} else if(numberType.equals(Double.TYPE.toString())) {
			numberOutput = new DoubleOutput(Double.parseDouble(userInput));
		}
		return numberOutput;
	}	
	
	public static String getType(String s) {
		

		double d = Double.parseDouble(s); //Exception
		
		if(s.indexOf(".") >= 1) {
			//contain . , check float or double
			double absd = Math.abs(d);
			if(absd >= Float.MIN_VALUE && absd <= Float.MAX_VALUE) {
				return Float.TYPE.toString();
			} else if(absd >= Double.MIN_VALUE && absd <= Double.MAX_VALUE) {
				return Double.TYPE.toString();
			}
		}
		
		if(d >= Byte.MIN_VALUE && d <= Byte.MAX_VALUE) {
			return Byte.TYPE.toString();
		} else if(d >= Short.MIN_VALUE && d <= Short.MAX_VALUE) {
			return Short.TYPE.toString();
		} else if(d >= Integer.MIN_VALUE && d<= Integer.MAX_VALUE) {
			return Integer.TYPE.toString();
		} else if(d >= Long.MIN_VALUE && d <= Long.MAX_VALUE) {
			return Long.TYPE.toString();
		} 
		
		return Double.TYPE.toString();		
	}	

}
