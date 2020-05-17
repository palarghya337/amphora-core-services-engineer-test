package com.amphora.familyproject.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.bean.Member.Gender;
import com.amphora.familyproject.bean.Partners;
import com.amphora.familyproject.exception.InvalidDataException;

public class Family {

	private Partners familyHead;
	private List<Member> familyMembers;
	/**
	 * To create a family we need family head.
	 * @param head
	 */
	public Family(Partners head) {
		
		Objects.requireNonNull(head);
		Member first = head.getFirstPartner();
		Member second = head.getSecondPartner();
		boolean isFirstNull = Objects.isNull(first);
		boolean isSecondNull = Objects.isNull(second);
		if (isFirstNull	&& isSecondNull) {
			throw new InvalidDataException("There should be at least one member");
		}
		this.familyHead = head;
		familyMembers = new LinkedList<>();
		if (isFirstNull && !isSecondNull) {
			familyMembers.add(second);
		} else if (isSecondNull) {
			familyMembers.add(first);
		} else {
			familyMembers.add(first);
			familyMembers.add(second);
		}
	}
	/**
	 * Method to add a new member in the family. To add new member
	 * into the family tree you need to pass parents information
	 * or partners information (in case if you want to add
	 * spouse). You can add all these information inside the
	 * "member" variable.
	 * 
	 * @param member variable contain the new member details.
	 * @param inLawMember variable, when true, the method will
	 * find given spouse of new member and create a {@linkplain Partners}
	 * object and set proper value in that object. If variable is
	 * false then it will find the parent of the given member
	 * and set the child of the parent.
	 * @return {@linkplain Member} object contains the updated data of new member.
	 * If given parent or spouse (name, gender, age) does not
	 * matches with the available family members then returns null.
	 */
	public Member addMember(Member member, boolean inLawMember) {
		
		if (inLawMember) {
			return addSpouse(member);
		}
		Partners newMemberParents = member.getParents();
		if (Objects.isNull(newMemberParents)
				|| (Objects.isNull(newMemberParents.getFirstPartner())
						&& Objects.isNull(newMemberParents.getSecondPartner())) ) {
			return null;
		}
		Member newMember = addMember(member, familyHead.getFirstPartner());
		if (Objects.isNull(newMember)) {
			return addMember(member, familyHead.getSecondPartner());
		}
		return newMember;
	}
	/**
	 * Overloaded method of {@link #addMember(Member, boolean)}
	 * to add child member
	 * 
	 * @param newMember to add into the family.
	 * @param parent of the new member.
	 * @return {@linkplain Member} object contains the updated data
	 * of new member. If given parent(name, gender, age) does not
	 * matches with the available family members then returns null.
	 */
	private Member addMember(Member newMember, Member parent) {

		if (Objects.isNull(parent)) {
			return null;
		}
		Partners parents = parent.getLifePartners();
		Partners newMemberParents = newMember.getParents();
		if (newMemberParents.equals(parents)) {
			/*
			 * If current parent matches with the given parent
			 * inside the newMember object then we add newMember
			 * as a child of parent.
			 **/
			List<Member> children = parents.getChildren();
			if (Objects.isNull(children)) {
				/*
				 * Used linked list because it will insert data
				 * in O(1) time.
				 **/
				children = new LinkedList<>();
				parents.setChildren(children);
			}
			newMember.setParents(parents);
			children.add(newMember);
			familyMembers.add(newMember);
			return newMember;
		}
		/*
		 * If parents of a new member does not match then we try
		 * to find the child of current parent whether child
		 * is parent of new member or not.
		 * 
		 * Here we are using DFS to traverse the children.
		 * this method will call a proper method to add
		 * child or spouse. If parent of a child or partner
		 * of the spouse does not match then it will find
		 * the child of the parents or partner object and
		 * follow the same process again. If we found the
		 * proper location then add new member and return
		 * the updated member.
		 **/
		if (Objects.nonNull(parents) &&
				Objects.nonNull(parents.getChildren())) {
			for (Member child: parents.getChildren()) {
				
				Member partner = addMember(newMember, child);
				if (Objects.nonNull(partner)) {
					return partner;
				}
			}
		}
		return null;
	}
	/**
	 * Method to add spouse of a member.
	 * @param member
	 * @return {@linkplain Member} object contains the updated data
	 * of member. If given spouse(name, gender, age) in the member
	 * object does not matches with the available family members
	 * then returns null.
	 */
	private Member addSpouse(Member member) {
		/*
		 * If we need to add spouse of new member then we need to pass
		 * object of Partners. If partners object is null or both the
		 * member of partners object is null then it will return null.
		 **/
		if (Objects.isNull(member.getLifePartners())
				|| (Objects.isNull(member.getLifePartners().getFirstPartner())
				&& Objects.isNull(member.getLifePartners().getSecondPartner()))) {
			return null;
		}
		Partners lifePartnersOfNewMember = member.getLifePartners();
		Member spouseOfNewMember = null;
		if (Objects.isNull(lifePartnersOfNewMember.getFirstPartner())
				|| member.equals(lifePartnersOfNewMember.getFirstPartner())) {
			
			spouseOfNewMember = lifePartnersOfNewMember.getSecondPartner();
		} else {
			spouseOfNewMember = lifePartnersOfNewMember.getFirstPartner();
		}
		Member addedMember = addSpouse(member, familyHead.getFirstPartner(), spouseOfNewMember);
		if (addedMember == null) {
			return addSpouse(member, familyHead.getSecondPartner(), spouseOfNewMember);
		}
		return addedMember;
	}
	/**
	 * Overloaded method of {@link #addSpouse(Member)}
	 * @param newMember
	 * @param actualPartner
	 * @return {@linkplain Member}
	 */
	private Member addSpouse(Member newMember, Member actualPartner, Member expectedPartner) {
		
		if (Objects.nonNull(actualPartner) && Objects.nonNull(expectedPartner)) {
			if (expectedPartner.equals(actualPartner)) {

				Partners newPartner = null;
				if (Gender.Male.equals(newMember.getGender())) {
					newPartner = new Partners(newMember, actualPartner);
				} else {
					newPartner = new Partners(actualPartner, newMember);
				}
				actualPartner.setLifePartners(newPartner);
				newMember.setLifePartners(newPartner);
				familyMembers.add(newMember);
				/*
				 * We should not add this new member as a child
				 * of his/her partners parent because spouse is
				 * not considered as child. We could add spouse
				 * as a family member.
				 **/
				return newMember;
			} else {
				
				Partners lifePartners = actualPartner.getLifePartners();
				if (Objects.nonNull(lifePartners) &&
						Objects.nonNull(lifePartners.getChildren())) {
					for (Member child: lifePartners.getChildren()) {
						
						Member partner = addSpouse(newMember, child, expectedPartner);
						if (Objects.nonNull(partner)) {
							return partner;
						}
					}
				}
			}
		}
		return null;
	}
	
	public List<Member> getListOfAllMembers() {
		return familyMembers;
	}
	public List<Member> getSortedListOfMembers() {
		List<Member> allMembers = getListOfAllMembers();
		return allMembers.stream()
				.sorted((member1, member2) ->
				member2.getAge() - member1.getAge())
				.collect(Collectors.toList());
	}
	public Partners getFamilyHead() {
		return familyHead;
	}
}
