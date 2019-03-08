package com.core.blog.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import redis.clients.jedis.Jedis;

import com.core.blog.po.customer;
import com.core.blog.redis.RedisUtil;
import com.core.blog.service.CustomerServer;




@Controller
public class CustomerControl {
	@Autowired CustomerServer customerserver;
	
	@RequestMapping("/customer")
	@ResponseBody
	public List<customer> find(){
		System.out.println("我真的被执行了");		
		List<customer> cust = customerserver.findcustomer();
		for(customer ll :cust){
		System.out.println(ll.getCust_level());
		}
		return cust;
	}
	
	@RequestMapping("/hah")
	public String hhh(){
		return "redis";
		
	}
	@RequestMapping("/redis")
	public ModelAndView getredis(){
		ModelAndView modelandview = new ModelAndView("redis");
		RedisUtil redisUtil = new RedisUtil();
	    Jedis jedis = redisUtil.getJedis();
	    String id = jedis.get("id");
	    modelandview.addObject("id", id);
		return modelandview;
		
	}
	
	@RequestMapping("/add")
	public String addredis(){
		RedisUtil redisUtil = new RedisUtil();
	    Jedis jedis = redisUtil.getJedis();
	    String id = jedis.get("id");
	    int iid = Integer.parseInt(id)+1;
	    String sid =String.valueOf(iid);
	    jedis.set("id",sid);
		return "redirect:/redis";
		
	}
	

}
