package javasrc;

public class ProductNoOfItem {
	   private String productName;
	   private Integer numberOfItem;
	   
	public ProductNoOfItem(String string1, Integer parseInt) {
		
		this.productName=string1;
		this.numberOfItem=parseInt;
		
		// TODO Auto-generated constructor stub
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
        return String.format("ProductName: %s\r\nNumberOfItem: %d\r\n", 
        		 productName, numberOfItem);
    }

	
}
