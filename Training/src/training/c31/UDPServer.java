package training.c31;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class UDPServer {

	public static void main(String[] args) {

		try {
			Selector selector = Selector.open();
			DatagramChannel channel = DatagramChannel.open();
			channel.configureBlocking(false);
			channel.socket().bind(new InetSocketAddress("127.0.0.1", 2066));

			channel.register(selector, SelectionKey.OP_READ);

			while (true) {
				selector.select();

				Iterator<SelectionKey> iterator = selector.selectedKeys()
						.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();

					if (key.isConnectable()) {
						connect(key);
					} else if (key.isReadable()) {
						read(key);
						key.interestOps(SelectionKey.OP_WRITE);
					} else if (key.isWritable()) {
						write(key);
						key.interestOps(SelectionKey.OP_READ);
					}

					iterator.remove();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void write(SelectionKey key) {
		//System.out.println("invoking write");
		
		DatagramChannel channel = (DatagramChannel)key.channel();
		
		byte[] bytes = {1,2,3,4,5,6};
		ByteBuffer buffer = ByteBuffer.wrap(bytes); 
		
		try {
			if(key.attachment() != null) {
				System.out.print("response");
				channel.send(buffer, (SocketAddress)key.attachment());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void read(SelectionKey key) throws IOException {

		try {
			DatagramChannel channel = (DatagramChannel) key.channel();

			ByteBuffer dsts = ByteBuffer.allocate(1024);
			//must connected first if use read method
			//channel.read(dsts);
			SocketAddress socketAddress = (SocketAddress)key.attachment();
			socketAddress = channel.receive(dsts);
			if(socketAddress != null) {
				
				System.out.println(dsts);
				dsts.flip();
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}

	private static void connect(SelectionKey key) {
		System.out.println("invoking connect");

	}


}
