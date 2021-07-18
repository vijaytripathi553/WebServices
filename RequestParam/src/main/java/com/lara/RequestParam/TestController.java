package com.lara.RequestParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping("tets1")										//http://localhost:9090/tets1?name=Ram
	public String test1(@RequestParam String name)
	{
		return "Your Name is:"+""+name;
	}
	
	
	@GetMapping("tets2")										//http://localhost:9090/tets2?firstName=Ram&lastName=Shyam
	public String test2(@RequestParam String firstName,@RequestParam String lastName)
	{
		return "Your Full Name is:"+""+firstName+lastName;
	}
	
	
	@GetMapping("tets3")										//http://localhost:9090/tets3?email=raman012345@gmail.com
																				// OR
																//http://localhost:9090/tets3
	
	public String test3(@RequestParam(required=false) String email)   // default value of required is true
	{																  // So this API can be called in two ways that is with value and without value. When value is not being suppield in that case it will return false.
		return "Your Email is:"+""+email;
	}

}
