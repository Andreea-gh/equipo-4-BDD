
package sales;

import java.sql.Date;


public class Customer {
    public int CustomerID;//PK IDENTITY
    public int PersonID;//FK
    public int StoreID;//FK
    public int TerritoryID;// FK
    public String AccountNumeber;
    public int rowguid;
    public Date datetime;

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }

    public int getTerritoryID() {
        return TerritoryID;
    }

    public void setTerritoryID(int TerritoryID) {
        this.TerritoryID = TerritoryID;
    }

    public String getAccountNumeber() {
        return AccountNumeber;
    }

    public void setAccountNumeber(String AccountNumeber) {
        this.AccountNumeber = AccountNumeber;
    }

    public int getRowguid() {
        return rowguid;
    }

    public void setRowguid(int rowguid) {
        this.rowguid = rowguid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}
