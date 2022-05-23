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
go

exec sp_RecuperarSalesOrderHeader 
go

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
	-- ShipMethod. Aunque todos valen 5.
-- [OnlineOrderFlag]: Pedirla al usuario.
-- [SalesOrderNumber]: Es el mismo valor que SalesOrderId pero con el prefijo: SO.
-- [CustomerID]: Se solicita al usuario. Se busca en la tabla customer.
-- [TerritoryID]: Se busca en la tabla customer.
-- [BillToAddressID]: Ingresar uno aleatorio.
--[ShipToAddressID]: El mismo que el de arriba.
--[ShipMethodID]: Se trae de la tabla shipMethod. Pero la mayoria son 1.
--[CreditCardID]: Se trae de la tabla Sales.credtCard, pero hay que pasar por
	--Person.person, sales.PersonCreditCard.				  
--[SubTotal]: Es la suma de todos los line total de una orden. 
--[TaxAmt]: Especie de impuesto, pero de donde se trae(???).Ponerlo aleatorio en el
	--rango de 3 - 80000
--[Freight]: Especie de impuesto, pero de donde se trae(???).Ponerlo aleatorio en el
	--rango de 1 - 20000
--[TotalDue]: Es el resultado de Subtotal + TaxAmt + Freight.
--[rowguid]: NEWID()
--[ModifiedDate]: GETDATE()

create or alter procedure sp_BuscSalesOrderHeader
	@salesOrderID int, @bandera int, @customerId int, @BillToAddressID int, 
		@TaxAmt money, @Freight money
as begin 

			declare @revision int, @numeroOrden varchar(40), @TerritoryID int,
					@ShipToAddressID int, @CreditCardID int, @subtotal money,
					@Total money, @orderDate datetime, @duedate datetime, @shipDate datetime,
					@status int, @shipMethodId int
			
			set @revision = 8
			
			set @orderDate = ( DATEADD(DAY, ABS(CHECKSUM(NEWID()) % 3650), '2000-01-01') )
			
			set @duedate = ( DATEADD(DAY, ABS(CHECKSUM(NEWID()) % 3650), '2000-01-01') )
			
			set @shipDate = ( DATEADD(DAY, ABS(CHECKSUM(NEWID()) % 3650), '2000-01-01') )

			set @status = 5
	
			set @numeroOrden = concat('SO', @salesOrderID)

			set @TerritoryID = ( select TerritoryID 
								from SERVIDOR2.SALES.sales.Customer
								where CustomerID = @customerId )

			set @ShipToAddressID = @BillToAddressID

			set @shipMethodId = 1

			set @CreditCardID = ( select TOP 1 CreditCardID
								from SERVIDOR2.sales.sales.PersonCreditCard
								ORDER BY NEWID() )
								
			set @subtotal = ( select sum(LineTotal)
					from SERVIDOR2.SALES.sales.salesorderdetail
					where SalesOrderID = @salesOrderID )

			set @Total = ( @Subtotal + @TaxAmt + @Freight )
			
			SELECT  @Revision, @OrderDate, @DueDate, @ShipDate, @Status,
					 @bandera, @numeroOrden, @CustomerID, @TerritoryID, 
					 @BillToAddressID, @ShipToAddressID, @ShipMethodID, @CreditCardID, 
					 @SubTotal, @TaxAmt, @Freight, @Total, NEWID(), GETDATE()

end
go

exec sp_BuscarSalesOrderHeader 75084, 1, 30006, 24019, 20, 40  
go 

-- Insercion manual
INSERT INTO SERVIDOR2.SALES.sales.salesOrderHeader
	(RevisionNumber, OrderDate, DueDate, ShipDate, [Status],
	 OnlineOrderFlag, SalesOrderNumber, CustomerID, TerritoryID, 
	 BillToAddressID, ShipToAddressID, ShipMethodID, CreditCardID, 
	 SubTotal, TaxAmt, Freight, TotalDue, rowguid, ModifiedDate)
VALUES (8, '2012-05-07 00:00:00.000', '2012-09-07 00:00:00.000', 
		'2012-10-07 00:00:00.000', 5, 1, 'so49797', 30060, 2, 798,
		798, 5, 15155, 1000, 40, 30, 1070, NEWID(), GETDATE())

-- Para ver unos datos para la insercion
select * 
from SERVIDOR2.SALES.[Sales].[SalesOrderHeader]
order by ModifiedDate desc

select * 
from SERVIDOR2.SALES.[Sales].[SalesOrderDetail]
order by 

select *
from sales.customer

delete from SERVIDOR2.SALES.[Sales].[SalesOrderHeader]
where SalesOrderID = 75128