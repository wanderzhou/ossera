package training.c31;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class UDPClient {

	public static void main(String[] args) {
		try {
			DatagramChannel channel = DatagramChannel.open();
			channel.configureBlocking(false);
			channel.socket().connect(new InetSocketAddress("127.0.0.1", 2066));
			
			byte[] bytes = {1,2,3,4,5,6};
			ByteBuffer buffer = ByteBuffer.wrap(bytes); 
			
			//channel.write(buffer);
			channel.send(buffer, new InetSocketAddress("127.0.0.1", 2066));
			
			//Thread.sleep(1000);

			ByteBuffer buffer2 = ByteBuffer.wrap(bytes); 
			channel.send(buffer2, new InetSocketAddress("127.0.0.1", 2066));
			
			//channel.send(buffer, new InetSocketAddress("127.0.0.1", 2066));
			
			ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
			channel.receive(receiveBuffer);
			System.out.println(receiveBuffer);
			
			receiveBuffer.clear();

			channel.receive(receiveBuffer);
			System.out.println(receiveBuffer);

			


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
