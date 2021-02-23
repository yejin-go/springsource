select * from PRODUCTTBL;
select * from PRODUCTTBL_ATTACH where pcode=65;

delete from producttbl where pcode=366;

select *
		from (select /*+INDEX_DESC(PRODUCTTBL ProductTBL_PK)*/
	  		  rownum rn, pcode, pcategory, product, pcontent, price, pamount,pdate,origin
	  		  from PRODUCTTBL where		
			rownum <= (1*10))
			where rn > (1-1)*10;
			
			
select p.pcode,pcategory,product,pcontent,price,pamount,pdate,origin,uuid,filename
		from producttbl p left outer join PRODUCTTBL_ATTACH attach
		on p.pcode = attach.pcode
		where p.pcode=#{pcode};
	

		
select *	
from(select rn,p.pcode,pcategory,product,pcontent,price,pamount,pdate,origin,uuid,filename
	 from (select /*+INDEX_DESC(PRODUCTTBL ProductTBL_PK)*/
	  	   rownum rn, pcode, pcategory, product, pcontent, price, pamount,pdate,origin from PRODUCTTBL) p left outer join PRODUCTTBL_ATTACH attach
		   on p.pcode = attach.pcode) where	rownum <= (1*10)
	where rn > (1-1)*10;
		

	
select *	
from(select rn,p.pcode,pcategory,product,pcontent,price,pamount,pdate,origin,uuid,filename
	 from (select /*+INDEX_DESC(PRODUCTTBL ProductTBL_PK)*/
	  	   rownum rn, pcode, pcategory, product, pcontent, price, pamount,pdate,origin from PRODUCTTBL) p left outer join PRODUCTTBL_ATTACH attach
		   on p.pcode = attach.pcode where	pcategory = '라이트' and rownum <= (1*10))
where rn > (1-1)*10;
	
	
	
	
	
	select p.pcode,pcategory,product,pcontent,price,pamount,pdate,origin,uuid,filename
		from producttbl p left outer join PRODUCTTBL_ATTACH attach
		on p.pcode = attach.pcode
		where p.pcode=48;	
		
		