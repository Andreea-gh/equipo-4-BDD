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

-- 5. Listar las ofertas que tienen los productos de la categoría “Bikes”
go
CREATE OR ALTER PROCEDURE sp_ofertasProductoBikes
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
	SELECT * FROM Sales.SpecialOffer WHERE SpecialOfferID IN (
	SELECT SpecialOfferID FROM Sales.SpecialOfferProduct SOP 
	INNER JOIN Production.Product P ON SOP.ProductID = P.ProductID
	INNER JOIN Production.ProductSubcategory PS ON P.ProductSubcategoryID = PS.ProductSubcategoryID
	INNER JOIN Production.ProductCategory PC ON PS.ProductCategoryID = PC.ProductCategoryID WHERE
	[PC].[Name] = 'Bikes');
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_ofertasProductoBikes

SELECT * FROM Sales.SpecialOffer WHERE SpecialOfferID IN (
SELECT SpecialOfferID FROM Sales.SpecialOfferProduct SOP 
INNER JOIN Production.Product P ON SOP.ProductID = P.ProductID
INNER JOIN Production.ProductSubcategory PS ON P.ProductSubcategoryID = PS.ProductSubcategoryID
INNER JOIN Production.ProductCategory PC ON PS.ProductCategoryID = PC.ProductCategoryID WHERE
[PC].[Name] = 'Bikes');
-- 6. Listar los 3 productos menos solicitados en la región “Pacific”go
CREATE OR ALTER PROCEDURE sp_productoMasSolicitadoEuropa
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
		SELECT * FROM OPENQUERY ([192.168.56.1], 'select top 3 so.ProductID, COUNT(*) as Cantidad_Productos
		from EuropePacific.sales.SalesOrderDetail so
		inner join EuropePacific.sales.SalesOrderHeader sh
		on sh.SalesOrderID = so.SalesOrderID 
		where sh.TerritoryID = 9 
		group by so.ProductID
		order by 2 asc
		')
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
goexec sp_productoMasSolicitadoEuropa-- 7.  Actualizar la subcategoría de los productos con productId del 1 al 4 a la subcategoría valida 
-- para el tipo de producto gocreate or alter procedure sp_ActualizarSubcategoria(
	@productId int,
	@subcategoriaID int
)
as
	IF exists( select *	from AdventureWorks2019.Production.Product where ProductID = @productId )
	BEGIN
		IF exists( select * from AdventureWorks2019.Production.ProductSubcategory where ProductSubcategoryID = @subcategoriaID )
			update AdventureWorks2019.Production.Product
			set [ProductSubcategoryID] = @subcategoriaID, ModifiedDate = GETDATE()
			where ProductID = @productId
		ELSE
			SELECT -1 as resultado
	END
	ELSE
		SELECT 0 as resultado
-- 9. Listar los clientes del territorio 1 y 4 que no tengan asociado un valor en personId
