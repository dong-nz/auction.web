package dong.app.bidding;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import dong.app.bidding.auction.Auction;
import dong.app.bidding.auction.AuctionRepository;
import dong.app.bidding.auction.Status;

@RestController
public class HelloController {

	private static Logger logger = LoggerFactory.getLogger(HelloController.class);

	@Autowired
	private AuctionRepository auctionRepository;

	@GetMapping("/")
	public String hello() {

		Optional<Auction> auction = auctionRepository.findById(1L);
		auction.get().setStatus(Status.BIDDING);
		auctionRepository.save(auction.get());
		
		if (auction.isPresent()) {
			auction.get().getBids().forEach(bid -> {
				logger.info(String.format("Bid id: %s %s", bid.getId(), bid.getPrice()));
			});

		}
		return "Hello World, from Spring Boot 2!";
	}
	
}
