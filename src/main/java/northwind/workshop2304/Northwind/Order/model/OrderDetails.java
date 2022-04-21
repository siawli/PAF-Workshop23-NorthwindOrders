package northwind.workshop2304.Northwind.Order.model;

public class OrderDetails {

    private Double quantity;
    private Double unitPrice;
    private Double discount;
    private Double standardCost;

    public Double getStandardCost() {
        return standardCost;
    }
    public void setStandardCost(Double standardCost) {
        this.standardCost = standardCost;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public Double getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    
}
