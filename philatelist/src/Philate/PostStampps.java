package Philate;

import philatelist.Philatelist;
import philatelist.PostStamp;
import java.util.*;

public class PostStampps implements Philatelist {

	private Collection<PostStamp> stamps = new ArrayList<PostStamp>();

	@Override
	public void addToCollection(PostStamp stamp) {
		if (((stamp) == null)) {
			throw new IllegalArgumentException();
		}
		if (stamp.getName() == null) {
			throw new IllegalArgumentException();
		}
		if (stamp.getName().isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (!this.stamps.contains(stamp)) {
			this.stamps.add(stamp);
		}
	}

	@Override
	public double getAveragePostStampPrice() {
		int sum = 0;
		int size = this.stamps.size();
		double average = 0;
		for (PostStamp g : stamps) {
			sum += g.getMarketPrice();
		}
		average = (double) sum / size;
		return average;
	}

	@Override
	public int getNumberOfPostStampsInCollection() {
		return this.stamps.size();
	}

	@Override
	public PostStamp getTheMostExpensivePostStamByMarketValue() {
		return this.stamps.stream().max(Comparator.comparing(PostStamp::getMarketPrice)).get();
	}

}
