package com.core;

import com.core.blog.po.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@EnableScheduling
@ComponentScan("com.core")
@SpringBootApplication
public class MyBlog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(MyBlog.class, args);
		System.out.println("----------------------------项目已启动！！！！");
	}
	@RequestMapping("/")
	public String run(Model model){
		Result result = new Result();
		result.setCode(0);
		model.addAttribute("result",result);
		return "login";
	}

}
