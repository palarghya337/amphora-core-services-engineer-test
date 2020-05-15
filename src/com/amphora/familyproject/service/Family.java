package com.amphora.familyproject.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.exception.InvalidDataException;

public class Family {

	private Member head;
	public Family(Member head) {
		this.head = copyMember(head);
	}
	/**
	 * Method to insert a new member into family
	 * 
	 * @param member to add into a family.
	 * @return List<Member> of found parents
	 * @throws If parent of new member is not given or given
	 * more than two parents then throws InvalidDataException
	 */
	public List<Member> insert(Member member) {
		
		if (head == null) {
			head = copyMember(member);
			return null;
		} else {
			if (linkSpouse(member, head)) return null;
			/*
			 * If parent of new member is not given or given
			 * more than two parents then throws InvalidDataException
			 **/
			Member[] parents = member.getParents();
			if (parents == null || parents.length == 0 || parents.length > 2) {
				throw new InvalidDataException("Invalid parents data.");
			}
			return searchAndInsert(member, head);
		}
	}
	private Member copyMember(Member member) {
		
		Member copiedMember = new Member(member.getName(),
				member.getGender(), member.getAge(),
				member.getParents());
		copiedMember.setSpouse(member.getSpouse());
		copiedMember.setKids(member.getKids());
		copiedMember.getSpouse().setSpouse(copiedMember);
		return copiedMember;
	}
	private boolean linkSpouse(Member newMember, Member spouceOfNewMember) {
		
		boolean isSpouseAdded = false;
		Member spouseOfCurrentMember = spouceOfNewMember.getSpouse();
		Member spouseGiven = newMember.getSpouse();
		if (spouseGiven != null && spouseOfCurrentMember == null
				&& spouceOfNewMember.equals(spouseGiven)) {
			
			Member temp = copyMember(newMember);
			temp.setSpouse(spouceOfNewMember);
			spouceOfNewMember.setSpouse(temp);
			isSpouseAdded = true;
		}
		return isSpouseAdded;
	}
	/**
	 * @param newMember
	 * @param parent
	 * @param memberParentIndex
	 * @return List<Member>
	 */
	private List<Member> searchAndInsert(Member newMember, Member parent) {//,int memberParentIndex) {

		if (linkSpouse(newMember, parent)) {
			return Arrays.asList(newMember.getParents());
		}
		List<Member> notFound = insert(newMember, parent);
		if (!notFound.isEmpty()) {
			
			List<Member> kids = parent.getKids();
			if (kids != null) {
				for (Member kid: parent.getKids()) {
					
					notFound = searchAndInsert(newMember, kid);
					if (notFound.size() < newMember.getParents().length) {
						if (kid.getKids() == null) {
							kids = new LinkedList<>();
							parent.setKids(kids);
						}
						kid.getKids().add(copyMember(newMember));
						return notFound;
					}
				}
			}
		}
		if (parent.getKids() == null) {
			parent.setKids(new LinkedList<>());
		}
		parent.getKids().add(copyMember(newMember));
		return notFound;
	}
	private List<Member> insert(Member newMember, Member parent) {
		
		List<Member> notFound = new LinkedList<>();
		int count = 0;
		for (Member dummyParents: newMember.getParents()) {
			
			notFound.add(dummyParents);
			Member spouse = parent.getSpouse();
			if (parent.equals(dummyParents)) {
				
				notFound.remove(dummyParents);
				newMember.getParents()[count] = parent;
			} else if (spouse != null && spouse.equals(dummyParents)) {
				
				notFound.remove(dummyParents);
				newMember.getParents()[count] = spouse;
			}
			count++;
		}
		return notFound;
	}
	public Member getFamilyHead() {
		return copyMember(head);
	}
}
