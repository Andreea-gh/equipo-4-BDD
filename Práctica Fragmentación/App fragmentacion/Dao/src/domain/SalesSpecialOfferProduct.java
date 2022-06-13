
package domain;


public class SalesSpecialOfferProduct {
    
    //Atributos los cuales vienen siendo el nombre de las columas de su respectiva tabla.
    private int specialOfferId;
    private int productId;
    private String rowguid;
    private String modifiedDate;

    @Override
    public String toString() {
        return "SalesSpecialOfferProduct{" + "specialOfferId=" + specialOfferId + ", productId=" + productId + ", rowguid=" + rowguid + ", modifiedDate=" + modifiedDate + '}';
    }

    
    public int getSpecialOfferId() {
        return specialOfferId;
    }

    public void setSpecialOfferId(int specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    
    
}
