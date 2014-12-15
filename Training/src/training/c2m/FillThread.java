package training.c2m;

public class FillThread extends Thread {

	private int start;

	private int step;

	private int fillCount;

	private HybridList<Staff> hybridList;

	public FillThread(int start, int step, int fillCount,
			HybridList<Staff> hybridList) {
		this.start = start;
		this.step = step;
		this.fillCount = fillCount;
		this.hybridList = hybridList;
	}

	public void run() {
		int count = 0;
		for (int i = start; count < fillCount; i += step, count++) {
			Staff staff = new Staff(i, this.getName() + i);
			hybridList.add(staff);
		}
	}

}
