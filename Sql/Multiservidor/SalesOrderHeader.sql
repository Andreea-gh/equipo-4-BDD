use AdventureWorks2019
go

-- RECUPERACION
create or alter procedure sp_RecuperarSalesOrderHeader
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
			select * 
			from SERVIDOR2.SALES.[Sales].[SalesOrderHeader]
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end

exec sp_RecuperarSalesOrderHeader 


-- INSERTAR

-- [SalesOrderID]: Es el numero de orden de una venta en salesOrderDetail.
-- [RevisionNumber]: Se trae de la tabla PurcharseOrderHeader, pero hay que pasar primero por 
	-- Purcharsing.ShipMethod.
-- [OrderDate]: Se trae de la tabla PurcharseOrderHeader, pero hay que pasar primero por 
	-- ShipMethod.
-- [DueDate]: Se trae de la tabla PurcharseOrderDetail, pero hay que pasar primero por 
	-- PurcharseOrderHeader y ShipMethod.
-- [ShipDate]: Se trae de la tabla PurcharseOrderHeader, pero hay que pasar primero por 
	-- ShipMethod.
-- [Status]: Se trae de la tabla PurcharseOrderHeader, pero hay que pasar primero por 
	-- ShipMethod.
-- [OnlineOrderFlag]: Pedirla al usuario.
-- [SalesOrderNumber]: Es el mismo valor que SalesOrderId pero con el prefijo: SO.
-- [PurchaseOrderNumber]: Dejarla nula.
-- [AccountNumber]: Dejarlo nulo.
-- [CustomerID]: Se solicita al usuario. Se busca en la tabla customer.
-- [SalesPersonID]: Dejarlo nulo.
-- [TerritoryID]: Se busca en la tabla customer.
-- [BillToAddressID]: Ingresar uno aleatorio.
--[ShipToAddressID]: El mismo que el de arriba.
--[ShipMethodID]: Se trae de la tabla shipMethod.
--[CreditCardID]: Se trae de la tabla Sales.credtCard, pero hay que pasar por Sales.customer, 
	--Person.person, sales.PersonCreditCard y sales.creditcard.

	@CreditCardID int
	set @CreditCardID = ( select *
						  from SERVIDOR2.sales.sales.creditcard )
						  select *
						  from SERVIDOR2.sales.sales.PersonCreditCard

						  select *
						  from Person.Person

						  select *
						  from SERVIDOR2.sales.sales.customer
--[CreditCardApprovalCode]:
--[CurrencyRateID]: Dejarlo nulo.
--[SubTotal]: Es la suma de todos los line total de una orden. 
--[TaxAmt]: 
--[Freight]: 
--[TotalDue]: Es el resultado de Subtotal + TaxAmt + Freight.
--[Comment]: Dejarlo nulo.
--[rowguid]: NEWID()
--[ModifiedDate]: GETDATE()

select * 
from SERVIDOR2.SALES.[Sales].[SalesOrderHeader]
where SalesOrderID = 48092

select * 
from [Purchasing].[ShipMethod]

select *
from SERVIDOR2.SALES.[Sales].[SalesOrderHeaderSalesReason]
where SalesOrderID = 48092


select * 
from SERVIDOR2.SALES.[Sales].customer

select * 
from Person.Person
where BusinessEntityID = 16162

select * 
from SERVIDOR2.SALES.[Sales].[SalesTerritory]

select * 
from SERVIDOR2.SALES.[Sales].[CreditCard]
where CreditCardID = 12922

select * 
from SERVIDOR2.SALES.[Sales].[PersonCreditCard]
where CreditCardID = 12922 -- 16162



create or alter procedure sp_BuscarSalesOrderHeader
	@salesOrderID int, @bandera int, @customerId int, @BillToAddressID int
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
			declare @revision int, @numeroOrden varchar(40), @TerritoryID int,
					@ShipToAddressID int

			set @revision = ( select *
							from [Purchasing].[PurchaseOrderHeader]
							where ShipMethodID = ( select *
												from purchasing.shipMethod ))

			set @numeroOrden = concat('SO', @salesOrderID)

			set @TerritoryID = ( select TerritoryID 
								from SERVIDOR2.SALES.sales.Customer
								where CustomerID = @customerId )

			set @ShipToAddressID = @BillToAddressID
			
			
			
			
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end

select concat('CONCAT',5000)

DECLARE @vicio varchar(1000)
set @vicio = concat('CONCAT',5000)
print @vicio