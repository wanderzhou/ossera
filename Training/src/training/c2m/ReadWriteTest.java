package training.c2m;

/**
 * 
 * 首先定义一个HybridList，然后创建两个线程，一个填充，一个读取
 * 
 * @author admin
 *
 */
public class ReadWriteTest {

	public static void main(String[] args) {
		
		final HybridList<Staff> hybridList = new HybridList<Staff>();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 100; i++) {
					hybridList.add(new Staff(i, "name-" + i));
					
					try {
						Thread.sleep(320);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println("current size: " + hybridList.getSize());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		t2.setDaemon(true);
		
		
		t1.start();
		
		t2.start();
		
		try {
			//so t2 can read the final size
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
