package com.amphora.familyproject.bean;

public class Member {

	private int age;
	private String name;
	private Gender gender;
	private Partners lifePartners;
	private Partners parents;
	public enum Gender {
		Male, Female, Other;
	}
	/**
	 * Exposing parameterized constructor because this are the mandatory
	 * information which we need to store the member information in a
	 * proper place.
	 * 
	 * @param name of the family member
	 * @param gender of the member
	 * @param age of the member
	 */
	public Member(String name, Gender gender, int age) {
		
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Partners getLifePartners() {
		return lifePartners;
	}
	public void setLifePartners(Partners lifePartners) {
		this.lifePartners = lifePartners;
	}
	public Partners getParents() {
		return parents;
	}
	public void setParents(Partners parents) {
		this.parents = parents;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (age != other.age)
			return false;
		if (gender != other.gender)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Member [age=" + age + ", name=" + name + ", gender=" + gender + ", partner=" + lifePartners + ", parents="
				+ parents + "]";
	}
}
