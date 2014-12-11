package training.c3;

public class ReceiveHandlerTest {

	public static void main(String[] args) {
		ReceiveHandler handler = new ReceiveHandler();
		
		DataPackage pack = new DataPackage("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><company><staffs><staff><id>1</id><name>test</name></staff></staffs></company>");
		
		byte[] bytes = pack.toBytes();
		
		try {
			//first
			handler.handle(bytes);
			
			byte[] bytes1 = new byte[bytes.length/2];
			byte[] bytes2 = new byte[bytes.length - bytes.length/2];
			
			System.arraycopy(bytes, 0, bytes1, 0, bytes1.length);
			
			System.arraycopy(bytes, bytes1.length, bytes2, 0, bytes2.length);
			
			//second
			handler.handle(bytes1);
			handler.handle(bytes2);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
