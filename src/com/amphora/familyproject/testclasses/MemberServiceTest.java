package com.amphora.familyproject.testclasses;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.bean.Member.Gender;
import com.amphora.familyproject.service.Family;

public class MemberServiceTest {

	@Test
	public void testInsert() {
		Member memberA = new Member("A", Gender.Male,
				110, null);
		Member spouceOfA = new Member("spouceOfA", Gender.Female,
				103, null);
		memberA.setSpouse(spouceOfA);
		spouceOfA.setSpouse(memberA);
		Family service = new Family(memberA);

		Member memberB = new Member("B", Gender.Male, 85,
				new Member[]{memberA, spouceOfA});
		List<Member> foundParents = service.insert(memberB);
		Assert.assertEquals(0, foundParents.size());
		/*
		Member grandParentFemale = new Member("grand Parent female",
				Gender.Female, 80, new Member[]{memberA});
		foundParents = service.insert(grandParentFemale);
		Assert.assertEquals(1, foundParents.size());
		
		Member parentMaleOne = new Member("parentMaleOne",
				Gender.Male, 55, new Member[] {grandParentMale,
						grandParentFemale});
		foundParents = service.insert(parentMaleOne);
		Assert.assertEquals(2, foundParents.size());
		
		Member parentMaleTwo = new Member("parentMaleTwo",
				Gender.Male, 50, new Member[] {grandParentMale,
						grandParentFemale});
		foundParents = service.insert(parentMaleTwo);
		Assert.assertEquals(2, foundParents.size());
		
		Member parentFemale = new Member("parentFemale",
				Gender.Female, 48, new Member[] {grandParentMale,
						grandParentFemale});
		foundParents = service.insert(parentFemale);
		Assert.assertEquals(2, foundParents.size());
		
		Member motherOfKidMale = new Member("modtherOfKidMale",
				Gender.Female, 50, null);
		Member kidMale = new Member("kidMale",
				Gender.Male, 29, new Member[] {parentMaleOne,
						motherOfKidMale});
		foundParents = service.insert(kidMale);
		Assert.assertEquals(1, foundParents.size());
		
		Member motherOfKidFemale = new Member("modtherOfKidMale",
				Gender.Female, 52, null);
		Member kidFemale = new Member("kidFemale",
				Gender.Female, 23, new Member[] {parentMaleTwo,
						motherOfKidFemale});
		foundParents = service.insert(kidFemale);
		Assert.assertEquals(1, foundParents.size());
		*/
		Member head = service.getFamilyHead();
		print(head);
	}
	public void print(Member parent) {
		
		if (parent != null) {
			
			List<Member> kids = parent.getKids();
			if (kids != null) {
				
				StringBuilder kidsString = new StringBuilder();
				for (Member kid: parent.getKids()) {
					
					print(kid);
					kidsString.append(kid.getName());
					kidsString.append(", ");
				}
				System.out.println("[" + kidsString
						.substring(0, kidsString.length() - 2)
				+ "] is child of " + parent.getName() + " and "
				+ parent.getSpouse().getName());
			}
		}
	}
}
