package training.c3;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import training.c2.Staff;

/**
 * 
 * 传输数据对应的对象，只包括员工列表
 * 
 * @author admin
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement
public class Company {
	
	public Company() {}
	
	@XmlElementWrapper(name = "staffs")
	@XmlElement(name="staff")	
	private List<Staff> staffList;

	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

}
