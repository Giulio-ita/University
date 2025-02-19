package model;

import model.Customer;

public class Animal {
	private int id;
    private String name;
    private String species;
    private String breed;
    private String age;
    private String gender;
    //private String owner;
    private User owner;

    public Animal(int id, String name, String species, String breed, String age, String gender, User customer) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.owner = customer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
    
    public User getOwner() {
        return owner;
    }
    public void setOwner(Customer owner) {
        this.owner = owner;
    }
	public boolean hasChronicCondition() {

		return false;
	}
	public String getResponseToTreatment(String treatment) {
		return"Good Treatment";
	}
    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                //se non gestisci il null, Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException:
                ", owner=" + (owner != null ? owner.getUsername() : "No Owner") +
                
                '}';
    }
}
