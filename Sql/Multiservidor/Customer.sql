use AdventureWorks2019
go

-- RECUPERAR
-- sp diseñado para consultar los registros que contiene la tabla customer el cual
-- se encuentra en el otro servidor de sql server.
create or alter procedure sp_RecuperarCustomer
as begin 
	
	BEGIN TRY
		BEGIN TRANSACTION
			select *
			from SERVIDOR2.SALES.[Sales].[Customer]		
		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH

end
go

exec sp_RecuperarCustomer
go


-- ACTUALIZAR
-- En un principio se habia solicitado realizar la actualizacion de esta tabla, sin embargo
-- ya no se volvio a hacer mencion de ello.
-- Aun no se sabe si se utilizara y de ser el caso, no se sabe con certeza
-- si esta correcto asi como esta.
create or alter procedure sp_ActualizarCustomer
	@CustomerID int, @PersonID int
as begin 
	
	BEGIN TRY
		BEGIN TRANSACTION
			
			update
				SERVIDOR2.SALES.[Sales].[Customer]
			set  
				PersonID=@PersonID, ModifiedDate=GETDATE()
					where CustomerID=@CustomerID;

		COMMIT TRANSACTION
	END TRY 
	BEGIN CATCH   
		ROLLBACK TRANSACTION   
		RAISERROR ('No se pudo realizar la accion',16,1)  
	END CATCH

end
go

exec sp_ActualizarCustomer 1,15 
