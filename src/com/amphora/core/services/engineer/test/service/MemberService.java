package com.amphora.core.services.engineer.test.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.amphora.core.services.engineer.test.bean.Member;

public class MemberService {

	private Member head;
	public MemberService() {
	}
	public List<Member> insert(Member member) {
		
		if (head == null) {
			head = member;
			return null;
		} else {
			
			Member[] parents = member.getParents();
			if (parents == null || parents.length > 2) {
				throw new RuntimeException("Invalid parents data.");
			}
			return insert(member, head, 0);
		}
	}
	private List<Member> insert(Member newMember, Member parent,
			int memberParentIndex) {

		String parentToMatch = parent.getName();
		Member[] newMemberParents = newMember.getParents();
		Member parentOfNewMember = newMemberParents[memberParentIndex];
		List<Member> parentFound = new ArrayList<>();
		if (parentToMatch.equalsIgnoreCase(parentOfNewMember.getName())) {

			List<Member> kids = parent.getKids();
			if (kids == null) {
				kids = new LinkedList<>();
				parent.setKids(kids);
			}
			kids.add(newMember);
			
			parentFound.add(parent);
			return parentFound;
		} else {
			
			List<Member> kids = parent.getKids();
			if (kids != null) {
				for (Member kid: parent.getKids()) {
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
