package dong.app.bidding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dong.app.bidding.auction.Auction;
import dong.app.bidding.auction.AuctionRepository;
import dong.app.bidding.auction.Status;
import dong.app.bidding.auction.bid.Bid;
import dong.app.bidding.auction.bid.BidRepository;
import dong.app.bidding.auction.product.Product;
import dong.app.bidding.auction.product.ProductRepository;
import dong.app.bidding.user.User;
import dong.app.bidding.user.UserRepository;

@SpringBootApplication
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ProductRepository productRepository, AuctionRepository auctionRepository,
			UserRepository userRepository, BidRepository bidRepository) {
		return args -> {

			Optional<User> user = userRepository.findByUsername("user");

			Product item = new Product();
			item.setDescription("Item 1 description");
			item.setName("Item 1");
			item.setPrice(BigDecimal.valueOf(1000));

			if (user.isPresent()) {
				item.setOwner(user.get());
			}

			productRepository.save(item);

			Auction auction = new Auction();
			auction.setDescription("Auction for Item 1");
			auction.setProduct(item);
			auction.setStatus(Status.NEW);

			if (user.isPresent()) {
				auction.setOwner(user.get());
			}
			auctionRepository.save(auction);

			List<Bid> bids = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				Bid bet = new Bid();
				bet.setAuction(auction);
				bet.setPrice(BigDecimal.valueOf(1000 + i));

				if (user.isPresent()) {
					bet.setOwner(user.get());
				}

				bids.add(bet);

			}

			bidRepository.saveAll(bids);

		};
	}

}
