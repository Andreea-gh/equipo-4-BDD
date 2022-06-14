
package domain;

import java.sql.Date;


public class ProductionProduct {
    private int ProductID;
    private String Name;
    private String ProductNumber;
    private int MakeFlag;
    private int FinishedGoodsFlag;
    private String Color;
    private int SafetyStockLevel;
    private int ReorderPoint;
    private float StandarCost;
    private float ListPrice;
    private String Size;
    private String SizeUniteMeasureCode;
    private String WeightUniteMeasureCode;
    private String Weight;
    private int DaysToManufacture;
    private String ProductLine;
    private String Clase;
    private String Style;
    private int ProductSubCategoryID;
    private int ProductModelID;
    private Date SellStartDate;
    private Date SellEndDate;
    private Date DiscontinuedDate;
    private String rowguid;
    private Date ModifiedDate;

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getProductNumber() {
        return ProductNumber;
    }

    public void setProductNumber(String ProductNumber) {
        this.ProductNumber = ProductNumber;
    }

    public int getMakeFlag() {
        return MakeFlag;
    }

    public void setMakeFlag(int MakeFlag) {
        this.MakeFlag = MakeFlag;
    }

    public int getFinishedGoodsFlag() {
        return FinishedGoodsFlag;
    }

    public void setFinishedGoodsFlag(int FinishedGoodsFlag) {
        this.FinishedGoodsFlag = FinishedGoodsFlag;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public int getSafetyStockLevel() {
        return SafetyStockLevel;
    }

    public void setSafetyStockLevel(int SafetyStockLevel) {
        this.SafetyStockLevel = SafetyStockLevel;
    }

    public int getReorderPoint() {
        return ReorderPoint;
    }

    public void setReorderPoint(int ReorderPoint) {
        this.ReorderPoint = ReorderPoint;
    }

    public float getStandarCost() {
        return StandarCost;
    }

    public void setStandarCost(float StandarCost) {
        this.StandarCost = StandarCost;
    }

    public float getListPrice() {
        return ListPrice;
    }

    public void setListPrice(float ListPrice) {
        this.ListPrice = ListPrice;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getSizeUniteMeasureCode() {
        return SizeUniteMeasureCode;
    }

    public void setSizeUniteMeasureCode(String SizeUniteMeasureCode) {
        this.SizeUniteMeasureCode = SizeUniteMeasureCode;
    }

    public String getWeightUniteMeasureCode() {
        return WeightUniteMeasureCode;
    }

    public void setWeightUniteMeasureCode(String WeightUniteMeasureCode) {
        this.WeightUniteMeasureCode = WeightUniteMeasureCode;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public int getDaysToManufacture() {
        return DaysToManufacture;
    }

    public void setDaysToManufacture(int DaysToManufacture) {
        this.DaysToManufacture = DaysToManufacture;
    }

    public String getProductLine() {
        return ProductLine;
    }

    public void setProductLine(String ProductLine) {
        this.ProductLine = ProductLine;
    }

    public String getClase() {
        return Clase;
    }

    public void setClase(String Clase) {
        this.Clase = Clase;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String Style) {
        this.Style = Style;
    }

    public int getProductSubCategoryID() {
        return ProductSubCategoryID;
    }

    public void setProductSubCategoryID(int ProductSubCategoryID) {
        this.ProductSubCategoryID = ProductSubCategoryID;
    }

    public int getProductModelID() {
        return ProductModelID;
    }

    public void setProductModelID(int ProductModelID) {
        this.ProductModelID = ProductModelID;
    }

    public Date getSellStartDate() {
        return SellStartDate;
    }

    public void setSellStartDate(Date SellStartDate) {
        this.SellStartDate = SellStartDate;
    }

    public Date getSellEndDate() {
        return SellEndDate;
    }

    public void setSellEndDate(Date SellEndDate) {
        this.SellEndDate = SellEndDate;
    }

    public Date getDiscontinuedDate() {
        return DiscontinuedDate;
    }

    public void setDiscontinuedDate(Date DiscontinuedDate) {
        this.DiscontinuedDate = DiscontinuedDate;
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
