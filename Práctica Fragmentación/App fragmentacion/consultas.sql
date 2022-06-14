-- Listar al empleado que atendio mas ordenes
SELECT SalesPersonID, COUNT(Sales.SalesOrderHeader.TerritoryID) as numeroOrdenesAtendidas
FROM Sales.SalesOrderHeader INNER JOIN Sales.SalesPerson
ON Sales.SalesOrderHeader.SalesPersonID = Sales.SalesPerson.BusinessEntityID
GROUP BY SalesPersonID ORDER BY COUNT(*) DESC

CREATE OR ALTER PROCEDURE sp_listarOrdenesEmpleados
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
	SELECT SalesPersonID, COUNT(Sales.SalesOrderHeader.TerritoryID) as numeroOrdenesAtendidas
	FROM Sales.SalesOrderHeader INNER JOIN Sales.SalesPerson
	ON Sales.SalesOrderHeader.SalesPersonID = Sales.SalesPerson.BusinessEntityID
	GROUP BY SalesPersonID ORDER BY COUNT(*) DESC
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_listarOrdenesEmpleados
go

-- Buscar cuantas ordenes atendio un cierto empleado
SELECT SalesPersonID, COUNT(Sales.SalesOrderHeader.TerritoryID) as numeroOrdenesAtendidas
FROM Sales.SalesOrderHeader INNER JOIN Sales.SalesPerson
ON Sales.SalesOrderHeader.SalesPersonID = Sales.SalesPerson.BusinessEntityID
WHERE SalesPersonID = 278
GROUP BY SalesPersonID ORDER BY COUNT(*) DESC

CREATE OR ALTER PROCEDURE sp_listarOrdenesEmpleadosID @SalesPersonID int
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
    SELECT SalesPersonID, COUNT(Sales.SalesOrderHeader.TerritoryID) as numeroOrdenesAtendidas
    FROM Sales.SalesOrderHeader INNER JOIN Sales.SalesPerson
    ON Sales.SalesOrderHeader.SalesPersonID = Sales.SalesPerson.BusinessEntityID
    WHERE SalesPersonID = @SalesPersonID
    GROUP BY SalesPersonID ORDER BY COUNT(*) DESC
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_listarOrdenesEmpleadosID 277
go


-- Listar los datos del cliente con más ordenes solicitadas en la región "North America"
SELECT * FROM OPENQUERY ([192.168.56.1],'SELECT TOP 1 NorthAmerica.Sales.Customer.CustomerID, COUNT(NorthAmerica.sales.SalesOrderHeader.SalesOrderID) AS NumberOfOrders
FROM NorthAmerica.Sales.SalesOrderHeader INNER JOIN NorthAmerica.Sales.Customer 
ON NorthAmerica.Sales.SalesOrderHeader.CustomerID = NorthAmerica.Sales.Customer.CustomerID 
WHERE NorthAmerica.Sales.SalesOrderHeader.TerritoryID IN (1,2,3,4,5,6)
GROUP BY NorthAmerica.Sales.Customer.CustomerID ORDER BY NumberOfOrders DESC')

go
CREATE OR ALTER PROCEDURE sp_listarClienteNorthAmerica
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
	SELECT * FROM OPENQUERY ([192.168.56.1],'SELECT TOP 1 NorthAmerica.Sales.Customer.CustomerID, COUNT(NorthAmerica.sales.SalesOrderHeader.SalesOrderID) AS NumberOfOrders
	FROM NorthAmerica.Sales.SalesOrderHeader INNER JOIN NorthAmerica.Sales.Customer 
	ON NorthAmerica.Sales.SalesOrderHeader.CustomerID = NorthAmerica.Sales.Customer.CustomerID 
	WHERE NorthAmerica.Sales.SalesOrderHeader.TerritoryID IN (1,2,3,4,5,6)
	GROUP BY NorthAmerica.Sales.Customer.CustomerID ORDER BY NumberOfOrders DESC')
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_listarClienteNorthAmerica
go


-- 8.  Listar los productos que no estén disponibles a la venta 
SELECT * FROM Production.Product
WHERE SellEndDate is not NULL


go
CREATE OR ALTER PROCEDURE sp_productosNoDisponibles
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
	SELECT * FROM Production.Product
	WHERE SellEndDate is not NULL
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_productosNoDisponibles