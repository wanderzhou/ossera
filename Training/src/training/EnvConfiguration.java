package training;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * 系统配置，从config.properties文件读取
 * 
 * @author admin
 *
 */
public class EnvConfiguration {
	
	private static final EnvConfiguration instance = new EnvConfiguration();

	Properties properties = new Properties();
	
	private String serverIp;
	private int serverPort;
	
	//服务器端存储位置
	private String serverPath;
	//客户端取文件位置
	private String clientPath;
	
	public static EnvConfiguration getInstance() {
		return instance;
	}
	
	private EnvConfiguration() {		
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
			
			this.serverIp = getProperty("server_ip");
			this.serverPort = Integer.parseInt(getProperty("server_port"));
			this.serverPath = getProperty("server_path");
			if(serverPath.endsWith(File.separator)) {
				serverPath = serverPath.substring(0, serverPath.length() - 1);
			}
			
			this.clientPath = getProperty("client_path");
			if(clientPath.endsWith(File.separator)) {
				clientPath = clientPath.substring(0, clientPath.length() - 1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getProperty(String key) {
		return (String) properties.get(key);
	}

	public String getServerIp() {
		return serverIp;
	}

	public int getServerPort() {
		return serverPort;
	}

	public String getServerPath() {
		return serverPath;
	}

	public String getClientPath() {
		return clientPath;
	}
	
	

}
