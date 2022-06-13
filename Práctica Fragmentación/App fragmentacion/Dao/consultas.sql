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
