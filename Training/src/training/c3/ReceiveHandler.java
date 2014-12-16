package training.c3;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBException;

import training.c1.Util;

/**
 * 
 * 处理服务器端接收的数据
 * 
 * @author admin
 *
 */
public class ReceiveHandler {

	//不完整包，未解析部分的字节
	private byte[] leftBytes;
	
	public synchronized void handle(byte[] receiveBytes) throws Exception {
		
		byte[] bytes = null;
		
		if(leftBytes == null) {
			bytes = new byte[receiveBytes.length];
			System.arraycopy(receiveBytes, 0, bytes, 0, receiveBytes.length);
		} else {
			bytes = new byte[leftBytes.length + receiveBytes.length];
			System.arraycopy(leftBytes, 0, bytes, 0, leftBytes.length);
			System.arraycopy(receiveBytes, 0, bytes, leftBytes.length, receiveBytes.length);									
			leftBytes = null;			
		}
		
		if(bytes.length < DataPackage.fixedLength) {
			copyBytesToLeft(bytes, 0, bytes.length);
		} else {
			//每次分析的开始位置
			int packageStartIndex = 0; 
			while(packageStartIndex < bytes.length) {
				byte[] headerBytes = new byte[DataPackage.headerLen];
				System.arraycopy(bytes, packageStartIndex, headerBytes, 0, DataPackage.headerLen);
				
				String receiveHeader = new String(headerBytes);
				//包头是否合法
				if(DataPackage.packageHeader.equals(receiveHeader)) {
					byte[] dataLenBytes = new byte[DataPackage.dataLengthLen];
					System.arraycopy(bytes, packageStartIndex + DataPackage.headerLen, dataLenBytes, 0, DataPackage.dataLengthLen);
					
					int bodyLength = Util.ConvertBytesToInt(dataLenBytes);
					
					if(bytes.length - packageStartIndex >= (DataPackage.headerLen + DataPackage.dataLengthLen + bodyLength)) {
						//至少一个完整数据包
						byte[] bodyBytes = new byte[bodyLength];
						System.arraycopy(bytes, packageStartIndex + DataPackage.headerLen + DataPackage.dataLengthLen, bodyBytes, 0, bodyLength);
												
						try {
							//处理接收的数据（完整的数据包）
							dispatchData(bodyBytes);
						} catch (JAXBException e) {
							e.printStackTrace();
							throw new Exception(e);
						} catch (IOException e) {
							e.printStackTrace();
							throw new Exception(e);
						}
						
						//change start index
						packageStartIndex = packageStartIndex + DataPackage.headerLen + DataPackage.dataLengthLen + bodyLength;
					} else {
						copyBytesToLeft(bytes, packageStartIndex, bytes.length - packageStartIndex);
						break;
						//change start index
						//packageStartIndex = bytes.length - packageStartIndex;
					}						
				} else {
					//非法数据包头部，删除
					leftBytes = null;
					break;
				}
			}			
			
		}		
	}
	
	private void dispatchData(byte[] bytes) throws JAXBException, IOException {
		
		String body = new String(bytes, Charset.forName("UTF-8"));	
		System.out.println(body);
		FileHelper.storeFileContent(body);
		
	}


	
	private void copyBytesToLeft(byte[] bytes, int offset, int length) {
		leftBytes = new byte[bytes.length];
		System.arraycopy(bytes, offset, leftBytes, 0, length);		
	}
	
}
