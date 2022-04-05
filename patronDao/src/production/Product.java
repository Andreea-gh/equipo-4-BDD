
package production;

import java.sql.Date;


public class Product {
    int ProductID; // PK
    String Name;
    String ProductNumber;
    char MakeFlag;
    char FinishedGoodsFlag;
    String Color;
    int SafetyStockLevel;
    int RecorderPoint;
    float StandarCost;
    float ListPrice;
    String Size;
    String SizeUnitMeasureCode;
    String WeightUnitMeasureCode;
    int Weight;
    int DaysToManufacture;
    String ProductLine;
    String Clase;
    String Style;
    int ProductSubcategoryID;
    int ProductModelID;
    Date SellStartDate;
    Date SellEndDate;
    Date DiscontinuedDate;
    String rowguid;
    Date datetime;

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

    public char getMakeFlag() {
        return MakeFlag;
    }

    public void setMakeFlag(char MakeFlag) {
        this.MakeFlag = MakeFlag;
    }

    public char getFinishedGoodsFlag() {
        return FinishedGoodsFlag;
    }

    public void setFinishedGoodsFlag(char FinishedGoodsFlag) {
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

    public int getRecorderPoint() {
        return RecorderPoint;
    }

    public void setRecorderPoint(int RecorderPoint) {
        this.RecorderPoint = RecorderPoint;
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

    public String getSizeUnitMeasureCode() {
        return SizeUnitMeasureCode;
    }

    public void setSizeUnitMeasureCode(String SizeUnitMeasureCode) {
        this.SizeUnitMeasureCode = SizeUnitMeasureCode;
    }

    public String getWeightUnitMeasureCode() {
        return WeightUnitMeasureCode;
    }

    public void setWeightUnitMeasureCode(String WeightUnitMeasureCode) {
        this.WeightUnitMeasureCode = WeightUnitMeasureCode;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int Weight) {
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

    public int getProductSubcategoryID() {
        return ProductSubcategoryID;
    }

    public void setProductSubcategoryID(int ProductSubcategoryID) {
        this.ProductSubcategoryID = ProductSubcategoryID;
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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
    
    
}
