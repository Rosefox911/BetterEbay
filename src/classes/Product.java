package classes;

public class Product {
	private String ItemID;
	private String EndTime;
	private String ItemURL;
	private String ListingType;
	private String GalleryURL;
	private String PrimaryCategoryID;
	private String PrimaryCategoryName;
	private Long BidCount;
	private Double ConvertedCurrentPrice;
	private String ListingStatus;
	private String TimeLeft;
	private String Title;
	private String ShippingType;
	public String getItemID() {
		return ItemID;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getItemURL() {
		return ItemURL;
	}
	public void setItemURL(String itemURL) {
		ItemURL = itemURL;
	}
	public String getListingType() {
		return ListingType;
	}
	public void setListingType(String listingType) {
		ListingType = listingType;
	}
	public String getGalleryURL() {
		return GalleryURL;
	}
	public void setGalleryURL(String galleryURL) {
		GalleryURL = galleryURL;
	}
	public String getPrimaryCategoryID() {
		return PrimaryCategoryID;
	}
	public void setPrimaryCategoryID(String primaryCategoryID) {
		PrimaryCategoryID = primaryCategoryID;
	}
	public String getPrimaryCategoryName() {
		return PrimaryCategoryName;
	}
	public void setPrimaryCategoryName(String primaryCategoryName) {
		PrimaryCategoryName = primaryCategoryName;
	}
	public Long getBidCount() {
		return BidCount;
	}
	public void setBidCount(Long bidCount) {
		BidCount = bidCount;
	}
	public Double getConvertedCurrentPrice() {
		return ConvertedCurrentPrice;
	}
	public void setConvertedCurrentPrice(Double convertedCurrentPrice) {
		ConvertedCurrentPrice = convertedCurrentPrice;
	}
	public String getListingStatus() {
		return ListingStatus;
	}
	public void setListingStatus(String listingStatus) {
		ListingStatus = listingStatus;
	}
	public String getTimeLeft() {
		return TimeLeft;
	}
	public void setTimeLeft(String timeLeft) {
		TimeLeft = timeLeft;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getShippingType() {
		return ShippingType;
	}
	public void setShippingType(String shippingType) {
		ShippingType = shippingType;
	}
	public Product(String itemID, String endTime, String itemURL,
			String listingType, String galleryURL, String primaryCategoryID,
			String primaryCategoryName, Long bidCount,
			Double convertedCurrentPrice, String listingStatus,
			String timeLeft, String title, String shippingType) {
		super();
		ItemID = itemID;
		EndTime = endTime;
		ItemURL = itemURL;
		ListingType = listingType;
		GalleryURL = galleryURL;
		PrimaryCategoryID = primaryCategoryID;
		PrimaryCategoryName = primaryCategoryName;
		BidCount = bidCount;
		ConvertedCurrentPrice = convertedCurrentPrice;
		ListingStatus = listingStatus;
		TimeLeft = timeLeft;
		Title = title;
		ShippingType = shippingType;
	}
	public Product() {
		super();
	}
	
	
}
