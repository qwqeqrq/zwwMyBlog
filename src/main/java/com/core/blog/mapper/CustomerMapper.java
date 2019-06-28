package com.core.blog.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.core.blog.po.customer;
@Mapper
public interface CustomerMapper {
	
	@Select("SELECT cust_id,cust_name,cust_user_id,cust_creat_id,cust_source, cust_industry,cust_level,  " +
			"cust_limkman,cust_phone,cust_mobile,cust_zipcode,cust_address,cust_createtime FROM customer")
	 List<customer>  findcustomer();
	
	@Select("select count(1) from customer")
	 int  findsum();

}
