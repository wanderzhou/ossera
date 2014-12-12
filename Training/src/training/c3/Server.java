package training.c3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import training.EnvConfiguration;

/**
 * 
 * Socket Server
 * 
 * @author admin
 *
 */
public class Server {
	
	private Selector selector;
	private ServerSocketChannel channel;
	
	//ByteBuffer readBuffer = ByteBuffer.allocate(10); 
	
	//handle corresponding received data
	private Map<SocketChannel, ReceiveHandler> receiveHandlers = Collections
			.synchronizedMap(new HashMap<SocketChannel, ReceiveHandler>());	
	
	public Server() throws IOException {
		this.selector = initSelector();
	}
	
	private Selector initSelector() throws IOException {
		Selector selector = Selector.open();
		
		channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		channel.socket().bind(new InetSocketAddress(EnvConfiguration.getInstance().getServerIp(), EnvConfiguration.getInstance().getServerPort()));
		
		channel.register(selector, SelectionKey.OP_ACCEPT);
		
		return selector;
	}
	
	void run() {
		while(true) {
			try {
				//wait for event
				System.out.println("wait for event...");
				this.selector.select();
				
				
				Set<SelectionKey> selectedKey = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKey.iterator();
				
				while(iterator.hasNext()) {
					SelectionKey selectionKey = iterator.next();
					if(!selectionKey.isValid()) {
						continue;
					}
					
					if(selectionKey.isAcceptable()) {
						//accepted a connection
						this.accept(selectionKey);					
					} else if(selectionKey.isConnectable()) {
						//connection established with remote server
						this.connection(selectionKey);
					} else if(selectionKey.isReadable()) {
						//ready for reading
						this.read(selectionKey);
						
					} else if(selectionKey.isWritable()) {
						//ready for writing
						this.write(selectionKey);
						
					}
					iterator.remove();
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void write(SelectionKey selectionKey) {
		System.out.println("write...");
		
	}

	private void read(SelectionKey selectionKey) throws IOException {
		System.out.println("read..." + selectionKey.hashCode());
		
		SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
		ByteBuffer readBuffer = ByteBuffer.allocate(4096); 
		
		int readBytesCount = 0;
		try {
			readBytesCount = socketChannel.read(readBuffer);
			System.out.println(readBytesCount);
		} catch (IOException e) {
			socketChannel.close();
			selectionKey.cancel();
			e.printStackTrace();
		}		
		
		if(readBytesCount > 0) {		
			//
			ReceiveHandler receiveHandler = receiveHandlers.get(socketChannel);
			if(receiveHandler == null) {
				receiveHandler = new ReceiveHandler();
				receiveHandlers.put(socketChannel, receiveHandler);				
			}
			
			//
			byte[] receiveBytes = new byte[readBytesCount];
			byte[] buffer = readBuffer.array();
			System.arraycopy(buffer, 0, receiveBytes, 0, readBytesCount);
			//readBuffer.get(receiveBytes, 0, readBytesCount);
			try {
				receiveHandler.handle(receiveBytes);
			} catch (Exception e) {
				//log
				e.printStackTrace();
			}
		}
		
		if(readBytesCount == -1) {
			//remote closed
			socketChannel.close();
			selectionKey.cancel();
		} 		
		
	}

	private void connection(SelectionKey selectionKey) {
		System.out.println("connection...");		
	}

	private void accept(SelectionKey selectionKey) throws IOException {
		System.out.println("accept...");
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);
		socketChannel.register(this.selector, SelectionKey.OP_READ);
		
		
	}

	public static void main(String[] args) {
		
		try {
			Server server = new Server();
			server.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
