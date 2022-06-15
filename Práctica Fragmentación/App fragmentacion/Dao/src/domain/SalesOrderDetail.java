
package domain;


public class SalesOrderDetail {
    
    //Atributos los cuales vienen siendo el nombre de las columas de su respectiva tabla.
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
    // auxiliar para recupera la columna en el sp_productoMasSolicitadoEuropa
    private int Cantidad_Productos;

    public SalesOrderDetail() {
    }

    //Constructor buscador. Se creo este constructor ya que con estos atributos, son los unicos
    // que se necesitan para buscar los demas datos para llevar a cabo una insercion en esta tabla.
    public SalesOrderDetail(int salesOrderID, int productID, int orderQty) { 
        this.salesOrderID = salesOrderID;
        this.orderQty = orderQty;
        this.productID = productID;
    }

    //Constructor insertor. Este constructor contiene los datos necesarios para llevar a cabo una insercion
    // en su respectiva tabla.
    public SalesOrderDetail(int salesOrderID, int orderQty, int productID, int specialOfferID, double unitPrice, double unitPriceDiscount, double lineTotal, String rowguid, String modifiedDate) {
        this.salesOrderID = salesOrderID;
        this.orderQty = orderQty;
        this.productID = productID;
        this.specialOfferID = specialOfferID;
        this.unitPrice = unitPrice;
        this.unitPriceDiscount = unitPriceDiscount;
        this.lineTotal = lineTotal;
        this.rowguid = rowguid;
        this.modifiedDate = modifiedDate;
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

    public int getCantidad_Productos() {
        return Cantidad_Productos;
    }

    public void setCantidad_Productos(int Cantidad_Productos) {
        this.Cantidad_Productos = Cantidad_Productos;
    }
    
    
}
