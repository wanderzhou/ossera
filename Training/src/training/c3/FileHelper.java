package training.c3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBException;

import training.EnvConfiguration;
import training.c2.Staff;

public class FileHelper {

	public static void storeFileContent(String body) throws IOException,
			FileNotFoundException, JAXBException {
		String fileName = "test-" + System.currentTimeMillis() + ".csv";
		// TODO: verify if path and file exists

		File file = new File(EnvConfiguration.getInstance().getServerPath()
				+ File.separator + fileName);

		if (file.createNewFile()) {
			PrintWriter printWriter = new PrintWriter(file);

			Company company = XmlUtil.xmlToObject(body, Company.class);
			List<Staff> staffs = company.getStaffList();
			LineConvertor lineConvertor = new StaffLineConvertor();
			int len = staffs.size();
			int c = 0;
			for (Staff staff : staffs) {
				String line = lineConvertor.objectToLine(staff);
				printWriter.print(line);
				c++;
				if (c < len) {
					printWriter.println();
				}
			}
			printWriter.close();
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
	public static List<Staff> readFromFile(File file) throws IOException {
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
	public static File listFileAndGetOneRandom(String path) throws Exception {
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
		
		return files[indexFile.get((new Random()).nextInt(fileCount))];
	}	
}
