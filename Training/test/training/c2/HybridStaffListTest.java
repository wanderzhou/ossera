package training.c2;

public class HybridStaffListTest {

	public static void main(String[] args) {
		HybridStaffList staffList = new HybridStaffList();
		
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
		
		Staff[] staffs = staffList.listAll();
		for(Staff item : staffs) {
			System.out.println(item.getId());
		}
		
		System.out.println(staffList.getSize());

	}

}
