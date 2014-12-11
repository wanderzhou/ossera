package training.c3;

import training.c2.Staff;

/**
 * 
 * Staff与cvs格式数据行内容之间的互转
 * 
 * @author admin
 *
 */
public class StaffLineConvertor implements LineConvertor {

	public static final String Seperator = ",";
	
	@Override
	public Object lineToObject(String line) {
		String[] items = line.split(",");
		Staff staff = new Staff();
		staff.setId(Integer.parseInt(items[0]));
		staff.setName(items[1]);
		return staff;
	}

	@Override
	public String objectToLine(Object object) {
		Staff staff = (Staff)object;
		
		String line = staff.getId() + Seperator + staff.getName();
		
		return line;
	}

}
