package com.amphora.familyproject.bean;

import java.util.List;

/**
 * This class will help to manage the small family. Class will
 * create a small family with hubby, wifey and children as a
 * member. Hubby and wifey would be the object of
 * {@linkplain Member} class and children would
 * be the object of List of {@linkplain Member} class.
 * 
 */
public class Partners {

	private Member firstPartner;
	private Member secondPartner;
	/**
	 * Data type of variable "kids" I took List because there can be
	 * many kids which is not not fixed and it would be easy to add
	 * into a List cause insertion time for list is constant.
	 */
	private List<Member> children;
	
	public Partners(Member firstLifePartner, Member secondLifePartner) {
		
		this.firstPartner = firstLifePartner;
		this.secondPartner = secondLifePartner;
	}
	public Member getFirstPartner() {
		return firstPartner;
	}
	public void setFirstPartner(Member firstLifePartner) {
		this.firstPartner = firstLifePartner;
	}
	public Member getSecondPartner() {
		return secondPartner;
	}
	public void setSecondPartner(Member secondLifePartner) {
		this.secondPartner = secondLifePartner;
	}
	public List<Member> getChildren() {
		return children;
	}
	public void setChildren(List<Member> children) {
		this.children = children;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstPartner == null) ? 0 : firstPartner.hashCode());
		result = prime * result + ((secondPartner == null) ? 0 : secondPartner.hashCode());
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
		Partners other = (Partners) obj;
		if (firstPartner == null) {
			if (other.firstPartner != null)
				return false;
		} else if (!firstPartner.equals(other.firstPartner))
			return false;
		if (secondPartner == null) {
			if (other.secondPartner != null)
				return false;
		} else if (!secondPartner.equals(other.secondPartner))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Partners [hubby=" + firstPartner + ", wifey=" + secondPartner + ", children=" + children + "]";
	}
}
