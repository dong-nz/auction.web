package dong.app.bidding.auction.bid;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid> {

	@Override
	public int compare(Bid o1, Bid o2) {
		return o1.getPrice().compareTo(o2.getPrice()) * -1; // reverse order from highest to lowest
	}

}
