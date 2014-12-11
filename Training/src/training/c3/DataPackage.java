package training.c3;

import java.nio.charset.Charset;

import training.c1.Util;

/**
 * 
 * 
 * 发送的数据包格式，由包头'DATA' + 4字节的数据长度 + 数据
 * 
 * @author admin
 *
 */
public class DataPackage {
	
	public final static String packageHeader = "'DATA'";
	
	public final static int headerLen = 6;
	
	public final static int dataLengthLen = 4;
	
	public final static int fixedLength = headerLen + dataLengthLen;
	
	private int bodyLength;
	
	private byte[] bodyBytes;
	
	private String body;
	
	public DataPackage(byte[] bytes) {
		if(bytes.length <= (headerLen + dataLengthLen)) {
			//exception
		}
		
		byte[] headerBytes = new byte[headerLen];
		System.arraycopy(bytes, 0, headerBytes, 0, headerLen);
		
		String receiveHeader = new String(headerBytes);
		if(packageHeader.equals(receiveHeader)) {
			byte[] dataLenBytes = new byte[dataLengthLen];
			System.arraycopy(bytes, headerLen, dataLenBytes, 0, dataLengthLen);
			
			bodyLength = Util.ConvertBytesToInt(dataLenBytes);
			
			if(bytes.length != (headerLen + dataLengthLen + bodyLength)) {
				//exception
			} else {
				bodyBytes = new byte[bodyLength];
				System.arraycopy(bytes, headerLen + dataLengthLen, bodyBytes, 0, bodyLength);
				
				body = new String(bodyBytes, Charset.forName("UTF-8"));
			}
		} else {
			//exception
		}
	}
	
	public DataPackage(String body) {
		this.body = body;
		this.bodyBytes = body.getBytes(Charset.forName("UTF-8"));
		this.bodyLength = bodyBytes.length;
	}
	
	public byte[] toBytes() {
		byte[] bytes = new byte[headerLen + dataLengthLen + bodyLength];
		byte[] headerBytes = packageHeader.getBytes(Charset.forName("UTF-8"));
		byte[] dataLengthBytes = Util.ConvertIntToBytes(bodyLength);
		
		System.arraycopy(headerBytes, 0, bytes, 0, headerLen);
		System.arraycopy(dataLengthBytes, 0, bytes, headerLen, dataLengthLen);
		System.arraycopy(bodyBytes, 0, bytes, headerLen + dataLengthLen, bodyLength);
		
		return bytes;
	}

	public byte[] getBodyBytes() {
		return bodyBytes;
	}


	public String getBody() {
		return body;
	}

	public int getBodyLength() {
		return bodyLength;
	}

	
	

}
