package training.c2m;

/**
 * 
 * 定义一个HybridList，由三个线程进行填充
 * 
 * @author admin
 *
 */
public class MultiThreadTest {

	private void fillList() {
		HybridList<Staff> hybridList = new HybridList<Staff>();
		
		Thread t1 = new FillThread(0, 1, 5, hybridList);
		
		Thread t2 = new FillThread(10, 2, 6, hybridList);
		
		Thread t3 = new FillThread(100, 3, 7, hybridList);
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			
			int index = 0;
			
			Staff staff = hybridList.get(index++);
			while(staff != null) {
				System.out.println(staff.toString());
				staff = hybridList.get(index++);
			}		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	public static void main(String[] args) {
		MultiThreadTest test = new MultiThreadTest();
		
		test.fillList();
	}

}
