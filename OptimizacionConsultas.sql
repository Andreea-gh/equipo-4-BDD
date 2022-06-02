-- La db no tiene llaves y por tanto tampoco archivos indices
-- ENTIDAD_NAC: entidad de nacimiento.
-- ENTIDAD_RES: entidad de residencia.
-- ENTIDAD_UM: Entdidad de unidad medica. 
-- clasificacion final
	-- 1 a 3: Casos confirmados.
	-- 6: Casos sospechoso.
	-- 7: Caso negativo.
-- Los datos se interpretan con base al excel.
-- FECHA_DEF sis tiene un valor distinto a 9999-99-99 ya fallecio.
-- neumonia: si 1 si, si 2 no.

use datosCovid
go

/*
Listado de consultas a programar para anlizar planes de ejecución

1. Listar los casos potivos por entidad de residencia
2. Listar los casos sospechosos por entidad
3. Listar el top 5 de municipios por entidad con el mayor número de casos reportados, 
   indicando casos sospechosos y casos confirmados.
4. Determinar el municipio con el mayor número de defunciones en casos confirmados.
5. Determinar por entidad, si de casos sospechosos hay defunciones reportadas asociadas a neumonia.
6. Listar por entidad el total de casos sospechosos, casos confirmados, total de defunciones en los meses de marzo a agosto 2020 y de diciembre 2020 a mayo 2021.
7. Listar los 5 municipios con el mayor número de casos confirmados en niños menos de 13 años con alguna comorbilidad reportada y cuantos de esos casos fallecieron.
8. Determinar si en el año 2020 hay una mayor cantidad de defunciones menores de edad que en el año 2021 y 2022.
9. Determinar si en el año 2021 hay un pocentaje mayor al 60 de casos reportados que son confirmados por estudios de laboratorio en comparación al año 2020.
10. Determinar en que rango de edad: menor de edad, 19 a 40, 40 a 60 o mayor de 60 hay mas casos reportados que se hayan recuperado. 
*/

-- 1 Listar los casos potivos por entidad de residencia

-- solución 1
-- Debido a que un paciente se considera como caso positivo en covid si este tiene un valor
-- ya sea 1, 2 o 3 en el campo clasificacion final, es que se busca a los registros en dicho rango en dicho campo.
select *
from dbo.datoscovid
where CLASIFICACION_FINAL between 1 and 3
order by ENTIDAD_RES

-- solución 2
-- Se realizo de esta otra manera para contabilizar cuantos casos positivos hay por cada entidad.
select ENTIDAD_RES, count(*) total_confirmado
from dbo.datoscovid
where CLASIFICACION_FINAL between 1 and 3
group by ENTIDAD_RES
order by ENTIDAD_RES

-- 2 Listar los casos sospechosos por entidad

-- Solucion 1
-- Debido a que un paciente se considera como caso sospechoso en covid si este tiene un valor
-- de 6 en el campo clasificacion final, es que se busca a los registros en dicho valor en dicho campo.
-- Como no se entiende bien sobre cual entidad se refiere el enunciado, se seleccionan las entidades mas importantes, 
-- siendo la entidad de unidad medica y entidad residencial.
select ENTIDAD_UM, ENTIDAD_RES, count(*) total_sospechosos
from dbo.datoscovid
where CLASIFICACION_FINAL = 6
group by ENTIDAD_UM, ENTIDAD_RES
order by ENTIDAD_UM 

-- Solucion 2
-- Como muy probablemente la entidad residencial sea la mas importante, es que en esta otra solucion unicamnte se selecciona
-- este campo y se contabiliza cuantos casos sospeshocos hay por cada entidad residencial.
select ENTIDAD_RES, count(*) total_sospechosos
from dbo.datoscovid
where CLASIFICACION_FINAL = 6
group by ENTIDAD_RES



-- 3 Listar el top 5 de municipios por entidad con el mayor número de casos reportados, 
   --indicando casos sospechosos y casos confirmados.

-- solución 1
-- Un caso se considera como reportado si es que este se encuentra en la tabla.
-- Debido a que un paciente se considera como caso sospechoso en covid si este tiene un valor
-- de 6 en el campo clasificacion final, es que se busca a los registros en dicho valor en dicho campo.
-- Debido a que un paciente se considera como caso positivo en covid si este tiene un valor
-- ya sea 1, 2 o 3 en el campo clasificacion final, es que se busca a los registros en dicho rango en dicho campo.
-- Como no se entiende bien sobre cual entidad se refiere el enunciado, se seleccionan las entidades mas importantes, 
-- siendo la entidad de unidad medica y entidad residencial. 
Select ENTIDAD_RES, MUNICIPIO_RES, count(*) as reportados, count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL 
                                                                  when 2 then CLASIFICACION_FINAL
																  when 3 then CLASIFICACION_FINAL
                                          end) as confirmado,
       count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) as sospechoso
from dbo.datoscovid
group by ENTIDAD_RES, MUNICIPIO_RES
order by ENTIDAD_RES, reportados desc

-- solución 2
-- Esta solucion se realizo con ideas totalmente iguales a las anteriores pero utilizando inner join.
select cc.ENTIDAD_RES, cc.MUNICIPIO_RES, cc.confirmado, cs.sospechoso
from (select ENTIDAD_RES, MUNICIPIO_RES, count(*) as sospechoso 
      from dbo.datoscovid where CLASIFICACION_FINAL = 6
	  group by ENTIDAD_RES, MUNICIPIO_RES
      ) cs
inner join
(select ENTIDAD_RES, MUNICIPIO_RES, count (*) as confirmado 
 from dbo.datoscovid where CLASIFICACION_FINAL between 1 and 3
 group by ENTIDAD_RES, MUNICIPIO_RES) cc 
on cc.ENTIDAD_RES =  cs.ENTIDAD_RES and cs.MUNICIPIO_RES = cc.MUNICIPIO_RES
order by cc.ENTIDAD_RES, cc.MUNICIPIO_RES


-- 4 Determinar el municipio con el mayor número de defunciones en casos confirmados.

-- Solucion 1
-- Debido a que un paciente se considera como caso positivo en covid si este tiene un valor
-- ya sea 1, 2 o 3 en el campo clasificacion final, es que se busca a los registros en dicho rango en dicho campo.
-- Cuando un paciente muere, se coloca la fecha de deceso en el campo fecha_def pero si esta aun se encuentra vivo, es que en
-- este campo se encuentra el valor de 9999-99-99, entonces cuando un paciente muere tiene un valor distinto a 9999-99-99.
-- Primero se contabilizaron cuantos muertos hay en casos positivos al covid por cada municipio residencial.
-- De este resultado se selecciona la cantidad mayor.
-- Finalmente se vuelve a contabilizar cuantos muertos hay en casos positivos al covid por cada municipio residencial pero 
-- condicionando que solo se traiga la mayor cantidad.
select MUNICIPIO_RES, COUNT(*) as numero_Defunciones
from dbo.datoscovid
where CLASIFICACION_FINAL between 1 and 3 and FECHA_DEF != '9999-99-99'
group by MUNICIPIO_RES 
having COUNT(*) = ( select max(numero_Defunciones)
					from (
						select MUNICIPIO_RES, COUNT(*) as numero_Defunciones
						from dbo.datoscovid
						where CLASIFICACION_FINAL between 1 and 3 and FECHA_DEF != '9999-99-99'
						group by MUNICIPIO_RES ) as aux )


-- Solucion 2


-- 5  Determinar por entidad, si de casos sospechosos hay defunciones reportadas asociadas a neumonia.

-- Solucion 1
-- Debido a que un paciente se considera como caso sospechoso en covid si este tiene un valor
-- de 6 en el campo clasificacion final, es que se busca a los registros en dicho valor en dicho campo.
-- Cuando un paciente muere, se coloca la fecha de deceso en el campo fecha_def pero si esta aun se encuentra vivo, es que en
-- este campo se encuentra el valor de 9999-99-99, entonces cuando un paciente muere tiene un valor distinto a 9999-99-99.
-- Una paciente se considera con neumonia si tiene el valor 1 en ese campo.
-- Como no se entiende bien sobre cual entidad se refiere el enunciado, se seleccionan las entidades mas importantes, 
-- siendo la entidad de unidad medica y entidad residencial. De ahi se contabiliza cuantos registros hay por cada entidad.
select ENTIDAD_UM, ENTIDAD_RES, count(*)
from dbo.datoscovid
where CLASIFICACION_FINAL = 6 and FECHA_DEF != '9999-99-99' and NEUMONIA = 1
group by ENTIDAD_UM, ENTIDAD_RES

-- Solucion 2
-- Como muy probablemente la entidad residencial sea la mas importante, es que en esta otra solucion unicamnte se selecciona
-- este campo cuantos registros hay por cada entidad residencial.
select ENTIDAD_RES, count(*)
from dbo.datoscovid
where CLASIFICACION_FINAL = 6 and FECHA_DEF != '9999-99-99' and NEUMONIA = 1
group by ENTIDAD_RES


-- 6. Listar por entidad el total de casos sospechosos, casos confirmados, total de defunciones en los meses de marzo a agosto 2020 y de 
	--diciembre 2020 a mayo 2021.

-- Solucion 1
-- Debido a que un paciente se considera como caso sospechoso en covid si este tiene un valor
-- de 6 en el campo clasificacion final, es que se busca a los registros en dicho valor en dicho campo.
-- Debido a que un paciente se considera como caso positivo en covid si este tiene un valor
-- ya sea 1, 2 o 3 en el campo clasificacion final, es que se busca a los registros en dicho rango en dicho campo.
-- Cuando un paciente muere, se coloca la fecha de deceso en el campo fecha_def pero si esta aun se encuentra vivo, es que en
-- este campo se encuentra el valor de 9999-99-99, entonces cuando un paciente muere tiene un valor distinto a 9999-99-99.
-- Se buscan estas condiciones pero en las fechas indicadas.
-- Finalmente se selecciona cada entidad residencial para mostrar el resultado.
Select ENTIDAD_RES, count(case CLASIFICACION_FINAL when 6 then CLASIFICACION_FINAL end) as sospechoso, 
	count(*) as reportados, count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL 
                                                           when 2 then CLASIFICACION_FINAL
		               									   when 3 then CLASIFICACION_FINAL
    end) as confirmado, COUNT( case when  FECHA_DEF != '9999-99-99' then FECHA_DEF end ) as total_Defunciones      
from dbo.datoscovid
where FECHA_INGRESO between '2020-03-01' and '2020-08-31' or FECHA_INGRESO between '2021-05-01' and '2021-12-31' 
group by ENTIDAD_RES
order by ENTIDAD_RES

-- Solucion 2


-- 7. Listar los 5 municipios con el mayor número de casos confirmados en niños menos de 13 años con alguna comorbilidad reportada y 
	--cuantos de esos casos fallecieron.

-- Solucion 1
-- Debido a que un paciente se considera como caso positivo en covid si este tiene un valor
-- ya sea 1, 2 o 3 en el campo clasificacion final, es que se busca a los registros en dicho rango en dicho campo.
-- Cuando un paciente muere, se coloca la fecha de deceso en el campo fecha_def pero si esta aun se encuentra vivo, es que en
-- este campo se encuentra el valor de 9999-99-99, entonces cuando un paciente muere tiene un valor distinto a 9999-99-99.
-- Se buscan pacientes con una edad menor a 13.
-- Comorbilidad: Cuando una persona tiene 2 o mas enfermedades. En este caso se consideraron las enfermedades: neumonia, diabetes,
-- hipertension y asma.
-- Se contabilizo cuantos pacientes hay por municipio residencial que sen positivos en covid y cumplan con las condiciones 
-- anteriormente mencionadas.
-- Se ordenaron de mayor a menor el numero de casos confirmados por municipio residencial.
-- Finalmente se selecciono mostrar los primeros 5 registros.
Select top 5 MUNICIPIO_RES, count(case CLASIFICACION_FINAL when 1 then CLASIFICACION_FINAL 
                                                     when 2 then CLASIFICACION_FINAL
						      						 when 3 then CLASIFICACION_FINAL
                                          end) as confirmado, 
					  COUNT( case when  FECHA_DEF != '9999-99-99' then FECHA_DEF end ) as total_Defunciones  
from dbo.datoscovid
where EDAD < 13  and ID_REGISTRO in ( select ID_REGISTRO
									  from dbo.datoscovid
									  where EDAD < 13 and ( (NEUMONIA = 1 and DIABETES = 1) or (NEUMONIA = 1 and ASMA = 1) 
														or (NEUMONIA = 1 and HIPERTENSION = 1) or (DIABETES = 1 and ASMA = 1) 
														or (DIABETES = 1 and HIPERTENSION = 1) or (ASMA = 1 and HIPERTENSION = 1)) )
group by MUNICIPIO_RES
order by confirmado desc

-- Solucion 2


-- 8. Determinar si en el año 2020 hay una mayor cantidad de defunciones menores de edad que en el año 2021 y 2022.

-- Solucion 1
-- Cuando un paciente muere, se coloca la fecha de deceso en el campo fecha_def pero si esta aun se encuentra vivo, es que en
-- este campo se encuentra el valor de 9999-99-99, entonces cuando un paciente muere tiene un valor distinto a 9999-99-99.
-- Se busco el numero de defunciones en el año 2020, despues en el año 2021 y finalmente en el año 2022 y se comparo si 2020 tiene
-- una cantidad mayor o ya sea el año 2021 o 2022.
Declare @cant2020 int, @cant2021 int, @cant2022 int
set @cant2020 = ( Select COUNT( case when  FECHA_DEF like '2020-%' then FECHA_DEF end ) as total_Defunciones  
				  from dbo.datoscovid
				  where EDAD < 18 ) -- 1776
set @cant2021 = ( Select COUNT( case when  FECHA_DEF like '2021-%' then FECHA_DEF end ) as total_Defunciones  
				  from dbo.datoscovid
				  where EDAD < 18 ) -- 1636
set @cant2022 = ( Select COUNT( case when  FECHA_DEF like '2022-%' then FECHA_DEF end ) as total_Defunciones  
				  from dbo.datoscovid
				  where EDAD < 18 ) -- 314

IF @cant2020 > @cant2021 and @cant2020 > @cant2022
	print 'En el año 2020 hubo una mayor cantidad de defunciones en personas menores de edad que en el año 2021 y 2022'
ELSE
	print 'En el año 2020 NO hubo una mayor cantidad de defunciones en personas menores de edad que en el año 2021 y 2022'
	

-- Solucion 2
-- Se utilizo ideas totalmente iguales sin embargo, no se utilizo un print que indicara el resultado a estas consultas, si no 
-- se agruparon estos datos en una sola tabla con union all, para que un usuario pueda visualizar cuantas defunciones hubo por 
-- año y sea este mismo quien determine la respuesta a la pregunta.
Select 2020 as Año, COUNT( case when  FECHA_DEF like '2020-%' then FECHA_DEF end ) as total_Defunciones  
from dbo.datoscovid
where EDAD < 18 
UNION ALL 
Select 2021 as Año, COUNT( case when  FECHA_DEF like '2021-%' then FECHA_DEF end ) as total_Defunciones  
from dbo.datoscovid
where EDAD < 18 
UNION ALL
Select 2022 as Año, COUNT( case when  FECHA_DEF like '2022-%' then FECHA_DEF end ) as total_Defunciones  
from dbo.datoscovid
where EDAD < 18 

-- 9. Determinar si en el año 2021 hay un pocentaje mayor al 60 de casos reportados que son confirmados por estudios de laboratorio 
	--en comparación al año 2020.

-- Solucion 1
-- Un paciete que es positivo en covid que es confirmado por estudios de laboratorio tiene el valor 3 en el campo clasificacion final.
-- Se considera casos reportado si se encuentra en la tabla.
-- Se realiza dos consultas en los años especificados con las condiciones previamente mencionadas.
-- Se imprime el resultado.
Declare @porcentaje2021 int, @porcentaje2020 int
set @porcentaje2021 = (	select aux.Confirmados_Laboratorio*100/aux.Reportados as Confirmados_Laboratorio
						from (
							Select count(*) as Reportados, COUNT( case when CLASIFICACION_FINAL between 1 and 3 then CLASIFICACION_FINAL end ) as Confirmados, 
														   COUNT( case when CLASIFICACION_FINAL = 3 then CLASIFICACION_FINAL end ) as Confirmados_Laboratorio
							from dbo.datoscovid
							where FECHA_INGRESO like '2021-%' ) as aux ) -- 26%

set @porcentaje2020 = (	select aux.Confirmados_Laboratorio*100/aux.Reportados as Confirmados_Laboratorio
						from (
							Select count(*) as Reportados, COUNT( case when CLASIFICACION_FINAL between 1 and 3 then CLASIFICACION_FINAL end ) as Confirmados, 
														   COUNT( case when CLASIFICACION_FINAL = 3 then CLASIFICACION_FINAL end ) as Confirmados_Laboratorio
							from dbo.datoscovid
							where FECHA_INGRESO like '2020-%' ) as aux ) -- 38%

if @porcentaje2021 > @porcentaje2020
	print 'En el año 2021 hay un pocentaje mayor de casos reportados que son confirmados por estudios de laboratorio en comparación al año 2020.'
else
	print 'En el año 2020 hay un pocentaje mayor de casos reportados que son confirmados por estudios de laboratorio en comparación al año 2021.'


-- Solucion 2	
-- Se realizo un analisis igual pero utilizando subconsultas.
Declare @porcentaje2021 int, @porcentaje2020 int

set @porcentaje2021 = ( Select ( Select count(*)
								from dbo.datoscovid
								where FECHA_INGRESO like '2021-%' and CLASIFICACION_FINAL = 3 ) *100/ COUNT(*) as Confirmados_Laboratorio
						from dbo.datoscovid
						where FECHA_INGRESO like '2021-%' )

set @porcentaje2020 = ( Select ( Select count(*)
								from dbo.datoscovid
								where FECHA_INGRESO like '2020-%' and CLASIFICACION_FINAL = 3 ) *100/ COUNT(*) as Confirmados_Laboratorio
						from dbo.datoscovid
						where FECHA_INGRESO like '2020-%' )

if @porcentaje2021 > @porcentaje2020
	print 'En el año 2021 hay un pocentaje mayor de casos reportados que son confirmados por estudios de laboratorio en comparación al año 2020.'
else
	print 'En el año 2020 hay un pocentaje mayor de casos reportados que son confirmados por estudios de laboratorio en comparación al año 2021.'


-- 10. Determinar en que rango de edad: menor de edad, 19 a 40, 40 a 60 o mayor de 60 hay mas casos reportados que se hayan recuperado. 
-- Nota: Se desprecia la edad 18 y se toma en cuenta dos veces la edad de 40, porque asi lo indica la consulta.

-- Solucion 1
-- Cuando un paciente muere, se coloca la fecha de deceso en el campo fecha_def pero si esta aun se encuentra vivo, es que en
-- este campo se encuentra el valor de 9999-99-99.
-- Se buscaron a la cantidad de pacientes vivos en los rangos de edad mencionados por el enunciado.
-- se agruparon estos datos en una sola tabla con union all.
-- Se busco la cantidad mayor de personas vivos.
-- Se volvio a buscar de nuevo a la cantidad de pacientes vivos en los rangos de edad mencionados por el enunciado pero condicionando
-- que solo mostrara la cantidad que sea igual a la cantidad mayor de pacientes vivos.
SELECT *
from
	( select 'menor de edad' as Rango_Edad, COUNT(*) as Recuperados
	from dbo.datoscovid
	where EDAD < 18 and FECHA_DEF = '9999-99-99'
	UNION ALL
	select '19 a 40' as Rango_Edad, COUNT(*) as Recuperados
	from dbo.datoscovid
	where EDAD between 19 and 40 and FECHA_DEF = '9999-99-99'
	UNION ALL
	select '40 a 60' as Rango_Edad, COUNT(*) as Recuperados
	from dbo.datoscovid
	where EDAD between 40 and 60 and FECHA_DEF = '9999-99-99'
	UNION ALL
	select 'mayor a 60' as Rango_Edad, COUNT(*) as Recuperados
	from dbo.datoscovid
	where EDAD > 60 and FECHA_DEF = '9999-99-99' ) as aux
where aux.Recuperados = (  SELECT MAX(aux2.Recuperados)
						from
							( select 'menor de edad' as Rango_Edad, COUNT(*) as Recuperados
							from dbo.datoscovid
							where EDAD < 18 and FECHA_DEF = '9999-99-99'
							UNION ALL
							select '19 a 40' as Rango_Edad, COUNT(*) as Recuperados
							from dbo.datoscovid
							where EDAD between 19 and 40 and FECHA_DEF = '9999-99-99'
							UNION ALL
							select '40 a 60' as Rango_Edad, COUNT(*) as Recuperados
							from dbo.datoscovid
							where EDAD between 40 and 60 and FECHA_DEF = '9999-99-99'
							UNION ALL
							select 'mayor a 60' as Rango_Edad, COUNT(*) as Recuperados
							from dbo.datoscovid
							where EDAD > 60 and FECHA_DEF = '9999-99-99' ) as aux2 )

-- Solucion 2




