package dong.app.bidding.auction.bid;

import dong.app.bidding.auction.Auction;

public interface BidService {

	Iterable<Bid> findAll(Auction auction);

	Bid save(Auction auction, Bid bid);
}
