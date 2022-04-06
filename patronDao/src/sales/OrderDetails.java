
package sales;

import java.sql.Date;


public class OrderDetails {
    int SalesOrderID; //PK
    int SalesOrderDetailID; // PK
    String CarrierTrackingNumber;
    int OrderQty;
    int ProductID;
    int SpecialOfferID;
    float UnitPrice;
    float UnitPriceUnite;
    float LineTotal;
    String rowguid;
    Date ModifiedDate;

    public int getSalesOrderID() {
        return SalesOrderID;
    }

    public void setSalesOrderID(int SalesOrderID) {
        this.SalesOrderID = SalesOrderID;
    }

    public int getSalesOrderDetailID() {
        return SalesOrderDetailID;
    }

    public void setSalesOrderDetailID(int SalesOrderDetailID) {
        this.SalesOrderDetailID = SalesOrderDetailID;
    }

    public String getCarrierTrackingNumber() {
        return CarrierTrackingNumber;
    }

    public void setCarrierTrackingNumber(String CarrierTrackingNumber) {
        this.CarrierTrackingNumber = CarrierTrackingNumber;
    }

    public int getOrderQty() {
        return OrderQty;
    }

    public void setOrderQty(int OrderQty) {
        this.OrderQty = OrderQty;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getSpecialOfferID() {
        return SpecialOfferID;
    }

    public void setSpecialOfferID(int SpecialOfferID) {
        this.SpecialOfferID = SpecialOfferID;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public float getUnitPriceUnite() {
        return UnitPriceUnite;
    }

    public void setUnitPriceUnite(float UnitPriceUnite) {
        this.UnitPriceUnite = UnitPriceUnite;
    }

    public float getLineTotal() {
        return LineTotal;
    }

    public void setLineTotal(float LineTotal) {
        this.LineTotal = LineTotal;
    }

    public String getRowguid() {
        return rowguid;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Date ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

}
