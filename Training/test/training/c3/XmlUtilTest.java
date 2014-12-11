package training.c3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import training.c2.Staff;

public class XmlUtilTest {

	public static void main(String[] args) {
		List<Staff> staffs = new ArrayList<Staff>();
		
		staffs.add(new Staff(100, "Test"));
		staffs.add(new Staff(200, "Admin"));
		
		Company company = new Company();
		company.setStaffList(staffs);
		

		try {
			String xml = XmlUtil.objectToXml(company);
			System.out.println(xml);
			
			Company company2 = XmlUtil.xmlToObject(xml, Company.class);
			for(Staff staff : company2.getStaffList()) {
				System.out.println(staff.getId() + ":" + staff.getName());
			}			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
