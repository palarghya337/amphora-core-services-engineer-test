package com.amphora.familyproject.bean;

import java.util.Arrays;
import java.util.List;

public class Member {

	private int age;
	private String name;
	private Gender gender;
	/**
	 * Data type of variable "parents" I took array because parents
	 * would be only two or fixed.
	 */
	private Member[] parents;
	/**
	 * Data type of variable "kids" I took List because there can be
	 * many kids which is not not fixed and it would be easy to add
	 * into a List cause insertion time for list is constant.
	 */
	private List<Member> kids;
	public enum Gender {
		Male, Female;
	}
	/**
	 * Exposing parameterized constructor because this are the mandatory
	 * information which we need to store the member information in a
	 * proper place.
	 * 
	 * @param name of the family member
	 * @param sex of the member
	 * @param age of the member
	 * @param parents of the member
	 */
	public Member(String name, Gender gender, int age, Member[] parents) {
		
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.parents = parents;
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

	public Member[] getParents() {
		return parents;
	}

	public void setParents(Member[] parents) {
		this.parents = parents;
	}

	public List<Member> getKids() {
		return kids;
	}

	public void setKids(List<Member> kids) {
		this.kids = kids;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((kids == null) ? 0 : kids.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Arrays.hashCode(parents);
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
		if (kids == null) {
			if (other.kids != null)
				return false;
		} else if (!kids.equals(other.kids))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (!Arrays.equals(parents, other.parents))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Member [age=" + age + ", name=" + name + ", gender=" + gender + ", parents=" + Arrays.toString(parents)
				+ ", kids=" + kids + "]";
	}
}
