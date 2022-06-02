use AdventureWorks2019
go

-- RECUPERAR
-- sp diseñado para consultar los registros que contiene la tabla SpecialOfferProduct el cual
-- se encuentra en el otro servidor de sql server.
create or alter procedure sp_RecuperarSpecialOfferProduct
as begin 
	
	BEGIN TRY
		BEGIN TRANSACTION
			select *
			from SERVIDOR2.SALES.[Sales].SpecialOfferProduct		
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH

end
go

exec sp_RecuperarSpecialOfferProduct
go

-- ACTUALIZAR
-- Falat este pero ya no se solicito