package training.c3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import javax.xml.bind.JAXBException;

import training.EnvConfiguration;
import training.c1.Util;
import training.c2.Staff;

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
		String fileName = "test-" + System.currentTimeMillis() + ".csv";
		//TODO: verify if path and file exists
		
		File file = new File(EnvConfiguration.getInstance().getServerPath() + File.separator + fileName);
		//File file = new File(storePath + fileName);
		if(file.createNewFile()) {
			PrintWriter printWriter = new PrintWriter(file);
			
			Company company = XmlUtil.xmlToObject(body, Company.class);
			List<Staff> staffs = company.getStaffList();
			LineConvertor lineConvertor = new StaffLineConvertor();
			int len = staffs.size();
			int c = 0;
			for(Staff staff : staffs) {
				String line = lineConvertor.objectToLine(staff);
				printWriter.print(line);
				c++;
				if(c < len) {
					printWriter.println();
				}
			}
			printWriter.close();
		}
		
	}
	
	private void copyBytesToLeft(byte[] bytes, int offset, int length) {
		leftBytes = new byte[bytes.length];
		System.arraycopy(bytes, offset, leftBytes, 0, length);		
	}
	
}
