package com.amphora.familyproject.testclasses;

import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.bean.Member.Gender;
import com.amphora.familyproject.service.Family;
import com.amphora.familyproject.bean.Partners;

public class FamilyTreeTest {

	@Test
	public void testInsert() {
		
		Member memberA = new Member("A", Gender.Male,
				110, null);
		Member spouceOfA = new Member("0", Gender.Female,
				103, null);
		
		Partners partners = new Partners(memberA, spouceOfA);
		spouceOfA.setPartner(partners);
		memberA.setPartner(partners);
		Family family = new Family(partners);
		
		testInsertChildOfA(partners, family);
		
		/*
		 * Member head = service.getFamilyHead(); print(head);
		 */
	}
	private void testInsertChildOfA(Partners head, Family family) {
		
		Member a0 = new Member("A0", Gender.Male, 85, head);
		Member child = family.addMember(a0, false);
		List<Member> children = child.getParents().getChildren();
		Assert.assertEquals(1, children.size());
		
		
		Member z0 = new Member("Z0", Gender.Female,
				80, null);
		Partners a0z0 = new Partners(a0, z0);
		z0.setPartner(a0z0);
		child = family.addMember(z0, true);
		Assert.assertEquals(1, children.size());
		
		testInsertChildOfA0Z0(a0z0, family);
		
		Member b0 = new Member("B0", Gender.Male, 80, head);
		child = family.addMember(b0, false);
		Assert.assertEquals(2, children.size());
		
		Member y0 = new Member("Y0", Gender.Female,
				75, head);
		y0.setPartner(new Partners(b0, y0));
		child = family.addMember(y0, true);
		Assert.assertEquals(2, children.size());
	}
	private void testInsertChildOfA0Z0(Partners a0z0, Family family) {
		
		Member a0z01 = new Member("A0Z01", Gender.Male, 55, a0z0);
		Member child = family.addMember(a0z01, false);
		List<Member> children = child.getParents().getChildren();
		Assert.assertEquals(1, children.size());
		
		Member a0z02 = new Member("A0Z02", Gender.Male, 52, a0z0);
		child = family.addMember(a0z02, false);
		Assert.assertEquals(2, children.size());
		
		Member a0z03 = new Member("A0Z03", Gender.Female, 50, a0z0);
		child = family.addMember(a0z03, false);
		Assert.assertEquals(3, children.size());
	}
	public void print(Partners parent) {
		
		boolean flag = true;
		if (Objects.nonNull(parent)) {
			for (Member child: parent.getChildren()) {
				
				if (flag) {
					System.out.println(parent.getChildren()
							+ " is/are children of "
							+ parent.getHubby().getName()
							+ " and " + parent.getWifey().getName());
					flag = false;
				}
				print(child.getPartner());
			}
		}
	}
}
