use AdventureWorks2019
go

-- RECUPERAR
create or alter procedure sp_RecuperarOrderDetail
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

exec sp_RecuperarOrderDetail


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


-- Validar existencia, cantidad stock.
-- Tambien aqui mismo deberia validarse si tiene o no algun tipo de oferta (?)
create or alter procedure sp_ValidarInserccionSalesOrderDetail
	@ProductID int, @solicitud_stock int
as begin 
	BEGIN TRY
		BEGIN TRANSACTION
		
			create table #tablaExistencia( existencia int )

			DECLARE @TSQL varchar(8000), @productNo VARCHAR(100)
			SELECT  @productNo = CONVERT(varchar(100), @ProductID)
			SELECT  @TSQL = 'select * from openquery(MYSQL,''select ProductID from AdventureWorks2019.product
							WHERE ProductID = ''''' + @productNo + ''''''')'
			
			insert into #tablaExistencia exec(@tsql)

			IF EXISTS ( select * from #tablaExistencia )
			BEGIN
				create table #tablaCantidad( cant int )

				Declare @nuevo_stock int
				SELECT  @productNo = CONVERT(varchar(100), @ProductID)
				SELECT  @TSQL = 'select * from openquery(MYSQL,''select SafetyStockLevel from AdventureWorks2019.product
								WHERE ProductID = ''''' + @productNo + ''''''')'
				insert into #tablaCantidad exec(@tsql)

				set @nuevo_stock = ( select * from #tablaCantidad ) - @solicitud_stock

				IF @nuevo_stock >= 0
				BEGIN
					-- Aqui se mandaria a llamar el sp que actualiza el stock en mysql.
					select 1 

				END
				ELSE
				BEGIN
					PRINT N'No hay la cantidad de stock del producto que solicita';
					select -1
				END
			END
			ELSE 
			BEGIN
				PRINT N'No existe el producto que solicita';
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

exec sp_ValidarInserccionSalesOrderDetail 1,11