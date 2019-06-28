package com.core.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.blog.mapper.CustomerMapper;
import com.core.blog.po.customer;

@Service
public class CustomerServerImp implements CustomerServer {
@Autowired
	private CustomerMapper customer;
	@Override
	public List<customer> findcustomer() {
		// TODO Auto-generated method stub
		return customer.findcustomer();
	}
	@Override
	public int findsum() {
		// TODO Auto-generated method stub
		return customer.findsum();
	}

}
