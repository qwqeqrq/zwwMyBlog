package com.core.blog.service;

import java.util.List;

import com.core.blog.po.customer;

public interface CustomerServer {

	 List<customer>  findcustomer();
	
	 int  findsum();
}
