

select *
from AdventureWorks2019.sales.SalesOrderDetail -- Total = 121,317

--select so.SalesOrderID, so.SalesOrderDetailID, sh.SalesOrderID
--from AdventureWorks2019.sales.SalesOrderDetail so
--inner join AdventureWorks2019.sales.SalesOrderHeader sh
--on sh.SalesOrderID = so.SalesOrderID 
--order by 2 

select * into NorthAmerica.sales.SalesOrderDetail
from ( select so.*
		from AdventureWorks2019.sales.SalesOrderDetail so
		inner join AdventureWorks2019.sales.SalesOrderHeader sh
		on sh.SalesOrderID = so.SalesOrderID 
		where sh.TerritoryID in ( select TerritoryID
								  from AdventureWorks2019.Sales.SalesTerritory
								  where [Group] = 'North America' ) ) as aux -- 79,217


select * into EuropePacific.sales.SalesOrderDetail
from ( (( select so.*
			from AdventureWorks2019.sales.SalesOrderDetail so
			inner join AdventureWorks2019.sales.SalesOrderHeader sh
			on sh.SalesOrderID = so.SalesOrderID 
			where sh.TerritoryID in ( select TerritoryID
										from AdventureWorks2019.Sales.SalesTerritory
										where [Group] = 'Europe' ) ))  --27,042
		union all
		(( select so.*
		from AdventureWorks2019.sales.SalesOrderDetail so
		inner join AdventureWorks2019.sales.SalesOrderHeader sh
		on sh.SalesOrderID = so.SalesOrderID 
		where sh.TerritoryID in ( select TerritoryID
								  from AdventureWorks2019.Sales.SalesTerritory
								  where [Group] = 'Pacific' ) )) ) as aux --15,058

