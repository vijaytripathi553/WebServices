package com.lara.fileuploadjpa.controller;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lara.fileuploadjpa.PersonRepository.PersonRepository;
import com.lara.fileuploadjpa.entity.Person;


@RestController
public class PersonController {
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(path="upload",method=RequestMethod.POST)
	public String upload(@RequestParam MultipartFile file)throws Exception
	{
		String fileName=file.getOriginalFilename();
		FileOutputStream fout=new FileOutputStream("src/main/resources/uploads/"+fileName);
		fout.write(file.getBytes());
		// The above 3 lines of code will upload the file on target address that is src/main/resources/uploads
		
		// Now below lines are going to be used for inserting file data into db ( Code to insert filr data into DB)
		FileReader fin=new FileReader("src/main/resources/uploads/"+fileName);
		BufferedReader bin=new BufferedReader(fin);
		String line;
		String fields[]=new String[100];
		
		List<Person> per=new ArrayList<Person>();
		
		while((line=bin.readLine())!=null)
		{
			fields=line.split(";");
			
			Person pp=new Person();
			pp.setId(Integer.parseInt(fields[0]));
			pp.setFirstName(fields[1]);
			pp.setLastName(fields[2]);
			pp.setAge(Integer.parseInt(fields[3]));
			pp.setEmail(fields[4]);
			
			per.add(pp);
		}
		
		personRepository.saveAll(per);
		return "success";
	}

}