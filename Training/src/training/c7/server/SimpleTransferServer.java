package training.c7.server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import training.c3.FileHelper;
import training.c7.SimpleTransfer;
import training.c7.SimpleTransferHelper;
import training.c7.SimpleTransferPOA;

/**
 * 
 * run arguments : -ORBInitialPort 1050 -ORBInitialHost localhost
 * 
 * @author admin
 *
 */
class SimpleTransferImpl extends SimpleTransferPOA {

	/**
	 * 接收xml格式的内容，解析后保存到文件
	 */
	@Override
	public boolean putContent(String fileName, String content) {
		
		try {
			System.out.println("received file :" + fileName);
			FileHelper.storeFileContent(content);	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}

public class SimpleTransferServer {
	
	public static void main(String[] args) {
		try {
			//creating and initializing an ORB object
			ORB orb = ORB.init(args, null);
			
			//get a reference to the ROOT POA
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			
			//instantiating the servant object
			SimpleTransferImpl simpleTransferImpl = new SimpleTransferImpl();
			//get object reference from servant 
			org.omg.CORBA.Object objRef = rootPOA.servant_to_reference(simpleTransferImpl);
			
			SimpleTransfer simpleTransferRef = SimpleTransferHelper.narrow(objRef);
						
			//get the root naming context
			org.omg.CORBA.Object namingRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(namingRef);
			
			//binding the object reference in naming
			String name = "SimpleTransfer";
			NameComponent[] path = ncRef.to_name(name);
			ncRef.bind(path, simpleTransferRef);
			
			System.out.println("SimpleTransferServer ready");
			orb.run();	
			
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
