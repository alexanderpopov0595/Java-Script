package ru.esphere.school.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.esphere.school.data.Member;
import ru.esphere.school.data.MembersGroup;

public class Finder {
	/**
	 * Поиск групп людей старше определенного возраста.
	 *
	 * @param groups    группы
	 * @param targetAge возраст для поиска
	 * @return список имен групп из списка групп старше возраста targetAge
	 */
	/*public Set<String> findOldMembers(List<MembersGroup> groups, int targetAge) {
		Set<String> groupsNames = new HashSet<>();
		for (MembersGroup membersGroup : groups) {
			for (Member member : membersGroup.getMembers()) {
				if (member.getAge() > targetAge) {
					String name = member.getName();
					groupsNames.add(name);
				}
			}
		}
		return groupsNames;
	}*/

	public Set<String> findOldMembers(List<MembersGroup> groups, int targetAge) {
		// get list of lists of members
		Stream<List<Member>> listOfLists = groups.stream().map(s -> s.getMembers());
		// get list of members
		Stream<Member> listOfMembers = listOfLists.flatMap(s -> s.stream());
		// get set of members with ages > targetAge
		return listOfMembers.filter(s -> s.getAge() > targetAge).map(s -> s.getName()).collect(Collectors.toSet());
	}

}
