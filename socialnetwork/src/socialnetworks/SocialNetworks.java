package socialnetworks;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import lt.infobalt.itakademija.javalang.exam.socialnetwork.Friend;
import lt.infobalt.itakademija.javalang.exam.socialnetwork.FriendNotFoundException;
import lt.infobalt.itakademija.javalang.exam.socialnetwork.SocialNetwork;

public class SocialNetworks implements SocialNetwork {
	private Collection<Friend> friends = new ArrayList<>();

	@Override
	public void addFriend(Friend friend) {
		if (!friends.contains(friend)) {
			this.friends.add(friend);
		}
		if (friend == null) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Collection<Friend> findByCity(String city) {
		if (city == null) {
			throw new IllegalArgumentException();
		}
		return friends.stream().filter(friend -> friend.getCity().equals(city)).collect(Collectors.toList());
	}

	@Override
	public Friend findFriend(String first, String last) throws FriendNotFoundException {
		if ((first == null) || (last == null)) {
			throw new IllegalArgumentException();
		}
		friends.stream().filter(friend -> (first.equals(friend.getFirstName())) && (last.equals(friend.getLastName())))
				.findAny().orElseThrow(() -> new FriendNotFoundException(first, last));

		return (Friend) this.friends.stream().filter(friend -> friend.getFirstName().equals(first))
				.filter(friend -> friend.getLastName().equals(last)).findFirst().orElse(null);
	}

	@Override
	public int getNumberOfFriends() {
		return this.friends.size();
	}

	@Override
	public Collection<Friend> getOrderedFriends() {

		Comparator<Friend> comparator = Comparator.comparing(Friend::getCity).thenComparing(Friend::getFirstName)
				.thenComparing(Friend::getLastName);

		return this.friends.stream().sorted(comparator).collect(Collectors.toList());

	}

}
