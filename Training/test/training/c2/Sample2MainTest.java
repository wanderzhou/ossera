package training.c2;

import java.util.ArrayList;
import java.util.Random;



/**
 * 
 *  test result(ms)
 *  ergodic by get
	[10000             StaffList]Add time :       15, Get time :      110, Contains time :      842, List All time :        0, Remove time       16
	[10000     EnhancedStaffList]Add time :        0, Get time :      110, Contains time :       16, List All time :        0, Remove time        0
	[10000       HybridStaffList]Add time :       16, Get time :      655, Contains time :        0, List All time :        0, Remove time        0
	
	[100000            StaffList]Add time :       47, Get time :    34335, Contains time :   102602, List All time :       15, Remove time       16
	[100000    EnhancedStaffList]Add time :      125, Get time :    18518, Contains time :       31, List All time :       15, Remove time       16
	[100000      HybridStaffList]Add time :      141, Get time :    77236, Contains time :       16, List All time :       15, Remove time       32
	
	[100000            StaffList]Add time :       74, Get time :    37191, Contains time :   105971, List All time :        0, Remove time       15
	[100000    EnhancedStaffList]Add time :      109, Get time :    25100, Contains time :       47, List All time :        0, Remove time       31
	[100000      HybridStaffList]Add time :      127, Get time :    77844, Contains time :       15, List All time :       16, Remove time       16
	
	
	[200000            StaffList]Add time :      156, Get time :   148684, Contains time :   548902, List All time :       16, Remove time       47
	[200000    EnhancedStaffList]Add time :      235, Get time :    93148, Contains time :       62, List All time :       16, Remove time       47
	[200000      HybridStaffList]Add time :      235, Get time :   328334, Contains time :       62, List All time :       16, Remove time       46
	
	random access by get
	[100000            StaffList]Add time :       62, Get time :        0, Contains time :   101042, List All time :        0, Remove time       15
	[100000    EnhancedStaffList]Add time :      141, Get time :        0, Contains time :       32, List All time :        0, Remove time       31
	[100000      HybridStaffList]Add time :      109, Get time :       16, Contains time :       31, List All time :       15, Remove time       16
	
	[200000            StaffList]Add time :      140, Get time :       31, Contains time :   534440, List All time :       15, Remove time       32
	[200000    EnhancedStaffList]Add time :      251, Get time :       15, Contains time :       63, List All time :       15, Remove time       47
	[200000      HybridStaffList]Add time :      281, Get time :       31, Contains time :       47, List All time :       31, Remove time       47
	
	[100000            StaffList]Add time :       62, Get time :    38782, Contains time :   105893, List All time :        0, Remove time       31
	[100000    EnhancedStaffList]Add time :      126, Get time :    22090, Contains time :       15, List All time :       16, Remove time       31
	[100000      HybridStaffList]Add time :      110, Get time :    82088, Contains time :       31, List All time :       16, Remove time       15
	[100000      HybridList]Add time :   121753, Get time :       15, Contains time :    59733, List All time :       15, Remove time     2122	

 * 
 * add, get, listAll, remove
 * @author admin
 *
 */
public class Sample2MainTest {
	static ArrayList<Integer> rndList = new ArrayList<Integer>();

	public static void main(String[] args) {		
		int recordCount = 10000;
		initRnd(recordCount);
		
		long[] time = doIt1(recordCount);
		System.out.println(String.format("[%d %20s]Add time : %8d, Get time : %8d, Contains time : %8d, List All time : %8d, Remove time %8d", recordCount, "StaffList", time[0], time[1], time[2], time[3], time[4]));
		
		time = doIt2(recordCount);
		System.out.println(String.format("[%d %20s]Add time : %8d, Get time : %8d, Contains time : %8d, List All time : %8d, Remove time %8d", recordCount, "EnhancedStaffList", time[0], time[1], time[2], time[3], time[4]));	
		
		
		time = doIt3(recordCount);
		System.out.println(String.format("[%d %20s]Add time : %8d, Get time : %8d, Contains time : %8d, List All time : %8d, Remove time %8d", recordCount, "HybridStaffList", time[0], time[1], time[2], time[3], time[4]));

		time = doIt4(recordCount);
		System.out.println(String.format("[%d %20s]Add time : %8d, Get time : %8d, Contains time : %8d, List All time : %8d, Remove time %8d", recordCount, "HybridList", time[0], time[1], time[2], time[3], time[4]));
		
	}
	
	private static void initRnd(int recordCount) {
		Random random = new Random();
		for(int i = 0; i  < recordCount; i++) {
			rndList.add(random.nextInt(recordCount - 1));
		}
		
	}
	
	private static long[] doIt1(int recordCount) {
		//record start time
		long addStart, getStart, containStart, listStart, removeStart;
		//record end time
		long addEnd, getEnd, containEnd, listEnd, removeEnd;
		//record cost time
		long addTime, getTime, containTime, listTime, removeTime;
		
		StaffList staffList = new StaffList();
		
		
		addStart = System.currentTimeMillis();
		for(int i = 0; i < recordCount; i++) {
			Staff staff = new Staff(i, "name" + i);
			staffList.add(staff);
		}
		addEnd = System.currentTimeMillis();
		addTime = addEnd - addStart;
		
		getStart = System.currentTimeMillis();
		//Staff staff = staffList.get(index);
		/*for(int i = 0; i < recordCount; i++) {
			int rnd = (new Random(recordCount)).nextInt();
			staffList.get(rnd).getName();
		}*/		
		for(Integer i : rndList) {
			staffList.get(i);
		}
		getEnd = System.currentTimeMillis();
		getTime = getEnd - getStart;
		//System.out.println(String.format("staff name : %s, index : %d", staff.getName(), index));
		
		containStart = System.currentTimeMillis();
		int count = staffList.getSize();
		for(int i = 0; i < count; i++) {
			staffList.contains(new Staff(i, "name" + i));
		}
		containEnd = System.currentTimeMillis();
		
		containTime = containEnd - containStart;
		
		listStart = System.currentTimeMillis();
		Staff[] staffs = staffList.listAll();
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
	
	private static long[] doIt2(int recordCount) {
		//record start time
		long addStart, getStart, containStart, listStart, removeStart;
		//record end time
		long addEnd, getEnd, containEnd, listEnd, removeEnd;
		//record cost time
		long addTime, getTime, containTime, listTime, removeTime;
		
		EnhancedStaffList staffList = new EnhancedStaffList();
		
		
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
		for(Integer i : rndList) {
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
		Staff[] staffs = staffList.listAll();
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
	
	//test HybridStaffList
	private static long[] doIt3(int recordCount) {
		//record start time
		long addStart, getStart, containStart, listStart, removeStart;
		//record end time
		long addEnd, getEnd, containEnd, listEnd, removeEnd;
		//record cost time
		long addTime, getTime, containTime, listTime, removeTime;
		
		HybridStaffList staffList = new HybridStaffList();
		
		
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
		for(Integer i : rndList) {
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
		Staff[] staffs = staffList.listAll();
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
	
	//test HybridStaffList
	private static long[] doIt4(int recordCount) {
		//record start time
		long addStart, getStart, containStart, listStart, removeStart;
		//record end time
		long addEnd, getEnd, containEnd, listEnd, removeEnd;
		//record cost time
		long addTime, getTime, containTime, listTime, removeTime;
		
		HybridList<Staff> staffList = new HybridList<Staff>();
		
		
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
		for(Integer i : rndList) {
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
