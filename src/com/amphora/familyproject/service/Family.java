package com.amphora.familyproject.service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.bean.Member.Gender;
import com.amphora.familyproject.bean.Partners;
import com.amphora.familyproject.exception.InvalidDataException;

public class Family {

	private Partners head;
	public Family(Partners head) {
		
		Objects.requireNonNull(head);
		if (Objects.isNull(head.getHubby())
				&& Objects.isNull(head.getWifey())) {
			throw new InvalidDataException("There should be at least one member");
		}
		this.head = head;
	}
	/**
	 * Method to add a new member in the family. To add new member
	 * into the family tree you need to pass parents information
	 * or partners information (in case if you want to add only
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
				|| (Objects.isNull(newMemberParents.getHubby())
						&& Objects.isNull(newMemberParents.getWifey())) ) {
			return null;
		}
		Member newMember = addMember(member, head.getHubby());
		if (Objects.isNull(newMember)) {
			return addMember(member, head.getWifey());
		}
		return newMember;
	}
	/**
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
		Partners parents = parent.getPartner();
		Partners newMemberParents = newMember.getParents();
		if (newMemberParents.equals(parents)) {
			/*
			 * If current parent matches with the given parent
			 * inside the newMember object then we add newMember
			 * as a child of parent.
			 **/
			List<Member> children = parents.getChildren();
			if (Objects.isNull(children)) {
				children = new LinkedList<>();
				parents.setChildren(children);
			}
			newMember.setParents(parents);
			children.add(newMember);
			return newMember;
		}
		return findAndAddMember(newMember, parents, false);
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
		
		if (Objects.nonNull(member.getPartner())) {
			Member addedMember = addSpouse(member, head.getHubby());
			if (addedMember == null) {
				return addSpouse(member, head.getWifey());
			}
			return addedMember;
		}
		return null;
	}
	/**
	 * Overloaded method of {@link #addSpouse(Member)}
	 * @param member
	 * @param partner
	 * @return {@linkplain Member}
	 */
	private Member addSpouse(Member member, Member partner) {
		
		if (Objects.nonNull(partner)) {
			
			Partners partnerOfNewMember = member.getPartner();
			Member spouseOfNewMember = null;
			Partners newPartner = null;
			/*
			 * Based on gender we have to create partners
			 * object.
			 **/
			if (Gender.Male.equals(member.getGender())) {
				spouseOfNewMember = partnerOfNewMember.getWifey();
				newPartner = new Partners(member, partner);
			} else {
				spouseOfNewMember = partnerOfNewMember.getHubby();
				newPartner = new Partners(partner, member);
			}
			if (spouseOfNewMember.equals(partner)) {
				
				partner.setPartner(newPartner);
				member.setPartner(newPartner);
				/*
				 * We should not add this new member as a child
				 * of his/her partners parent because spouse is
				 * not considered as child. We could add spouse
				 * as a family member.
				 **/
				return member;
			} else {
				return findAndAddMember(member,
						partner.getPartner(), true);
			}
		}
		return null;
	}
	/**
	 * Method to find the parent of the new member.
	 * @param newMember
	 * @param parent
	 * @param inLawMember
	 * @return {@linkplain Member}
	 */
	private Member findAndAddMember(Member newMember,
			Partners parent, boolean inLawMember) {
		/*
		 * Here we are using DFS to traverse the children.
		 **/
		if (Objects.nonNull(parent) &&
				Objects.nonNull(parent.getChildren())) {
			for (Member child: parent.getChildren()) {
				
				Member partner = null;
				if (inLawMember) {
					partner = addSpouse(newMember, child);
				} else {
					partner = addMember(newMember, child);
				}
				if (Objects.nonNull(partner)) {
					return partner;
				}
			}
		}
		return null;
	}
	
	public void sort(Partners headMembers) {
		
		if (Objects.nonNull(headMembers)
				&& Objects.nonNull(headMembers.getChildren())) {
			
			Collections.sort(headMembers.getChildren(),
					(child1, child2) -> child2.getAge() - child1.getAge());
			for (Member child: headMembers.getChildren()) {
				sort(child.getPartner());
			}
		}
	}
	public Partners getFamilyHead() {
		return head;
	}
}
