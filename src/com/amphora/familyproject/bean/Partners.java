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

	private Member hubby;
	private Member wifey;
	/**
	 * Data type of variable "kids" I took List because there can be
	 * many kids which is not not fixed and it would be easy to add
	 * into a List cause insertion time for list is constant.
	 */
	private List<Member> children;
	
	public Partners(Member hubby, Member wifey) {
		
		this.hubby = hubby;
		this.wifey = wifey;
	}
	public Member getHubby() {
		return hubby;
	}
	public void setHubby(Member hubby) {
		this.hubby = hubby;
	}
	public Member getWifey() {
		return wifey;
	}
	public void setWifey(Member wifey) {
		this.wifey = wifey;
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
		result = prime * result + ((hubby == null) ? 0 : hubby.hashCode());
		result = prime * result + ((wifey == null) ? 0 : wifey.hashCode());
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
		if (hubby == null) {
			if (other.hubby != null)
				return false;
		} else if (!hubby.equals(other.hubby))
			return false;
		if (wifey == null) {
			if (other.wifey != null)
				return false;
		} else if (!wifey.equals(other.wifey))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Partners [hubby=" + hubby + ", wifey=" + wifey + ", children=" + children + "]";
	}
}
