package com.amphora.familyproject.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.exception.InvalidDataException;

public class MemberService {

	private Member head;
	public MemberService() {
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
			head = member;
			return null;
		} else {
			/*
			 * If parent of new member is not given or given
			 * more than two parents then throws InvalidDataException
			 **/
			Member[] parents = member.getParents();
			if (parents == null || parents.length > 2) {
				throw new InvalidDataException("Invalid parents data.");
			}
			return insert(member, head, 0);
		}
	}
	/**
	 * @param newMember
	 * @param member
	 * @param memberParentIndex
	 * @return List<Member>
	 */
	private List<Member> insert(Member newMember, Member member,
			int memberParentIndex) {

		String memberName = member.getName();
		Member[] newMemberParents = newMember.getParents();
		Member parentOfNewMember = newMemberParents[memberParentIndex];
		List<Member> parentFound = new ArrayList<>();
		if (memberName.equalsIgnoreCase(parentOfNewMember.getName())) {
			/*
			 * If member name and new member parent name is same
			 * then add new member as a kid of current member and
			 * return the current member in a list.
			 **/
			List<Member> kids = member.getKids();
			if (kids == null) {
				kids = new LinkedList<>();
				member.setKids(kids);
			}
			kids.add(newMember);
			parentFound.add(member);
			return parentFound;
		} else {
			/*
			 * If we member name and new member parent name is not
			 * same then we find if there are any kids of current
			 * member. If kids are present then we call the same
			 * method to do all the stuff we previously doing.
			 * If we found parent then we add that parent in a list
			 * and return the list.
			 * We return empty list when -
			 *   we do not find the parent of new member.
			 *   if there is no kids of current member
			 **/
			List<Member> kids = member.getKids();
			if (kids != null) {
				for (Member kid: member.getKids()) {
					if(memberParentIndex >= newMemberParents.length) {
						break;
					}
					List<Member> temp = insert(newMember, kid,
							memberParentIndex);
					parentFound.addAll(temp);
					if (parentFound.size() != memberParentIndex) {
						memberParentIndex++;
					}
				}
			}
			return parentFound;
		}
	}
	public Member getFamilyHead() {
		return head;
	}
}
