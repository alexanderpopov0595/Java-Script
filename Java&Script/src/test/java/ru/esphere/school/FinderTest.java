package ru.esphere.school;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;
import ru.esphere.school.service.Finder;

public class FinderTest {

	int targetAge = 25;

	// test data
	List<Member> members1;
	List<Member> members2;
	MembersGroup group1;
	MembersGroup group2;
	List<MembersGroup> groups;

	// expected results
	Set<String> expectedResults;

	@Before
	public void initialize() {
		// first group
		members1 = new ArrayList<Member>();

		members1.add(new Member("Anton", 18));
		members1.add(new Member("Boris", 30));
		members1.add(new Member("Andrei", 20));
		members1.add(new Member("Bogdan", 35));
		members1.add(new Member("Alex", 25));
		members1.add(new Member("Bill", 37));

		group1 = new MembersGroup("Group 1", members1);

		// second group
		members2 = new ArrayList<Member>();

		members2.add(new Member("Alice", 17));
		members2.add(new Member("Bella", 30));
		members2.add(new Member("Anton", 18));
		members2.add(new Member("Barbara", 18));
		members2.add(new Member("Anna", 25));
		members2.add(new Member("Britney", 26));

		group2 = new MembersGroup("Group 2", members2);

		// list of groups
		groups = new ArrayList<MembersGroup>();
		groups.add(group1);
		groups.add(group2);

		expectedResults = new HashSet<String>();

		for (MembersGroup membersGroup : groups) {
			for (Member member : membersGroup.getMembers()) {
				if (member.getAge() > targetAge) {
					String name = member.getName();
					expectedResults.add(name);
				}
			}
		}

	}

	@Test
	public void testFindOldMembers() {
		Finder finder = new Finder();
		assertEquals(finder.findOldMembers(groups, targetAge), expectedResults);

	}

}
