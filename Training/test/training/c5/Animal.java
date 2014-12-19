package training.c5;

public class Animal {

	private String name;
	
	private String gender;
	
	private String classification;
	
	private int weight;
	
	public Animal(String name, String gender, String classfication, int weight) {
		this.name = name;
		this.gender = gender;
		this.classification = classfication;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
}
