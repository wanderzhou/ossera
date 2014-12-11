package training.c3;

public class ClientTest {

	public static void main(String[] args) {
		Client client = null;
		// Client client2 = null;
		try {
			client = new Client();
			client.start();

			// client2 = new Client();
			// client2.start();

			// String s = "welcome, this is test";
			// client.write(s.getBytes());
			// client2.write( (s + "1").getBytes());

			// s = "another file name";
			// client.write(s.getBytes());
			// client2.write( (s + "1").getBytes());

			DataPackage pack = new DataPackage(
					"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><company><staffs><staff><id>1</id><name>test</name></staff></staffs></company>");

			byte[] bytes = pack.toBytes();
			client.write(bytes);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (client != null) {
				client.close();
			}

		}
	}

}
