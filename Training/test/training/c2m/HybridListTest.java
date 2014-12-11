package training.c2m;

import training.c2m.HybridList;
import training.c2m.Staff;

public class HybridListTest {
 
	public static void main(String[] args) {
		System.out.println("basic test");
		//基本测试
		BasicList<Staff> staffList = new HybridList<Staff>();
		
		for(int i = 0; i < 10; i++) {
			Staff staff = new Staff(i, "name-" + i);
			staffList.add(staff);
		}
		
		System.out.println(staffList.size == 10);
		System.out.println(staffList.get(0).getId() == 0);
		System.out.println(staffList.get(9).getId() == 9);
		
		Staff staff = new Staff(5, "name-5");
		System.out.println(staffList.contains(staff));
		
		staffList.remove(staff);
		System.out.println(staffList.size == 9);
		
		int size = staffList.getSize();
		for(int i = 0; i < size; i++) {
			System.out.println(staffList.get(i).getName());
		}
		
		Object[] staffs = staffList.listAll();
		for(Object item : staffs) {
			System.out.println(((Staff)item).getName());
		}
		
		System.out.println(staffList.getSize());
		//基本测试结束
		
		//批量操作测试
		System.out.println("Performance test");
		
		int recordCount = 10000;
		long[] time = test(staffList, recordCount);
		System.out.println(String.format("[%d %20s]Add time : %8d, Get time : %8d, Contains time : %8d, List All time : %8d, Remove time %8d", recordCount, staffList.getClass().toString(), time[0], time[1], time[2], time[3], time[4]));

	}

	private static long[] test(BasicList<Staff> staffList, int recordCount) {	
		//record start time
		long addStart, getStart, containStart, listStart, removeStart;
		//record end time
		long addEnd, getEnd, containEnd, listEnd, removeEnd;
		//record cost time
		long addTime, getTime, containTime, listTime, removeTime;
		
		
		addStart = System.currentTimeMillis();
		for(int i = 0; i < recordCount; i++) {
			Staff staff = new Staff(i, "name" + i);
			staffList.add(staff);
		}
		addEnd = System.currentTimeMillis();
		addTime = addEnd - addStart;
		
		
		getStart = System.currentTimeMillis();
		/*for(int i = 0; i < recordCount; i++) {
			int rnd = (new Random(recordCount)).nextInt();
			staffList.get(rnd).getName();
		}*/
		for(int i = 0; i < recordCount; i++) {
			staffList.get(i);
		}
		getEnd = System.currentTimeMillis();
		getTime = getEnd - getStart;
		
		containStart = System.currentTimeMillis();
		int count = staffList.getSize();
		for(int i = 0; i < count; i++) {
			staffList.contains(new Staff(i, "name" + i));
		}
		containEnd = System.currentTimeMillis();
		
		containTime = containEnd - containStart;
		
		listStart = System.currentTimeMillis();
		staffList.listAll();
		listEnd = System.currentTimeMillis();
		listTime = listEnd - listStart;
		
		
		removeStart = System.currentTimeMillis();
		for(int j = 0; j < recordCount; j++) {
			staffList.remove(new Staff(j, "name" + j));
		}
		removeEnd = System.currentTimeMillis();
		removeTime = removeEnd - removeStart;
		
		return new long[] {addTime, getTime, containTime, listTime, removeTime};		
	}

}
