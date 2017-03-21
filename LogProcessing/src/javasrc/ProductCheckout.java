package javasrc;

public class ProductCheckout {
	   private String customerId;
	   private String productName;
	   private Integer numberOfItem;
	   
	public ProductCheckout(String string1, String string2, int parseInt) {
		
		this.customerId=string1;
		this.productName=string2;
		this.numberOfItem=parseInt;
		
		// TODO Auto-generated constructor stub
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(Integer numberOfItem) {
		this.numberOfItem = numberOfItem;
	}

	@Override
    public String toString() {
        return String.format("CustomerId: %s\r\nProductName: %s\r\nNumberOfItem: %d\r\n", 
        		customerId, productName, numberOfItem);
    }

	
}
