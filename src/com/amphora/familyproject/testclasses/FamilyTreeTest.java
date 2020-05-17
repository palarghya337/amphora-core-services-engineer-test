package com.amphora.familyproject.testclasses;

import java.util.List;
import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.amphora.familyproject.bean.Member;
import com.amphora.familyproject.bean.Member.Gender;
import com.amphora.familyproject.bean.Partners;
import com.amphora.familyproject.service.Family;

/**
 * This is the dummy test class to check whether
 * my code is working properly or not. This test
 * class is not structured well.
 */
public class FamilyTreeTest {

	@Test
	public void testInsert() {
		
		Member akash = new Member("Akash Dutta", Gender.Male, 110);
		Member pramila = new Member("Pramila Dey", Gender.Female, 103);
		
		Partners partners = new Partners(akash, pramila);
		pramila.setLifePartners(partners);
		akash.setLifePartners(partners);
		Family family = new Family(partners);
		
		insertChildOfAkashAndPramila(partners, family);
		/*
		 * I am adding spouse of all the child of Akash
		 * and pramila
		 **/
		insertSpouseOfAllTheChildrens(family);
		// Reyansh is the child of Akash and pramila
		insertChildOfReyanshKakali(family);
		insertSpouseOfChildOfReyanshKakali(family);
		// Ballav is child of Reyansh and Kakali
		insertChildOfHiyaBallav(family);
		// Kaberi is child of Reyansh and Kakali
		insertChildOfRajuKaberi(family);
		// Kuntal is child of Reyansh and Kakali
		insertChildOfNandiniKuntal(family);
		// insert found another child of akash and pramila
		insertFoundedChildOfAkashPramila(family);
		//print(family.getFamilyHead());
		print(family.getFamilyHead());
		System.out.println("----------------------------------");
		family.getSortedListOfMembers()
			.stream()
			.forEach(member ->
			System.out.println(member.getName() + "["
			+ member.getAge() + "]"));
	}
	private void insertFoundedChildOfAkashPramila(Family family) {
		
		Member akash = new Member("Akash Dutta", Gender.Male, 110);
		Member pramila = new Member("Pramila Dey", Gender.Female, 103);
		
		Partners head = new Partners(akash, pramila);
		Member arav = new Member("Arav Dutta", Gender.Male, 80);
		arav.setParents(head);
		arav = family.addMember(arav, false);
		Assert.assertEquals(2, arav.getParents().getChildren().size());
	}
	private void insertSpouseOfAllTheChildrens(Family family) {
		
		Member reyansh = new Member("Reyansh Dutta", Gender.Male, 85);
		Member kakali = new Member("Kakali Guha", Gender.Female, 80);
		Partners kakaliReyansh = new Partners(reyansh, kakali);
		kakali.setLifePartners(kakaliReyansh);
		Member child = family.addMember(kakali, true);
		
		Assert.assertNotNull(child.getLifePartners().getFirstPartner().getParents());
	}
	private void insertChildOfAkashAndPramila(Partners head, Family family) {
		
		Member reyansh = new Member("Reyansh Dutta", Gender.Male, 85);
		reyansh.setParents(head);
		Member child = family.addMember(reyansh, false);
		List<Member> children = child.getParents().getChildren();
		Assert.assertEquals(1, children.size());
	}
	private void insertChildOfReyanshKakali(Family family) {
		
		Member reyansh = new Member("Reyansh Dutta", Gender.Male, 85);
		Member kakali = new Member("Kakali Guha", Gender.Female, 80);
		Partners head = new Partners(reyansh, kakali);
		
		Member kaberi = new Member("Kaberi Dutta", Gender.Female, 50);
		kaberi.setParents(head);
		Member child = family.addMember(kaberi, false);
		List<Member> children = child.getParents().getChildren();
		Assert.assertEquals(1, children.size());
		
		Member ballav = new Member("Ballav Dutta", Gender.Male, 52);
		ballav.setParents(head);
		child = family.addMember(ballav, false);
		Assert.assertEquals(2, children.size());

		Member kuntal = new Member("Kuntal Dutta", Gender.Male, 55);
		kuntal.setParents(head);
		child = family.addMember(kuntal, false);
		Assert.assertEquals(3, children.size());
	}
	private void insertSpouseOfChildOfReyanshKakali(Family family) {
		
		Member raju = new Member("Raju Sutradhar", Gender.Male, 52);
		Member kaberi = new Member("Kaberi Dutta", Gender.Female, 50);
		raju.setLifePartners(new Partners(raju, kaberi));
		Member child = family.addMember(raju, true);
		Assert.assertNotNull(child.getLifePartners().getSecondPartner().getParents());
		
		Member hiya = new Member("Hiya Roy", Gender.Female, 49);
		Member ballav = new Member("Ballav Dutta", Gender.Male, 52);
		hiya.setLifePartners(new Partners(ballav, hiya));
		child = family.addMember(hiya, true);
		Assert.assertNotNull(child.getLifePartners().getFirstPartner().getParents());
		
		Member nandini = new Member("Nandini Dey", Gender.Female, 50);
		Member kuntal = new Member("Kuntal Dutta", Gender.Male, 55);
		nandini.setLifePartners(new Partners(kuntal, nandini));
		child = family.addMember(nandini, true);
		Assert.assertNotNull(child.getLifePartners().getFirstPartner().getParents());
	}
	private void insertChildOfHiyaBallav(Family family) {
		
		Member hiya = new Member("Hiya Roy", Gender.Female, 49);
		Member ballav = new Member("Ballav Dutta", Gender.Male, 52);
		Partners head = new Partners(ballav, hiya);
		
		Member pratik = new Member("Pratik Dutta", Gender.Male, 22);
		pratik.setParents(head);
		Member child = family.addMember(pratik, false);
		Assert.assertEquals(1, child.getParents().getChildren().size());
		
		Member pooja = new Member("Pooja Dutta", Gender.Female, 26);
		pooja.setParents(head);
		child = family.addMember(pooja, false);
		Assert.assertEquals(2, child.getParents().getChildren().size());
	}
	private void insertChildOfRajuKaberi(Family family) {
		
		Member raju = new Member("Raju Sutradhar", Gender.Male, 52);
		Member kaberi = new Member("Kaberi Dutta", Gender.Female, 50);
		Partners head = new Partners(raju, kaberi);
		
		Member roshni = new Member("Roshni Sutradhar", Gender.Female, 30);
		roshni.setParents(head);
		Member child = family.addMember(roshni, false);
		Assert.assertEquals(1, child.getParents().getChildren().size());
	}
	private void insertChildOfNandiniKuntal(Family family) {
		
		Member nandini = new Member("Nandini Dey", Gender.Female, 50);
		Member kuntal = new Member("Kuntal Dutta", Gender.Male, 55);
		Partners head = new Partners(kuntal, nandini);
		
		Member sayan = new Member("Sayan Dutta", Gender.Male, 32);
		sayan.setParents(head);
		sayan = family.addMember(sayan, false);
		Assert.assertEquals(1, sayan.getParents().getChildren().size());
	}
	public void print(Partners parent) {
		
		if (Objects.nonNull(parent) &&
				Objects.nonNull(parent.getChildren())) {
			StringBuilder str = new StringBuilder();
			for (Member child: parent.getChildren()) {
				
				str.append(child.getName());
				str.append("[");
				str.append(child.getAge());
				str.append("], ");
				print(child.getLifePartners());
			}
			StringBuilder output = new StringBuilder();
			output.append(str.substring(0, str.length() - 2));
			output.append(" is/are children of ");
			output.append(parent.getFirstPartner().getName());
			output.append("[").append(parent.getFirstPartner().getAge()).append("] and ");
			output.append(parent.getSecondPartner().getName());
			output.append("[").append(parent.getSecondPartner().getAge()).append("]");
			System.out.println(output);
		}
	}
}
