package dong.app.bidding.auction.bid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dong.app.bidding.auction.Auction;
import dong.app.bidding.auction.AuctionRepository;

@Service
public class BidServiceImpl implements BidService {

	@Autowired
	private AuctionRepository auctionRepository;

	@Autowired
	private BidRepository bidRepository;

	@Override
	public Iterable<Bid> findAll(Auction auction) {
		return auctionRepository.findById(auction.getId()).get().getBids();
	}

	@Override
	public Bid save(Auction auction, Bid bid) {
		// validate bid before saving

		bid.setAuction(auction);
		return bidRepository.save(bid);
	}

}
