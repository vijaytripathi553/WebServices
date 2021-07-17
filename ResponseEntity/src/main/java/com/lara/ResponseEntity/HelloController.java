package com.lara.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//ResponseEntity: Earlier were returning a person object or list objects or map objects etc as a result. But now we are using ResponseEntity which will not return 
//result (like earlier) but it will also return the statuscode of result.
// Suppose you are searching for a student record whose id is 10 but if id does'nt exist will get nothing in response. But with ResponseEntity we can track the staus whethet response is ok means available or not

@RestController
public class HelloController {

	// This API is going to return String object as a result and also it will return
	// the status of response which can be seen over the testing tool like ARC or
	// PostMan
	@GetMapping("service1")
	public ResponseEntity<String> service1() {
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("success", HttpStatus.GATEWAY_TIMEOUT);
		return responseEntity;

	}

	// This API is going to return list of integer object as a response likewise we
	// can return String objects,double objects,list set and map objects.
	@GetMapping("service2")
	public ResponseEntity<List<Integer>> listOfIntegers() {

		List<Integer> li = new ArrayList<Integer>();
		li.add(100);
		li.add(500);
		li.add(190);
		li.add(136);
		li.add(148);

		ResponseEntity<List<Integer>> responseEntity = new ResponseEntity<List<Integer>>(li, HttpStatus.OK);
		return responseEntity;

	}

	// This API is developed to return Person object
	@GetMapping("service3")
	public ResponseEntity<Person> listOfPersonObjects() {
		Person p = new Person();
		p.setId(101);
		p.setFirstName("Ram");
		p.setLastName("Shyam");

		ResponseEntity<Person> responseEntity = new ResponseEntity<Person>(p, HttpStatus.OK);
		return responseEntity;

	}

	// This API will help in understanding the importance of ResponseEntity
	@GetMapping("service4/{id}")
	public ResponseEntity<String> service4(@PathVariable Integer id) {
		
		ResponseEntity<String> responseEntity = null;
		if (id < 0) {
			responseEntity = new ResponseEntity<String>("Id must be > 0", HttpStatus.BAD_REQUEST);
		} else {
			responseEntity = new ResponseEntity<String>("Youir Id is:" + id, HttpStatus.OK);

		}

		return responseEntity;
	}
	
	// This API is going to show the different approach of returning result as well as status code. It means all the above API can be return in this form as well.
	// The diff bet above approach and this approach is 
	// In the above approach we are passing both result as well as status code in the ResponseEntity constructor 
	//while in this approach we specify the status code first and then in body() we specify the result
	
	@GetMapping("service5/{dobYear}")
	public ResponseEntity<String> service5(@PathVariable Integer dobYear) {
		
		ResponseEntity<String> responseEntity = null;
		if (dobYear > 2021 ) {
			responseEntity = ResponseEntity.badRequest().body("DOB year can't be grater then current year");
		} else {
			dobYear=2021-dobYear;
			responseEntity =ResponseEntity.ok().body("You are"+dobYear+" old");

		}

		return responseEntity;
	}
	
	
	// UNIT - ResponseEntity body, header and status code
	// This API is going to return body , header and status code
	
	@GetMapping("service6")
	public ResponseEntity<String> service6()
	{
		return ResponseEntity.ok().header("content/type", "application/json").body("ResponseEntity Implementation with status code , header and body");
	}
	
	int counter=0;
	@GetMapping("service7")
	public ResponseEntity<String> service7()
	{
		counter++;
		return ResponseEntity.ok().header("refresh","4" ).body("Counter:"+" "+counter);
	}
	

	

}
