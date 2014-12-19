package training.c5;

public class Zoo {

	private String name;
	
	private String city;
	
	private Animal[] animals;
	
	private int count = 0;
	
	private int Default_Size = 10;
	
	private int capacity;
	
	public Zoo(String name, String city) {
		this.name = name;
		this.city = city;
		animals = new Animal[Default_Size];
		this.capacity = Default_Size;
	}
	
	public Zoo(String name, String city, int capacity) {
		this.name = name;
		this.city = city;
		animals = new Animal[capacity];
		this.capacity = capacity;
	}

	public synchronized boolean add(Animal animal) throws Exception {
		if(count == capacity) {
			throw new Exception("full");
		}
		
		animals[count++] = animal;
		
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
}
