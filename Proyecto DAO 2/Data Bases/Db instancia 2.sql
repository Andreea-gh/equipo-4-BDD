create database SALES
go

use SALES
go

CREATE SCHEMA [Sales]
go


select * into SALES.[Sales].[CountryRegionCurrency]
from [AdventureWorks2019].[Sales].[CountryRegionCurrency]
go

select * into SALES.[Sales].[CreditCard]
from [AdventureWorks2019].[Sales].[CreditCard]
go

select * into SALES.[Sales].[Currency]
from [AdventureWorks2019].[Sales].[Currency]
go

select * into SALES.[Sales].[CurrencyRate]
from [AdventureWorks2019].[Sales].[CurrencyRate]
go

select * into SALES.[Sales].[Customer]
from [AdventureWorks2019].[Sales].[Customer]
go

select * into SALES.[Sales].[PersonCreditCard]
from [AdventureWorks2019].[Sales].[PersonCreditCard]
go

select * into SALES.[Sales].[SalesOrderDetail]
from [AdventureWorks2019].[Sales].[SalesOrderDetail]
go

select * into SALES.[Sales].[SalesOrderHeader]
from [AdventureWorks2019].[Sales].[SalesOrderHeader]
go

select * into SALES.[Sales].[SalesOrderHeaderSalesReason]
from [AdventureWorks2019].[Sales].[SalesOrderHeaderSalesReason]
go

select * into SALES.[Sales].[SalesPerson]
from [AdventureWorks2019].[Sales].[SalesPerson]
go

select * into SALES.[Sales].[SalesPersonQuotaHistory]
from [AdventureWorks2019].[Sales].[SalesPersonQuotaHistory]
go

select * into SALES.[Sales].[SalesReason]
from [AdventureWorks2019].[Sales].[SalesReason]
go

select * into SALES.[Sales].[SalesTaxRate]
from [AdventureWorks2019].[Sales].[SalesTaxRate]
go

select * into SALES.[Sales].[SalesTerritory]
from [AdventureWorks2019].[Sales].[SalesTerritory]
go

select * into SALES.[Sales].[SalesTerritoryHistory]
from [AdventureWorks2019].[Sales].[SalesTerritoryHistory]
go

select * into SALES.[Sales].[ShoppingCartItem]
from [AdventureWorks2019].[Sales].[ShoppingCartItem]
go

select * into SALES.[Sales].[SpecialOffer]
from [AdventureWorks2019].[Sales].[SpecialOffer]
go

select * into SALES.[Sales].[SpecialOfferProduct]
from [AdventureWorks2019].[Sales].[SpecialOfferProduct]
go

-- El unico que no deja.
select * into SALES.[Sales].[Store]
from [AdventureWorks2019].[Sales].[Store]
go