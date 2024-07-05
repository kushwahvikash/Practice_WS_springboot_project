package com.example.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/test")
	public Map<String, String> test() {
		Map<String,String> map=new HashMap<String, String>();
		map.put("studentName", "Vikas");
		map.put("studentClass", "MCA");
		map.put("studentRollNo", "01");
		System.out.println("This is test api...");
		return map;
	}
}
