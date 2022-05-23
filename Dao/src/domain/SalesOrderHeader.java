
package domain;


public class SalesOrderHeader {
    
    private int salesOrderId;
    private int revisionNumber;
    private String orderDate;
    private String duedate;
    private String shipDate;
    private int status;
    private int onlineOrderFlag;
    private String salesOrderNumber;
    private String purchaseOrderNumber;
    private String accountNumber;
    private int customerId;
    private int salesPersionId;
    private int territoryId;
    private int billToAdressId;
    private int shipToAdressId;
    private int shipMethodId;
    private int creditCardId;
    private String creditCardAppovalCode;
    private int currencyRateId;
    private double subTotal;
    private double taxAmt;
    private double freight;
    private double totalDue;
    private String comment;
    private String rowguid;
    private String modifiedDate;

    @Override
    public String toString() {
        return "SalesOrderHeader{" + "salesOrderId=" + salesOrderId + ", revisionNumber=" + revisionNumber + ", orderDate=" + orderDate + ", duedate=" + duedate + ", shipDate=" + shipDate + ", status=" + status + ", onlineOrderFlag=" + onlineOrderFlag + ", salesOrderNumber=" + salesOrderNumber + ", purchaseOrderNumber=" + purchaseOrderNumber + ", accountNumber=" + accountNumber + ", customerId=" + customerId + ", salesPersionId=" + salesPersionId + ", territoryId=" + territoryId + ", billToAdressId=" + billToAdressId + ", shipToAdressId=" + shipToAdressId + ", shipMethodId=" + shipMethodId + ", creditCardId=" + creditCardId + ", creditCardAppovalCode=" + creditCardAppovalCode + ", currencyRateId=" + currencyRateId + ", subTotal=" + subTotal + ", taxAmt=" + taxAmt + ", freight=" + freight + ", totalDue=" + totalDue + ", comment=" + comment + ", rowguid=" + rowguid + ", modifiedDate=" + modifiedDate + '}';
    }

    public SalesOrderHeader() {
    }

    //Constructor buscador
    public SalesOrderHeader(int salesOrderId, int onlineOrderFlag, int customerId, int billToAdressId, double taxAmt, double freight) {
        this.salesOrderId = salesOrderId;
        this.onlineOrderFlag = onlineOrderFlag;
        this.customerId = customerId;
        this.billToAdressId = billToAdressId;
        this.taxAmt = taxAmt;
        this.freight = freight;
    }

    
    
    
    public int getSalesOrderId() {
        return salesOrderId;
    }

    public void setSalesOrderId(int salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(int revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public String getOderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOnlineOrderFlag() {
        return onlineOrderFlag;
    }

    public void setOnlineOrderFlag(int onlineOrderFlag) {
        this.onlineOrderFlag = onlineOrderFlag;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public void setSalesOrderNumber(String salesOrderNumber) {
        this.salesOrderNumber = salesOrderNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalesPersionId() {
        return salesPersionId;
    }

    public void setSalesPersionId(int salesPersionId) {
        this.salesPersionId = salesPersionId;
    }

    public int getTerritoryId() {
        return territoryId;
    }

    public void setTerritoryId(int territoryId) {
        this.territoryId = territoryId;
    }

    public int getBillToAdressId() {
        return billToAdressId;
    }

    public void setBillToAdressId(int billToAdressId) {
        this.billToAdressId = billToAdressId;
    }

    public int getShipToAdressId() {
        return shipToAdressId;
    }

    public void setShipToAdressId(int shipToAdressId) {
        this.shipToAdressId = shipToAdressId;
    }

    public int getShipMethodId() {
        return shipMethodId;
    }

    public void setShipMethodId(int shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    public int getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(int creditCardId) {
        this.creditCardId = creditCardId;
    }

    public String getCreditCardAppovalCode() {
        return creditCardAppovalCode;
    }

    public void setCreditCardAppovalCode(String creditCardAppovalCode) {
        this.creditCardAppovalCode = creditCardAppovalCode;
    }

    public int getCurrencyRateId() {
        return currencyRateId;
    }

    public void setCurrencyRateId(int currencyRateId) {
        this.currencyRateId = currencyRateId;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
