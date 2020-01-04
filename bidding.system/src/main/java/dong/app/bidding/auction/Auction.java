package dong.app.bidding.auction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import dong.app.bidding.auction.bid.Bid;
import dong.app.bidding.auction.product.Product;
import dong.app.bidding.user.User;

@Entity
public class Auction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "create_time", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime createTime;

	@Version
	private Long version;

	@Column
	private String description;

	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	@OneToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("price DESC")
	private List<Bid> bids = new ArrayList<>();

	@Column(name = "end_time", updatable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime endTime;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Optional<Bid> getHighestBid() {
		return bids.isEmpty() ? Optional.empty() : Optional.of(bids.get(0));
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@PrePersist
	public void prePersist() {
		createTime = LocalDateTime.now();
	}
}
