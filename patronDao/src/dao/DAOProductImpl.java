package dao;

import interfaces.DAOProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import production.Product;

public class DAOProductImpl extends Conexion implements DAOProduct {

    @Override
    public void registrar(Product prod) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO Copia.copiaProduct (ProductID,Name,ProductNumber,MakeFlag,FinishedGoodsFlag,Color,SafetyStockLevel,RecorderPoint,StandarCost,ListPrice,Size,SizeUnitMeasureCode,WeightUnitMeasureCode,Weight,DaysToManufacture,ProductLine,Clase,Style,ProductSubcategoryID,ProductModelID,SellStartDate,SellEndDate,DiscontinuedDate,rowguid,ModifiedDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, prod.getProductID());
            st.setString(2, prod.getName());
            st.setString(3, prod.getProductNumber());
            st.setByte(4, prod.getMakeFlag());
            st.setByte(5, prod.getFinishedGoodsFlag());
            st.setString(6, prod.getColor());
            st.setInt(7, prod.getSafetyStockLevel());
            st.setInt(8, prod.getRecorderPoint());
            st.setFloat(9, prod.getStandarCost());
            st.setFloat(10, prod.getListPrice());
            st.setString(11, prod.getSize());
            st.setString(12, prod.getSizeUnitMeasureCode());
            st.setString(13, prod.getWeightUnitMeasureCode());
            st.setInt(14, prod.getWeight());
            st.setInt(15, prod.getDaysToManufacture());
            st.setString(16, prod.getProductLine());
            st.setString(17, prod.getClase());
            st.setString(18, prod.getStyle());
            st.setInt(19, prod.getProductSubcategoryID());
            st.setInt(20, prod.getProductModelID());
            st.setDate(21, prod.getSellStartDate());
            st.setDate(22, prod.getSellEndDate());
            st.setDate(23, prod.getDiscontinuedDate());
            st.setString(24, prod.getRowguid());
            st.setDate(25, prod.getModifiedDate());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }

    }

    @Override
    public void modificar(Product prod) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("UPDATE Copia.copiaProduct (ProductID,Name,ProductNumber,MakeFlag,FinishedGoodsFlag,Color,SafetyStockLevel,RecorderPoint,StandarCost,ListPrice,Size,SizeUnitMeasureCode,WeightUnitMeasureCode,Weight,DaysToManufacture,ProductLine,Clase,Style,ProductSubcategoryID,ProductModelID,SellStartDate,SellEndDate,DiscontinuedDate,rowguid,ModifiedDate) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            st.setInt(1, prod.getProductID());
            st.setString(2, prod.getName());
            st.setString(3, prod.getProductNumber());
            st.setByte(4, prod.getMakeFlag());
            st.setByte(5, prod.getFinishedGoodsFlag());
            st.setString(6, prod.getColor());
            st.setInt(7, prod.getSafetyStockLevel());
            st.setInt(8, prod.getRecorderPoint());
            st.setFloat(9, prod.getStandarCost());
            st.setFloat(10, prod.getListPrice());
            st.setString(11, prod.getSize());
            st.setString(12, prod.getSizeUnitMeasureCode());
            st.setString(13, prod.getWeightUnitMeasureCode());
            st.setInt(14, prod.getWeight());
            st.setInt(15, prod.getDaysToManufacture());
            st.setString(16, prod.getProductLine());
            st.setString(17, prod.getClase());
            st.setString(18, prod.getStyle());
            st.setInt(19, prod.getProductSubcategoryID());
            st.setInt(20, prod.getProductModelID());
            st.setDate(21, prod.getSellStartDate());
            st.setDate(22, prod.getSellEndDate());
            st.setDate(23, prod.getDiscontinuedDate());
            st.setString(24, prod.getRowguid());
            st.setDate(25, prod.getModifiedDate());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void eliminar(Product prod) throws Exception {
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("DELETE from Copia.copiaProduct where ProductID = ? ");
            st.setInt(1, prod.getProductID());
            st.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }

    @Override
    public List<Product> listar() throws Exception {
        List<Product> lista = null;
        try {
            this.conectar();
            PreparedStatement st = this.conexion.prepareStatement("select * from Copia.copiaProduct");

            lista = new ArrayList();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product prod = new Product();
                prod.setProductID(rs.getInt("ProductID"));
                prod.setName(rs.getString("Name"));
                prod.setProductNumber(rs.getString("ProductNumber"));
                prod.setMakeFlag(rs.getByte("MakeFlag"));
                prod.setFinishedGoodsFlag(rs.getByte("FinishedGoodsFlag"));
                prod.setColor(rs.getString("Color"));
                prod.setSafetyStockLevel(rs.getInt("SafetyStockLevel"));
                prod.setRecorderPoint(rs.getInt("RecorderPoint"));
                prod.setStandarCost(rs.getFloat("StandarCost"));
                prod.setListPrice(rs.getFloat("ListPrice"));
                prod.setSize(rs.getString("Size"));
                prod.setSizeUnitMeasureCode(rs.getString("SizeUnitMeasureCode"));
                prod.setWeightUnitMeasureCode(rs.getString("WeightUnitMeasureCode"));
                prod.setDaysToManufacture(rs.getInt("DaysToManufacture"));
                prod.setProductLine(rs.getString("ProductLine"));
                prod.setClase(rs.getString("Class"));
                prod.setStyle(rs.getString("Style"));
                prod.setProductSubcategoryID(rs.getInt("ProductSubcategoryID"));
                prod.setProductModelID(rs.getInt("ProductModelID"));
                prod.setSellStartDate(rs.getDate("SellStartDate"));
                prod.setSellEndDate(rs.getDate("SellEndDate"));
                prod.setDiscontinuedDate(rs.getDate("DiscontinuedDate"));
                prod.setRowguid(rs.getString("rowguid"));
                prod.setModifiedDate(rs.getDate("ModifiedDate"));

                lista.add(prod);
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.cerrar();
        }
        return lista;
    }
}

