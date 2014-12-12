package training.c3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import training.EnvConfiguration;

/**
 * 
 * socket 客户端，通过调用write(byte[] bytes)方法向服务端发送数据
 * 
 * @author admin
 *
 */
public class Client extends Thread {

	private Selector selector;
	
	private SocketChannel socketChannel;
		
	private List<ByteBuffer> pendingData = new ArrayList<ByteBuffer>();
	
	private boolean stop = false;
	
	public Client() throws Exception {
		initSelector();
	}
		
	
	private void initSelector() throws IOException {
		selector = Selector.open();
		
		socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
				
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		
		socketChannel.connect(new InetSocketAddress(EnvConfiguration.getInstance().getServerIp(), EnvConfiguration.getInstance().getServerPort()));
	}
	
	
	public void close() {
		if(socketChannel != null) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	public void run() {
		try {
			while(true) {
				selector.select();
				
				Set<SelectionKey> selectedKey = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKey.iterator();
				while(iterator.hasNext()) {
					SelectionKey key = iterator.next();
					
					if(!key.isValid()) {
						continue;
					}
					
					if(key.isConnectable()) {
						//System.out.println("client connectable");
						SocketChannel channel = (SocketChannel)key.channel();
						if(channel.isConnectionPending()) {
							channel.finishConnect();
						}
						channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
					} else if(key.isReadable()) {
						//ready for reading
						//System.out.println("client readable");
						//read(key);
						
					} else if(key.isWritable()) {
						//System.out.println("client writable");
						write(key);
					}
					
					iterator.remove();
				}
				
				if(this.isStop()) {
					close();
					break;
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	/*private void read(SelectionKey key) {
		
	}*/


	/**
	 * send data to server
	 * 
	 * @param key
	 * @throws IOException
	 */
	private void write(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		/*if(channel.isConnectionPending()) {
			channel.finishConnect();
		}*/
		synchronized(this.pendingData) {
			while(!pendingData.isEmpty()) {
				System.out.println("writing...");
				ByteBuffer buffer = pendingData.get(0);
				
				try {
					channel.write(buffer);
				} catch (IOException e) {
					e.printStackTrace();
					channel.close();
					key.cancel();
					pendingData.clear();					
				}
				pendingData.remove(0);
			}
			//key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
	}


	/**
	 * 
	 * receive data from "client", these data will be sent to server later when selection key is writable
	 * 
	 * @param bytes
	 * @throws IOException
	 */
	public void write(byte[] bytes) throws IOException {
				
		synchronized(this.pendingData) {
	
			ByteBuffer buff = ByteBuffer.wrap(bytes);
			
			pendingData.add(buff);
		}
	}


	public synchronized boolean isStop() {
		return stop;
	}


	public synchronized void setStop(boolean stop) {
		this.stop = stop;
	}
		

}
