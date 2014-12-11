package training.c2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 员工 
 * 
 * @author admin
 *
 */
public class Staff {
	
	private int id;
	
	private String name;
	
	public Staff() {}

	public Staff(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
