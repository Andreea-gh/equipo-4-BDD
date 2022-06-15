
package domain;

import java.sql.Date;

public class SalesSpecialOffer {
    private int SpecialOfferID;
    private String Description;
    private float DiscountPct;
    private String Type;
    private String Category;
    private Date StartDate;
    private Date EndDate;
    private int MinQty;
    private int MaxQty;
    private String rowguid;
    private Date ModifiedDate;

    public int getSpecialOfferID() {
        return SpecialOfferID;
    }

    public void setSpecialOfferID(int SpecialOfferID) {
        this.SpecialOfferID = SpecialOfferID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getDiscountPct() {
        return DiscountPct;
    }

    public void setDiscountPct(float DiscountPct) {
        this.DiscountPct = DiscountPct;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date StartDate) {
        this.StartDate = StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date EndDate) {
        this.EndDate = EndDate;
    }

    public int getMinQty() {
        return MinQty;
    }

    public void setMinQty(int MinQty) {
        this.MinQty = MinQty;
    }

    public int getMaxQty() {
        return MaxQty;
    }

    public void setMaxQty(int MaxQty) {
        this.MaxQty = MaxQty;
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
