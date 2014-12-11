package training.c3;

import java.nio.charset.Charset;

public class DataPackageTest {

	public static void main(String[] args) {
		DataPackage pack = new DataPackage("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><company><staffs><staff><id>1</id><name>test</name></staff></staffs></company>");
		
		byte[] bytes = pack.toBytes();
		
		int i = 0;
		for(byte b : bytes) {
			System.out.println("[" + i + "]" + b);
			i++;
		}
		
		byte[] bodyBytes = new byte[pack.getBodyLength()];
		
		System.arraycopy(bytes, DataPackage.headerLen + DataPackage.dataLengthLen, bodyBytes, 0, pack.getBodyLength());
		System.out.println(new String(bodyBytes, Charset.forName("UTF-8")));
		
		DataPackage pack2 = new DataPackage(bytes);
		System.out.println("body lengtg: " + pack2.getBodyLength());
		System.out.println("body: " + pack2.getBody());
	}

}
