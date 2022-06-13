
package domain;


public class SalesCustomer {
    
    //Atributos los cuales vienen siendo el nombre de las columas de su respectiva tabla.
    private int customerID;
    private int personID;
    private int storeID;
    private int territoryID;
    private String accountNumber;
    private String rowguid;
    private String modifiedDate;
    //Axuiliar para recuperar una columna del sp_listarClienteNorthAmerica
    private int NumberOfOrders;

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString() {
        return "SalesCustomer{" + "customerID=" + customerID + ", personID=" + personID + ", storeID=" + storeID + ", territoryID=" + territoryID + ", accountNumber=" + accountNumber + ", rowguid=" + rowguid + ", modifiedDate=" + modifiedDate + '}';
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getPersonID() {
        return this.personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getStoreID() {
        return this.storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getTerritoryID() {
        return this.territoryID;
    }

    public void setTerritoryID(int territoryID) {
        this.territoryID = territoryID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getRowguid() {
        return this.rowguid;
    }

    public void setRowguid(String rowguid) {
        this.rowguid = rowguid;
    }

    public String getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getNumberOfOrders() {
        return NumberOfOrders;
    }

    public void setNumberOfOrders(int NumberOfOrders) {
        this.NumberOfOrders = NumberOfOrders;
    }
    
    
    
}
