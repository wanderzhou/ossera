package training.c3;

import java.io.File;
import java.util.List;

import training.EnvConfiguration;
import training.c2.Staff;

/**
 * 
 * 客户端测试程序
 * 1.列出指定目录下的所有文件
 * 2.随机选择一个文件
 * 3.文件格式为csv，以xml格式发送到服务端
 * 
 * @author admin
 *
 */
public class ClientMain {
	
	public static void main(String[] args) {
	
		try {
			Client client = new Client();
			client.start();
			
			//list files and select one file random
			File file = FileHelper.listFileAndGetOneRandom(EnvConfiguration.getInstance().getClientPath());
			System.out.println("selected file: " + file.getName());
			
			//read from file and convert to object list
			List<Staff> staffs = FileHelper.readFromFile(file);
			
			//convert object to xml
			Company company = new Company();
			company.setStaffList(staffs);
			
			String xml = XmlUtil.objectToXml(company);
			
			//package data to be sent
			DataPackage dataPackage = new DataPackage(xml);						
			
			System.out.println("start send data to server ...");
			//send data to server
			client.write(dataPackage.toBytes());
			
			System.out.println("end ...");		
			
			client.setStop(true);			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
