use AdventureWorks2019
go

-- RECUPERAR
create or alter procedure sp_RecuperarSalesOrderDetail
as begin 
	
	BEGIN TRY
		BEGIN TRANSACTION
			select *
			from SERVIDOR2.SALES.[Sales].[SalesOrderDetail]	
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH

end
go

exec sp_RecuperarSalesOrderDetail
go

-- INSERTAR

-- SalesOrderID: es el id de venta (uno por cada grupo de productos vendidos).
-- orderdetailID: es autoincrementable
-- carrierTrackin: a que se refiere?? Todo el grupo de una venta tiene el mismo.
-- orderQty: cuantos productos se piden ([produccion].[Product] ()), para ello se debe checar el stock
-- productID: debe existir.
-- specialofferid: representa el tipo de oferta. Todos los productos tienen oferta? la respuesta 
	--es que no, los que tienen el 1 no tienen descuento. Pero hay unos que no estan en la tabla
	-- pe product 1, 2, 3, 4 (que significa?).
-- unitprice: debe traerse de product (campo Listprice)
-- unitpricediscoutn: se trae de special offer
-- linetotal: resultado de qty*unitprice*(1-unitdiscount)
-- rowguid: Se trae de algun lado o solo son caracteres random(?)

-- Otras tablas involucradas:
	-- SpecialOfferProduct: Tipo de ofertas
	-- SpecialOffer: Tipo de descuento con base al tipo de oferta.
	-- Product: Informacion de los productos: costo del producto (StandardCost), saber si 
		-- existe (mediante el id), saber si hay en existencia/stock (mediante campo 
		-- SafetyStockLevel).


-- Para saber que existe el producto
DECLARE @productID int
SELECT @productID = 5
IF EXISTS (select * from production.Product where ProductID = @ProductID )
    PRINT N' existe el producto que solicita';
	-- Actualizar el stock cuando tome productos.
ELSE 
    PRINT N'No existe el producto que solicita'; 

-- Consulta distribuida (con una variable).
DECLARE @TSQL varchar(8000), @NAME  VARCHAR(100), @aux int
SELECT  @aux = 316
SELECT  @NAME = CONVERT(varchar(100),@aux)
SELECT  @TSQL = 'select * from openquery(MYSQL,''select * from AdventureWorks2019.product
				WHERE ProductID = ''''' + @NAME + ''''''')'
EXEC (@TSQL)
go

-- Actualizar el stock
create or alter procedure sp_ActualizarProducto
	@ProductID int, @nuevo_stock int
as begin 
	BEGIN TRY
		BEGIN TRANSACTION

			update AdventureWorks2019.Production.[Product]
			set [SafetyStockLevel]=@nuevo_stock, ModifiedDate=getdate()
			where ProductID=@ProductID
			-- No se logro hacer la actualizacion distribuida (dos variables).

		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_ActualizarProducto 1,10
go

-- Validar existencia, cantidad stock.
-- Tambien aqui mismo deberia validarse si tiene o no algun tipo de oferta (?).
-- Por alguna extraña razon los openquery en la app no jalan, pero aqui en sql server si. NO BORRAR.
create or alter procedure sp_ValidarInserccionSalesOrderDetail
	@ProductID int, @solicitud_stock int
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
		
			--create table #tablaExistencia( existencia int )

			--DECLARE @TSQL varchar(8000), @productNo VARCHAR(100)
			--SELECT  @productNo = CONVERT(varchar(100), @ProductID)
			--SELECT  @TSQL = 'select * from openquery(MYSQL,''select ProductID from AdventureWorks2019.product
			--				WHERE ProductID = ''''' + @productNo + ''''''')'
			
			--insert into #tablaExistencia exec(@tsql)

			--IF EXISTS ( select * from #tablaExistencia )
			IF EXISTS ( select ProductID from production.Product
							WHERE ProductID = @ProductID )
			BEGIN
				--create table #tablaCantidad( cant int )

				--Declare @nuevo_stock int
				--SELECT  @productNo = CONVERT(varchar(100), @ProductID)
				--SELECT  @TSQL = 'select * from openquery(MYSQL,''select SafetyStockLevel from AdventureWorks2019.product
				--				WHERE ProductID = ''''' + @productNo + ''''''')'
				--insert into #tablaCantidad exec(@tsql)

				--set @nuevo_stock = ( select * from #tablaCantidad ) - @solicitud_stock
				Declare @nuevo_stock int
				set @nuevo_stock = ( select SafetyStockLevel from production.product
								WHERE ProductID =  @ProductID  ) - @solicitud_stock

				IF @nuevo_stock >= 0
				BEGIN
					-- Aqui se mandaria a llamar el sp que actualiza el stock en mysql.
					select 1 

				END
				ELSE
				BEGIN
					--PRINT N'No hay la cantidad de stock del producto que solicita';
					select -1
				END
			END
			ELSE 
			BEGIN
				--PRINT N'No existe el producto que solicita';
				select 0
			END

		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_ValidarInserccionSalesOrderDetail 1,9
go



-- Insertar 
-- porque no jala la insercion remota si, si jala fuera de el sp. 
-- Por alguna extraña razon los openquery en la app no jalan, pero aqui en sql server si. NO BORRAR.
create or alter procedure sp_BuscaSalesOrderDetail
	@SalesOrderID int, @ProductID int, @cantidad smallint
as begin 
	BEGIN TRY
		BEGIN TRANSACTION

			--Declare @tipo_oferta int, @precio money, @descuento smallmoney, @total numeric(38,6)
			--create table #tablaPrecio( preci money )

			--DECLARE @TSQL varchar(8000), @productNo VARCHAR(100)
			--SELECT  @productNo = CONVERT(varchar(100), @ProductID)
			--SELECT  @TSQL = 'select * from openquery(MYSQL,''select ListPrice from AdventureWorks2019.product
			--				WHERE ProductID = ''''' + @productNo + ''''''')'
			
			--insert into #tablaPrecio exec(@tsql)

			--set @precio = ( select * from #tablaPrecio )

			Declare @tipo_oferta int, @precio money, @descuento smallmoney, @total numeric(38,6)
			set @precio = ( select ListPrice from Production.Product
							where ProductID = @ProductID )
					
			IF EXISTS (select * from  SERVIDOR2.SALES.[Sales].[SpecialOfferProduct] where ProductID = @ProductID )
					BEGIN
					-- insertarlo pero aplicando su respectivo descuento.
					
					set @tipo_oferta = ( select top 1 SpecialOfferID from  SERVIDOR2.SALES.[Sales].[SpecialOfferProduct] 
																	  where ProductID = @ProductID )

    				set @descuento = ( select DiscountPct from  SERVIDOR2.SALES.[Sales].[SpecialOffer]
						     			where SpecialOfferID = @tipo_oferta )
									
					set @total = ( @cantidad*@precio*(1-@descuento) )
						
					SELECT @SalesOrderID, @cantidad, @ProductID, @tipo_oferta, @precio, @descuento, @total, NEWID(), getdate()
					--INSERT INTO SERVIDOR2.SALES.[Sales].[SalesOrderDetail] (SalesOrderID, [OrderQty], [ProductID],
					--	[SpecialOfferID], [UnitPrice], UnitPriceDiscount, [LineTotal], rowguid, ModifiedDate)
					--VALUES (@SalesOrderID, @cantidad, @ProductID, @tipo_oferta, @precio, @descuento, @total, NEWID(), getdate())	
					
					END
			ELSE
				    BEGIN
					--PRINT N'Esta producto no tiene algun tipo de oferta'; 
						-- insertarlo normal pero poniendo specialOfferID igual a 1
					set @tipo_oferta = 1
					set @descuento = (select DiscountPct from  SERVIDOR2.SALES.[Sales].[SpecialOffer]
						     			where SpecialOfferID = @tipo_oferta )			
					set @total = ( @cantidad*@precio*(1-@descuento) )
					
					SELECT @SalesOrderID, @cantidad, @ProductID, @tipo_oferta, @precio, @descuento, @total, NEWID(), getdate()


					--INSERT INTO SERVIDOR2.SALES.[Sales].[SalesOrderDetail] (SalesOrderID, [OrderQty], [ProductID],
					--	[SpecialOfferID], [UnitPrice], UnitPriceDiscount, [LineTotal], rowguid, ModifiedDate)
					--VALUES (@SalesOrderID, @cantidad, @ProductID, @tipo_oferta, @precio, @descuento, @total, NEWID(), getdate())
					END
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH
end
go

exec sp_BuscaSalesOrderDetail 1920, 517, 10

-- insercion manual
INSERT INTO SERVIDOR2.SALES.[Sales].[SalesOrderDetail] (SalesOrderID, [OrderQty], [ProductID],
						[SpecialOfferID], [UnitPrice], UnitPriceDiscount, [LineTotal], rowguid, ModifiedDate)
VALUES (1811, 1, 797, 1, 45, 0.00, 45, NEWID(), getdate());	


select *
from SERVIDOR2.SALES.[Sales].[SalesOrderDetail]
where SalesOrderID = 1740
-- ACTUALIZAR
-- Falata este pero ya no se solicito


-- ELIMINACION

