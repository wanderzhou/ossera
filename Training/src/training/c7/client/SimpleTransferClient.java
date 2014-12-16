package training.c7.client;

import java.io.File;
import java.util.List;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import training.EnvConfiguration;
import training.c2.Staff;
import training.c3.Company;
import training.c3.FileHelper;
import training.c3.XmlUtil;
import training.c7.SimpleTransfer;
import training.c7.SimpleTransferHelper;

/**
 * 
 * 从指定的目录下随机选择一个文件，读取内容后，以xml格式发送到服务端
 * 
 * run arguments : -ORBInitialPort 1050 -ORBInitialHost localhost
 * 
 * @author admin
 *
 */
public class SimpleTransferClient {
	
	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "SimpleTransfer";
			SimpleTransfer simpleTransferImpl = SimpleTransferHelper.narrow(ncRef.resolve_str(name));
										
			//list files and select one file random
			File file = FileHelper.listFileAndGetOneRandom(EnvConfiguration.getInstance().getClientPath());
			System.out.println("selected file: " + file.getName());
				
			//read from file and convert to object list
			List<Staff> staffs = FileHelper.readFromFile(file);
				
			//convert object to xml
			Company company = new Company();
			company.setStaffList(staffs);
				
			String xml = XmlUtil.objectToXml(company);
				
			boolean result = simpleTransferImpl.putContent(file.getName(), xml);
			System.out.print("transfer file result : " + (result ? "success" : "fail"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
