package training.c2m;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 
 * 员工 
 * 
 * @author admin
 *
 */
public class Staff extends BasicObject {
	
	private String name;

	public Staff(int id, String name) {
		super(id);
		this.name = name;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * id as hash code
	 */
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Staff)obj).id && this.name.equals(((Staff)obj).name);
	}

}
