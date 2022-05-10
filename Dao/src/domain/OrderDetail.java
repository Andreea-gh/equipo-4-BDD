
package domain;


public class OrderDetail {
    private int salesOrderID;
    private int salesOrderDetailID;
    private String carrierTrackingNumber;
    private int orderQty;
    private int productID;
    private int specialOfferID;
    private double unitPrice;
    private double unitPriceDiscount;
    private double lineTotal;
    private String rowguid;
    private String modifiedDate;

    public OrderDetail() {
    }

    public OrderDetail(int salesOrderID, int orderQty, int productID) { //constructor insertor
        this.salesOrderID = salesOrderID;
        this.orderQty = orderQty;
        this.productID = productID;
    }
    
    public int getSalesOrderID() {
        return salesOrderID;
    }

    public void setSalesOrderID(int salesOrderID) {
        this.salesOrderID = salesOrderID;
    }

    public int getSalesOrderDetailID() {
        return salesOrderDetailID;
    }

    public void setSalesOrderDetailID(int salesOrderDetailID) {
        this.salesOrderDetailID = salesOrderDetailID;
    }

    public String getCarrierTrackingNumber() {
        return carrierTrackingNumber;
    }

    public void setCarrierTrackingNumber(String carrierTrackingNumber) {
        this.carrierTrackingNumber = carrierTrackingNumber;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSpecialOfferID() {
        return specialOfferID;
    }

    public void setSpecialOfferID(int specialOfferID) {
        this.specialOfferID = specialOfferID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitPriceDiscount() {
        return unitPriceDiscount;
    }

    public void setUnitPriceDiscount(double unitPriceDiscount) {
        this.unitPriceDiscount = unitPriceDiscount;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getRowguid() {
        return rowguid;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "salesOrderID=" + salesOrderID + ", salesOrderDetailID=" + salesOrderDetailID + ", carrierTrackingNumber=" + carrierTrackingNumber + ", orderQty=" + orderQty + ", productID=" + productID + ", specialOfferID=" + specialOfferID + ", unitPrice=" + unitPrice + ", unitPriceDiscount=" + unitPriceDiscount + ", lineTotal=" + lineTotal + ", rowguid=" + rowguid + ", modifiedDate=" + modifiedDate + '}';
    }
}
