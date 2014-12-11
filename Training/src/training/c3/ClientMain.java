package training.c3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
		ClientMain clientMain = new ClientMain();
		
		try {
			Client client = new Client();
			client.start();
			
			//list files and select one file random
			File file = clientMain.listFileAndGetOneRandom(EnvConfiguration.getInstance().getClientPath());
			System.out.println("selected file: " + file.getName());
			
			//read from file and convert to object list
			List<Staff> staffs = clientMain.readFromFile(file);
			
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
	
	/**
	 * 
	 * read from file and convert to Staff list
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public List<Staff> readFromFile(File file) throws IOException {
		LineNumberReader lineReader = new LineNumberReader(new FileReader(file));
		List<Staff> staffs = new ArrayList<Staff>();
		String line;
		LineConvertor lineParser = new StaffLineConvertor();
		while((line = lineReader.readLine()) != null) {
			Staff staff = (Staff)lineParser.lineToObject(line);
			staffs.add(staff);
		}
		
		lineReader.close();
		return staffs;
	}
	
	/**
	 * 
	 * List files in specified path and get one random
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public File listFileAndGetOneRandom(String path) throws Exception {
		File file = new File(path);
		if(!file.exists()) {
			throw new Exception("path not exists: " + path);
		}
		
		File[] files = file.listFiles();
		HashMap<Integer, Integer> indexFile = new HashMap<Integer, Integer>();
		int index = 0;
		int fileCount = 0;
		if(files == null || files.length == 0) {
			throw new Exception("no files in the path: " + path);
		}
		
		System.out.println("list files: --------------------------------------");
		for(File f : files) {			
			if(f.isFile()) {
				indexFile.put(fileCount, index);
				fileCount++;
				System.out.println("\t" + f.getName());
			}
			index++;
		}
		System.out.println("list files end: ----------------------------------");
		System.out.println();
		
		int rnd = (new Random()).nextInt(fileCount);
		return files[indexFile.get(rnd)];
	}

}
