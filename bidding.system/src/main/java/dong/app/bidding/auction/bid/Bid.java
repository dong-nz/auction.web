package dong.app.bidding.auction.bid;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import dong.app.bidding.auction.Auction;
import dong.app.bidding.user.User;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@Column(nullable = false)
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "auction_id")
	private Auction auction;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User owner;

	@Column(nullable = false, updatable = false)
	private LocalDateTime bidTime;

	@PrePersist
	public void prePersist() {
		bidTime = LocalDateTime.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User user) {
		this.owner = user;
	}

	public LocalDateTime getBidTime() {
		return bidTime;
	}
}
