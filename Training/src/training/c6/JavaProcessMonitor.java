package training.c6;

public class JavaProcessMonitor {
	
	//要监控的进程名，如：training.c6.JavaProcessMonitor
	private String processName;
	
	//启动命令
	private String startCommand;
	
	//检测进程的时间间隔
	private int intervalInSeconds;
	
	public JavaProcessMonitor(String processName, String startCommand, int seconds) {
		this.processName = processName;
		this.startCommand = startCommand;
		this.intervalInSeconds = seconds;
	}
	
	public void monitor() {
		while(true) {
			try {
				if(!JpsTool.javaProcessExists(processName)) {
					//进程不存在，重新启动
					System.out.println("not exists, try to start...");
					Runtime.getRuntime().exec(startCommand);
				} else {
					System.out.println("alive...");
				};
				
				Thread.sleep(intervalInSeconds * 1000); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String mainClass = "training.c3.Server";
		//根据不同的平台，使用的命令有所不同
		String startCommand = "D:\\document\\testfolder\\deploy\\startServer.bat";
		JavaProcessMonitor jpm = new JavaProcessMonitor(mainClass, startCommand, 5);
		
		jpm.monitor();
	}

}
