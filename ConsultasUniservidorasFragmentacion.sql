use AdventureWorks2019
go

-- Tipos de idTerritory por region

select TerritoryID
from Sales.SalesTerritory
where [Group] = 'North America'

select TerritoryID
from Sales.SalesTerritory
where [Group] = 'Europe'

select TerritoryID
from Sales.SalesTerritory
where [Group] = 'Pacific'

-- Cantidad de customer por region

-- Fragmento 1.
select COUNT(*) as Cantidad_Customer
from Sales.Customer
where TerritoryID in ( select TerritoryID
						from Sales.SalesTerritory
						where [Group] = 'North America' ) --10428

-- Fragmento 2. Como La region Europe y Pacific hay muy pocos registros comparado con la region NA, se juntan
--		estas dos regiones para formar el fragmento 2.
select COUNT(*) as Cantidad_Customer
from Sales.Customer
where TerritoryID in ( select TerritoryID
						from Sales.SalesTerritory
						where [Group] = 'Europe' ) -- 5727

select COUNT(*) as Cantidad_Customer
from Sales.Customer
where TerritoryID in ( select TerritoryID
						from Sales.SalesTerritory
						where [Group] = 'Pacific' ) -- 3665

-- Cantidad de orderHeader (tickets) por region

-- Sucede lo mismo en la tabla OrderHeader
-- Fragmento 1.
select COUNT(*) as Cantidad_Customer
from Sales.SalesOrderHeader
where TerritoryID in ( select TerritoryID
						from Sales.SalesTerritory
						where [Group] = 'North America' ) -- 16108

-- Fragmento 2.
select COUNT(*) as Cantidad_Customer
from Sales.SalesOrderHeader
where TerritoryID in ( select TerritoryID
						from Sales.SalesTerritory
						where [Group] = 'Europe' ) -- 8514

select COUNT(*) as Cantidad_Customer
from Sales.SalesOrderHeader
where TerritoryID in ( select TerritoryID
						from Sales.SalesTerritory
						where [Group] = 'Pacific' ) -- 6843


----------------------------------------------------------

-- Data Bases and Schemas
Create database NorthAmerica
go

use NorthAmerica
go

CREATE SCHEMA sales 
go 

Create database EuropePacific
go

use EuropePacific
go

CREATE SCHEMA sales 
go 

-- Fragmentacion

-- Fragmento 1.
select * into NorthAmerica.sales.Customer
from (  select *
		from AdventureWorks2019.sales.Customer
		where TerritoryID in ( select TerritoryID
								from AdventureWorks2019.Sales.SalesTerritory
								where [Group] = 'North America' ) ) as aux


select * into NorthAmerica.sales.SalesOrderHeader
from (  select *
		from AdventureWorks2019.sales.SalesOrderHeader
		where TerritoryID in ( select TerritoryID
								from AdventureWorks2019.Sales.SalesTerritory
								where [Group] = 'North America' ) ) as aux


-- Fragmento 2.

select * into EuropePacific.sales.Customer
from (  ((select *
		from AdventureWorks2019.sales.Customer
		where TerritoryID in ( select TerritoryID
								from AdventureWorks2019.Sales.SalesTerritory
								where [Group] = 'Europe' ) ) )
		union all
		((select *
		from AdventureWorks2019.sales.Customer
		where TerritoryID in ( select TerritoryID
								from AdventureWorks2019.Sales.SalesTerritory
								where [Group] = 'Pacific' ) ) )   ) as aux
		

select * into EuropePacific.sales.SalesOrderHeader
from (  ((select *
		from AdventureWorks2019.sales.SalesOrderHeader
		where TerritoryID in ( select TerritoryID
								from AdventureWorks2019.Sales.SalesTerritory
								where [Group] = 'Europe' ) ) )
		union all
		((select *
		from AdventureWorks2019.sales.SalesOrderHeader
		where TerritoryID in ( select TerritoryID
								from AdventureWorks2019.Sales.SalesTerritory
								where [Group] = 'Pacific' ) ) )   ) as aux
